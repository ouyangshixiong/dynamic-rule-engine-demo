package com.example.ruleengine.apis;

import com.alibaba.fastjson.JSONObject;
import com.example.ruleengine.domain.SimCardGroup;
import com.example.ruleengine.dynamicrules.DbRulesGroupLoaderFactory;
import com.example.ruleengine.dynamicrules.DbRulesGroupLoader;
import com.example.ruleengine.dynamicrules.RulesGroupType;
import io.swagger.annotations.ApiOperation;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author alexouyang
 * @Date 2019-07-26
 */
@RestController
public class DynamicRuleDemo {

    @Autowired
    DbRulesGroupLoaderFactory dbRulesGroupLoaderFactory;
//    /**
//     * 任何一个规则条件不满足，则任何动作都不会执行
//     **/
//    @ApiOperation(value="unit规则组演示，所有规则是 AND(&&) 的关系",notes = "Java Rule")
//    @GetMapping("unit-rule-group-test-dynamic")
//    public String unitRuleGroupTest(@RequestParam Integer age, @RequestParam String gender, @RequestParam Integer salary){
//        StringBuilder sb = new StringBuilder();
//        Facts facts = new Facts();
//        facts.put("sb", sb);
//        facts.put("age", age);
//        facts.put("gender", gender);
//        facts.put("salary", salary);
//        sb.append("unitRuleGroupTest------- fire rule engine with age=" + age + " gender=" + gender + " salary=" + salary +"\n");
//        RulesEngine rulesEngine = new DefaultRulesEngine();
//        Long rulesGroupInfoId = 1L;
//        DbRulesGroupLoader loader = dbRulesGroupLoaderFactory.getLoader(RulesGroupType.UnitRulesGroup);
//        Rules rules = loader.doLoad(rulesGroupInfoId);
//        rulesEngine.fire(rules,facts);
//        System.out.println(sb.toString());
//        return sb.toString();
//    }

    /**
     * 任何一个规则条件不满足，则任何动作都不会执行
     **/
    @ApiOperation(value="unit规则组演示，所有规则是 AND(&&) 的关系",notes = "Dynamic Rule")
    @PostMapping("unit-rule-group-test-dynamic")
    public String unitRuleGroupTest2(@RequestBody String json){
        StringBuilder sb = new StringBuilder();
        Facts facts = new Facts();
        JSONObject jsonObject = JSONObject.parseObject(json);
        List<SimCardGroup> matchedSimCardGroups = new ArrayList<>();
        facts.put("matchedSimCardGroups",matchedSimCardGroups);
        facts.put("country",jsonObject.get("country"));
        facts.put("isVIP",jsonObject.get("isVIP"));
        facts.put("partner",jsonObject.get("partner"));
        RulesEngine rulesEngine = new DefaultRulesEngine();
        Long rulesGroupInfoId = 2L;
        DbRulesGroupLoader loader = dbRulesGroupLoaderFactory.getLoader(RulesGroupType.UnitRulesGroup);
        Rules rules = loader.doLoad(rulesGroupInfoId);
        rulesEngine.fire(rules,facts);
        for(int i=0; i<matchedSimCardGroups.size(); i++){
            sb.append( (i+1) + ": " + matchedSimCardGroups.get(i).toString()+"\n");
        }
        return sb.toString();
    }
}
