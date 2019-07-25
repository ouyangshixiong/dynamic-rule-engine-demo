package com.example.ruleengine.dynamicrules;

/**
 * @author alexouyang
 * 2019-07-25
 */
public enum RulesGroupType {
    /**
     * 任何一个规则条件不满足，则任何动作都不会执行
     **/
    UNIT("UnitRulesGroup"),
    /**
     * 满足条件的那个规则的动作，如果优先级高会执行
     **/
    CONDITION("ConditionRulesGroup"),
    /**
     * 按默认优先级顺序，只要满足其中一个规则，立刻执行动作，互不影响
     */
    ACTIVATION("ActivationRulesGroup");

    private String ruleGroupType;

    private RulesGroupType(String ruleGroupType){

    }
}
