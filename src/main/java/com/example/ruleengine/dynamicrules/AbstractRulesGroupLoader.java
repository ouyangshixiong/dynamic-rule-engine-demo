package com.example.ruleengine.dynamicrules;

import com.example.ruleengine.dynamicrules.domain.BusinessRuleDefinition;
import com.example.ruleengine.dynamicrules.domain.DynamicRule;
import com.example.ruleengine.dynamicrules.domain.RelationTable;
import com.example.ruleengine.dynamicrules.repository.BusinessRuleDefinitionRepository;
import com.example.ruleengine.dynamicrules.repository.DynamicRuleRepository;
import com.example.ruleengine.dynamicrules.repository.RelationTableRepository;
import com.example.ruleengine.rules.AgeRule;
import com.example.ruleengine.rules.EnrollUnitRuleGroup;
import com.example.ruleengine.rules.GenderRule;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jeasy.rules.support.YamlRuleDefinitionReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author alexouyang
 * @Date 2019-07-25
 */
public class AbstractRulesGroupLoader implements RulesGroupLoader {

    private static final Logger log = LoggerFactory.getLogger(AbstractRulesGroupLoader.class);

    private Long businessRuleDefinitionId;

    @Autowired
    private DynamicRuleRepository dynamicRuleRepository;

    @Autowired
    private RelationTableRepository relationTableRepository;

    @Autowired
    private BusinessRuleDefinitionRepository businessRuleDefinitionRepository;

    public Long getBusinessRuleDefinitionId() {
        return businessRuleDefinitionId;
    }

    public void setBusinessRuleDefinitionId(Long businessRuleDefinitionId) {
        this.businessRuleDefinitionId = businessRuleDefinitionId;
    }

    @Override
    public void load() {

    }

    protected List<Rule> selectRulesFromDB(){
        List<Rule> rules = new ArrayList<>();

        Optional<BusinessRuleDefinition> rs = businessRuleDefinitionRepository.findById(businessRuleDefinitionId);
        if( rs.isPresent() ){
            BusinessRuleDefinition businessRuleDefinition = rs.get();
            log.info("success load businessRuleDefinition =" + businessRuleDefinition.toString());
            Optional<List<RelationTable>> rs2 = relationTableRepository.findAllByBusinessRuleDefinitionId(businessRuleDefinition.getId());
            if(rs2.isPresent()){
                List<RelationTable> relationTableList = rs2.get();
                for( RelationTable relationTable : relationTableList){
                    Long dynamicRuleId = relationTable.getDynamicRuleId();
                    Optional<DynamicRule> rs3 = dynamicRuleRepository.findById(dynamicRuleId);
                    if( rs3.isPresent() ){
                        DynamicRule dynamicRule = rs3.get();
                        MVELRuleFactory ruleFactory = new MVELRuleFactory(new YamlRuleDefinitionReader());
                        try {
                            Rule rule = ruleFactory.createRule(new StringReader(dynamicRule.getContent()));
                            rules.add(rule);
                        }catch (Exception e){
                            log.error("rule content 解析不出来, dynamicRuleId=" + dynamicRuleId);
                        }

                    }else{
                        log.error("找不到DynamicRule id=" + rs3);
                    }

                }
            }else{
                log.error("找不到对应的关系表记录 businessRuleDefinition=" + businessRuleDefinition.getId());
            }

        }else{
            log.error("找不到businessRuleDefinition id=" + businessRuleDefinitionId);
        }

        return rules;
    }
}
