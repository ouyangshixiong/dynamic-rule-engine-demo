package com.example.ruleengine.apis;

import com.example.ruleengine.rules.*;
import io.swagger.annotations.ApiOperation;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.support.CompositeRule;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    @ApiOperation(value="unit规则组演示，所有规则是 AND(&&) 的关系",notes = "Java Rule")
    @GetMapping("unit-rule-group-test")
    public String unitRuleGroupTest(@RequestParam Integer age, @RequestParam String gender){
        StringBuilder sb = new StringBuilder();
        sb.append("unitRuleGroupTest------- fire rule engine with age=" + age + " gender=" + gender +"\n");
        runRuleEngine(age,gender,sb,new EnrollUnitRuleGroup(new AgeRule(), new GenderRule()));
        return sb.toString();
    }

    /**
     * 满足条件的那个规则的动作，如果优先级高会执行
     **/
    @ApiOperation(value="condition规则组演示，满足条件立即执行动作，一旦不满足跳过剩下规则",notes = "Java Rule")
    @GetMapping("condition-rule-group-test")
    public String conditionRuleGroupTest(@RequestParam Integer age, @RequestParam String gender){
        StringBuilder sb = new StringBuilder();
        sb.append("conditionRuleGroupTest------- fire rule engine with age=" + age + " gender=" + gender +"\n");
        runRuleEngine(age,gender,sb,new EnrollConditionalRuleGroup(new AgeRule(), new GenderRule()));
        return sb.toString();
    }

    /**
     * 按默认优先级顺序，只要满足其中一个规则，立刻执行动作，互不影响
     */
    @ApiOperation(value="activation规则组演示，所有规则是 XOR 的关系",notes = "Java Rule")
    @GetMapping("activation-rule-group-test")
    public String activationRuleGroupTest(@RequestParam Integer age, @RequestParam String gender){
        StringBuilder sb = new StringBuilder();
        sb.append("activationRuleGroupTest------- fire rule engine with age=" + age + " gender=" + gender +"\n");
        runRuleEngine(age,gender,sb,new EnrollActivationRuleGroup(new AgeRule(), new GenderRule()));
        return sb.toString();
    }

    private void runRuleEngine(int age, String gender, StringBuilder sb, CompositeRule rulegroup){
        Facts facts = new Facts();
        facts.put("sb",sb);
        Rules rules = new Rules();
        rules.register(rulegroup);
        RulesEngine rulesEngine = new DefaultRulesEngine();
        facts.put("age",age);
        facts.put("gender",gender);
        rulesEngine.fire(rules,facts);
    }
}
