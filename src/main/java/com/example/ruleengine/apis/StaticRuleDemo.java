package com.example.ruleengine.apis;

import com.example.ruleengine.rules.*;
import io.swagger.annotations.ApiOperation;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.support.CompositeRule;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author alexouyang
 *  2019-07-25
 */
@RestController
public class StaticRuleDemo {

    /**
     * 任何一个规则条件不满足，则任何动作都不会执行
     **/
    @ApiOperation(value="unit规则组演示，所有规则是 AND(&&) 的关系")
    @GetMapping("unit-rule-group-test")
    public String unitRuleGroupTest(){
        StringBuilder sb = new StringBuilder();

        int age = 30;
        String gender = "male";
        enrollUnitRuleGroup(age,gender,sb);

        age = 31;
        gender = "male";
        enrollUnitRuleGroup(age,gender,sb);

        age = 30;
        gender = "female";
        enrollUnitRuleGroup(age,gender,sb);

        return sb.toString();
    }

    /**
     * 满足条件的那个规则的动作，如果优先级高会执行
     **/
    @ApiOperation(value="condition规则组演示，满足条件立即执行动作，一旦不满足跳过剩下规则")
    @GetMapping("condition-rule-group-test")
    public String conditionRuleGroupTest(){
        StringBuilder sb = new StringBuilder();

        int age = 30;
        String gender = "male";
        enrollConditionalRuleGroup(age,gender,sb);

        age = 31;
        gender = "male";
        enrollConditionalRuleGroup(age,gender,sb);

        age = 30;
        gender = "female";
        enrollConditionalRuleGroup(age,gender,sb);

        return sb.toString();
    }

    /**
     * 按优先级顺序，只要满足其中一个规则，立刻执行动作，同时跳过剩下的规则
     */
    @ApiOperation(value="activation规则组演示，所有规则是 XOR 的关系")
    @GetMapping("activation-rule-group-test")
    public String activationRuleGroupTest(){
        StringBuilder sb = new StringBuilder();

        int age = 30;
        String gender = "male";
        enrollActivationRuleGroup(age,gender,sb);

        age = 31;
        gender = "male";
        enrollActivationRuleGroup(age,gender,sb);

        age = 30;
        gender = "female";
        enrollActivationRuleGroup(age,gender,sb);

        return sb.toString();
    }


    private void enrollUnitRuleGroup(int age, String gender, StringBuilder sb){
        Facts facts = new Facts();
        Rules rules = new Rules();
        rules.register(new EnrollUnitRuleGroup(new AgeRule(sb), new GenderRule(sb)));
        RulesEngine rulesEngine = new DefaultRulesEngine();
        facts.put("age",age);
        facts.put("gender",gender);
        sb.append("unitRuleGroupTest------- fire rule engine with age=" + age + " gender=" + gender +"\n");
        rulesEngine.fire(rules,facts);
    }

    private void enrollConditionalRuleGroup(int age, String gender, StringBuilder sb){
        Facts facts = new Facts();
        Rules rules = new Rules();
        rules.register(new EnrollConditionalRuleGroup(new AgeRule(sb), new GenderRule(sb)));
        RulesEngine rulesEngine = new DefaultRulesEngine();
        facts.put("age",age);
        facts.put("gender",gender);
        sb.append("conditionRuleGroupTest------- fire rule engine with age=" + age + " gender=" + gender +"\n");
        rulesEngine.fire(rules,facts);
    }

    private void enrollActivationRuleGroup(int age, String gender, StringBuilder sb){
        Facts facts = new Facts();
        Rules rules = new Rules();
        rules.register(new EnrollActivationRuleGroup(new AgeRule(sb), new GenderRule(sb)));
        RulesEngine rulesEngine = new DefaultRulesEngine();
        facts.put("age",age);
        facts.put("gender",gender);
        sb.append("activationRuleGroupTest------- fire rule engine with age=" + age + " gender=" + gender +"\n");
        rulesEngine.fire(rules,facts);
    }
}
