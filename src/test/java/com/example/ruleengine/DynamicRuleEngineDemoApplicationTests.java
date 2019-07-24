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
        Facts facts = new Facts();
        Rules rules = new Rules();
        rules.register(new AgeRule());
        RulesEngine rulesEngine = new DefaultRulesEngine();

        int age = 30;
        System.out.println("basicRuleTest fire rule engine with age=" + age);
        facts.put("age",age);
        rulesEngine.fire(rules,facts);

        age = 31;
        System.out.println("basicRuleTest fire rule engine with age=" + age);
        facts.put("age",age);
        rulesEngine.fire(rules,facts);
    }

    @Test
    /*
     * 任何一个规则条件不满足，则任何动作都不会执行
     */
    public void unitRuleGroupTest(){
        Facts facts = new Facts();
        Rules rules = new Rules();
        rules.register(new EnrollUnitRuleGroup(new AgeRule(), new GenderRule()));
        RulesEngine rulesEngine = new DefaultRulesEngine();

        int age = 30;
        String gender = "male";
        facts.put("age",age);
        facts.put("gender",gender);
        System.out.println("unitRuleGroupTest------- fire rule engine with age=" + age + " gender=" + gender);
        rulesEngine.fire(rules,facts);

        age = 31;
        gender = "male";
        facts.put("age",age);
        facts.put("gender",gender);
        System.out.println("unitRuleGroupTest------- fire rule engine with age=" + age + " gender=" + gender);
        rulesEngine.fire(rules,facts);

        age = 30;
        gender = "female";
        facts.put("age",age);
        facts.put("gender",gender);
        System.out.println("unitRuleGroupTest------- fire rule engine with age=" + age + " gender=" + gender);
        rulesEngine.fire(rules,facts);
    }

    @Test
    /*
     * 满足条件的那个规则的动作，如果优先级高会执行
     */
    public void conditionRuleGroupTest(){
        Facts facts = new Facts();
        Rules rules = new Rules();
        rules.register(new EnrollConditionalRuleGroup(new AgeRule(), new GenderRule()));
        RulesEngine rulesEngine = new DefaultRulesEngine();

        int age = 30;
        String gender = "male";
        facts.put("age",age);
        facts.put("gender",gender);
        System.out.println("conditionRuleGroupTest--------- fire rule engine with age=" + age + " gender=" + gender);
        rulesEngine.fire(rules,facts);

        age = 31;
        gender = "male";
        facts.put("age",age);
        facts.put("gender",gender);
        System.out.println("conditionRuleGroupTest--------- fire rule engine with age=" + age + " gender=" + gender);
        rulesEngine.fire(rules,facts);

        age = 30;
        gender = "female";
        facts.put("age",age);
        facts.put("gender",gender);
        System.out.println("conditionRuleGroupTest--------- fire rule engine with age=" + age + " gender=" + gender);
        rulesEngine.fire(rules,facts);
    }

    @Test
    /*
     * 按优先级顺序，只要满足其中一个规则，立刻执行动作，同时跳过剩下的规则
     */
    public void activationRuleGroupTest(){
        Facts facts = new Facts();
        Rules rules = new Rules();
        rules.register(new EnrollActivationRuleGroup(new AgeRule(), new GenderRule()));
        RulesEngine rulesEngine = new DefaultRulesEngine();

        int age = 30;
        String gender = "male";
        facts.put("age",age);
        facts.put("gender",gender);
        System.out.println("activationRuleGroupTest--------- fire rule engine with age=" + age + " gender=" + gender);
        rulesEngine.fire(rules,facts);

        age = 31;
        gender = "male";
        facts.put("age",age);
        facts.put("gender",gender);
        System.out.println("activationRuleGroupTest--------- fire rule engine with age=" + age + " gender=" + gender);
        rulesEngine.fire(rules,facts);

        age = 30;
        gender = "female";
        facts.put("age",age);
        facts.put("gender",gender);
        System.out.println("activationRuleGroupTest--------- fire rule engine with age=" + age + " gender=" + gender);
        rulesEngine.fire(rules,facts);
    }

}
