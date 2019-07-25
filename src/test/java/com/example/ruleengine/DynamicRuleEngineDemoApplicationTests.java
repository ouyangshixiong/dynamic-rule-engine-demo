package com.example.ruleengine;

import com.example.ruleengine.rules.*;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DynamicRuleEngineDemoApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Test
    public void basicRuleTest(){
        StringBuilder sb = new StringBuilder();
        Facts facts = new Facts();
        Rules rules = new Rules();
        rules.register(new AgeRule(sb));
        RulesEngine rulesEngine = new DefaultRulesEngine();

        int age = 30;
        sb.append("basicRuleTest fire rule engine with age=" + age +"\n");
        facts.put("age",age);
        rulesEngine.fire(rules,facts);

        age = 31;
        sb.append("basicRuleTest fire rule engine with age=" + age +"\n");
        facts.put("age",age);
        rulesEngine.fire(rules,facts);
        System.out.println(sb.toString());
    }

    @Test
    /*
     * 任何一个规则条件不满足，则任何动作都不会执行
     */
    public void unitRuleGroupTest(){
        StringBuilder sb = new StringBuilder();
        Facts facts = new Facts();
        Rules rules = new Rules();
        rules.register(new EnrollUnitRuleGroup(new AgeRule(sb), new GenderRule(sb)));
        RulesEngine rulesEngine = new DefaultRulesEngine();

        int age = 30;
        String gender = "male";
        facts.put("age",age);
        facts.put("gender",gender);
        sb.append("unitRuleGroupTest------- fire rule engine with age=" + age + " gender=" + gender +"\n");
        rulesEngine.fire(rules,facts);

        age = 31;
        gender = "male";
        facts.put("age",age);
        facts.put("gender",gender);
        sb.append("unitRuleGroupTest------- fire rule engine with age=" + age + " gender=" + gender +"\n");
        rulesEngine.fire(rules,facts);

        age = 30;
        gender = "female";
        facts.put("age",age);
        facts.put("gender",gender);
        sb.append("unitRuleGroupTest------- fire rule engine with age=" + age + " gender=" + gender +"\n");
        rulesEngine.fire(rules,facts);
        System.out.println(sb.toString());
    }

    @Test
    /*
     * 满足条件的那个规则的动作，如果优先级高会执行
     */
    public void conditionRuleGroupTest(){
        StringBuilder sb = new StringBuilder();
        Facts facts = new Facts();
        Rules rules = new Rules();
        rules.register(new EnrollConditionalRuleGroup(new AgeRule(sb), new GenderRule(sb)));
        RulesEngine rulesEngine = new DefaultRulesEngine();

        int age = 30;
        String gender = "male";
        facts.put("age",age);
        facts.put("gender",gender);
        sb.append("conditionRuleGroupTest--------- fire rule engine with age=" + age + " gender=" + gender +"\n");
        rulesEngine.fire(rules,facts);

        age = 31;
        gender = "male";
        facts.put("age",age);
        facts.put("gender",gender);
        sb.append("conditionRuleGroupTest--------- fire rule engine with age=" + age + " gender=" + gender +"\n");
        rulesEngine.fire(rules,facts);

        age = 30;
        gender = "female";
        facts.put("age",age);
        facts.put("gender",gender);
        sb.append("conditionRuleGroupTest--------- fire rule engine with age=" + age + " gender=" + gender +"\n");
        rulesEngine.fire(rules,facts);
        System.out.println(sb.toString());
    }

    @Test
    /*
     * 按优先级顺序，只要满足其中一个规则，立刻执行动作，同时跳过剩下的规则
     */
    public void activationRuleGroupTest(){
        StringBuilder sb = new StringBuilder();
        Facts facts = new Facts();
        Rules rules = new Rules();
        rules.register(new EnrollActivationRuleGroup(new AgeRule(sb), new GenderRule(sb)));
        RulesEngine rulesEngine = new DefaultRulesEngine();

        int age = 30;
        String gender = "male";
        facts.put("age",age);
        facts.put("gender",gender);
        sb.append("activationRuleGroupTest--------- fire rule engine with age=" + age + " gender=" + gender +"\n");
        rulesEngine.fire(rules,facts);

        age = 31;
        gender = "male";
        facts.put("age",age);
        facts.put("gender",gender);
        sb.append("activationRuleGroupTest--------- fire rule engine with age=" + age + " gender=" + gender +"\n");
        rulesEngine.fire(rules,facts);

        age = 30;
        gender = "female";
        facts.put("age",age);
        facts.put("gender",gender);
        sb.append("activationRuleGroupTest--------- fire rule engine with age=" + age + " gender=" + gender +"\n");
        rulesEngine.fire(rules,facts);
        System.out.println(sb.toString());
    }

}
