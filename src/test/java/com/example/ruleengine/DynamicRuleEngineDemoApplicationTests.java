package com.example.ruleengine;

import com.example.ruleengine.domain.SimCardGroup;
import com.example.ruleengine.loader.rulesgroup.DbRulesGroupLoaderFactory;
import com.example.ruleengine.loader.rulesgroup.DbRulesGroupLoader;
import com.example.ruleengine.loader.rulesgroup.RulesGroupType;
import com.example.ruleengine.rules.*;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rule;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.mvel.MVELRuleFactory;
import org.jeasy.rules.support.YamlRuleDefinitionReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

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
        facts.put("sb",sb);
        Rules rules = new Rules();
        rules.register(new AgeRule());
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
        facts.put("sb",sb);
        Rules rules = new Rules();
        rules.register(new EnrollUnitRuleGroup(new AgeRule(), new GenderRule()));
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
        facts.put("sb",sb);
        Rules rules = new Rules();
        rules.register(new EnrollConditionalRuleGroup(new AgeRule(), new GenderRule()));
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
        facts.put("sb",sb);
        Rules rules = new Rules();
        rules.register(new EnrollActivationRuleGroup(new AgeRule(), new GenderRule()));
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

    @Test
    /*
     * 测试从yaml文件中读取规则
     */
    public void salaryTest() throws Exception {
        StringBuilder sb = new StringBuilder();
        Facts facts = new Facts();
        facts.put("sb",sb);
        int age = 30;
        String gender = "male";
        facts.put("age",age);
        facts.put("gender",gender);
        facts.put("salary",900);

        MVELRuleFactory ruleFactory = new MVELRuleFactory(new YamlRuleDefinitionReader());
        Rule salaryRule = ruleFactory.createRule(new FileReader(new ClassPathResource("salary-rule.yml").getFile()));
        Rules rules = new Rules();
        rules.register(new EnrollUnitRuleGroup(new AgeRule(), new GenderRule(), salaryRule));
        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules,facts);
        System.out.println(sb.toString());
    }

    @Test
    /*
     * 测试从inputStream中读取规则
     */
    public void inputStreamTest() throws Exception {
        StringBuilder sb = new StringBuilder();
        Facts facts = new Facts();
        facts.put("sb",sb);
        int age = 30;
        String gender = "male";
        facts.put("age",age);
        facts.put("gender",gender);
        facts.put("salary",900);
        String stringRule = "name: \"salary rule\"\n" +
                "description: \"工资大于1000不要\"\n" +
                "priority: 20\n" +
                "condition: \"salary <= 1000\"\n" +
                "actions:\n" +
                "  - \"((StringBuilder)sb).append(\\\"YAML Rule: salary=\\\"+ salary +\\\" matched\\\");\"";

        MVELRuleFactory ruleFactory = new MVELRuleFactory(new YamlRuleDefinitionReader());
        Rule salaryRule = ruleFactory.createRule(new StringReader(stringRule));
        Rules rules = new Rules();
        rules.register(new EnrollUnitRuleGroup(new AgeRule(), new GenderRule(), salaryRule));
        RulesEngine rulesEngine = new DefaultRulesEngine();
        rulesEngine.fire(rules,facts);
        System.out.println(sb.toString());
    }

    @Autowired
    DbRulesGroupLoaderFactory dbRulesGroupLoaderFactory;

    @Test
    public void dbRulesTest(){
        StringBuilder sb = new StringBuilder();
        Facts facts = new Facts();
        facts.put("sb",sb);
        int age = 30;
        int salary = 900;
        facts.put("age",age);
        facts.put("gender","male");
        facts.put("salary",salary);
        sb.append("unitRuleGroupTest------- fire rule engine with age=" + age + " salary=" + salary +"\n");
        RulesEngine rulesEngine = new DefaultRulesEngine();
        Long rulesGroupInfoId = 1L;
        DbRulesGroupLoader loader = dbRulesGroupLoaderFactory.getLoader(RulesGroupType.UnitRulesGroup);
        Rules rules = loader.doLoad(rulesGroupInfoId);
        rulesEngine.fire(rules,facts);
        System.out.println(sb.toString());
    }

    @Test
    public void dbRules2Test(){
        Facts facts = new Facts();
        List<SimCardGroup> matchedSimCardGroups = new ArrayList<>();
        facts.put("matchedSimCardGroups",matchedSimCardGroups);
        facts.put("country","USA");
        facts.put("isVIP",false);
        facts.put("partner","USA");
        System.out.println("unitRuleGroupTest------- fire rule engine with country=" + facts.get("country") + " isVIP="
                + facts.get("isVIP") + " partner=" + facts.get("partner") +"\n");
        RulesEngine rulesEngine = new DefaultRulesEngine();
        Long rulesGroupInfoId = 2L;
        DbRulesGroupLoader loader = dbRulesGroupLoaderFactory.getLoader(RulesGroupType.ActivationRulesGroup);
        Rules rules = loader.doLoad(rulesGroupInfoId);
        rulesEngine.fire(rules,facts);
        for(int i=0; i<matchedSimCardGroups.size(); i++){
            System.out.println( (i+1) + ": " + matchedSimCardGroups.get(i).toString());
        }
    }


}
