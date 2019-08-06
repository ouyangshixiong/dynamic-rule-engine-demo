package com.example.ruleengine.loader.rulesgroup;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author alexouyang
 * @Date 2019-07-25
 * 工厂类，根据传入的规则loader设定的种类
 * 和business业务ID加载DB数据
 */
@Service
public class DbRulesGroupLoaderFactory {

    @Autowired
    UnitDbRulesGroupLoader unitDbRulesGroupLoader;

    @Autowired
    ConditionDbRulesGroupLoader conditionDbRulesGroupLoader;

    @Autowired
    ActivationDbRulesGroupLoader activationDbRulesGroupLoader;

    public DbRulesGroupLoader getLoader(RulesGroupType rulesGroupType) throws IllegalStateException {

        switch(rulesGroupType){
            case UnitRulesGroup:
                return unitDbRulesGroupLoader;
            case ConditionRulesGroup:
                return conditionDbRulesGroupLoader;
            case ActivationRulesGroup:
                return activationDbRulesGroupLoader;
            default:
                throw new IllegalStateException("No db rule loader matched, unexpected value: " + rulesGroupType);
        }
    }

}
