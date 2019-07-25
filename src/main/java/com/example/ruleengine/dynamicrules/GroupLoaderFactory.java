package com.example.ruleengine.dynamicrules;

/**
 * @author alexouyang
 * @Date 2019-07-25
 * 工厂类，根据传入的规则loader设定的种类
 * 和business业务ID加载DB数据
 */
public class GroupLoaderFactory {

    private AbstractRulesGroupLoader loader;

    public RulesGroupLoader getLoader( RulesGroupType rulesGroupType ) throws IllegalStateException {

        switch(rulesGroupType){
            case UNIT:
                loader =  new UnitRulesGroupLoader();
                break;
            case CONDITION:
                loader =  new ConditionRulesGroupLoader();
                break;
            case ACTIVATION:
                loader =  new ActivationRulesGroupLoader();
                break;
            default:
                throw new IllegalStateException("No rule loader matched, unexpected value: " + rulesGroupType);
        }
        return loader;
    }


    public void doLoad(Long businessRuleDefinitionId){
        loader.setBusinessRuleDefinitionId(businessRuleDefinitionId);
        loader.load();
    }
}
