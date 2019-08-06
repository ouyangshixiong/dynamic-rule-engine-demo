package com.example.ruleengine.apis;

import com.alibaba.fastjson.JSONObject;
import com.example.ruleengine.control.ControlConstants;
import com.example.ruleengine.domain.SimCardGroup;
import com.example.ruleengine.loader.rules.SimpleDbRulesLoader;
import com.example.ruleengine.loader.rulesgroup.DbRulesGroupLoaderFactory;
import com.example.ruleengine.loader.rulesgroup.DbRulesGroupLoader;
import com.example.ruleengine.loader.rulesgroup.RulesGroupType;
import io.swagger.annotations.ApiOperation;
import org.jeasy.rules.api.Facts;
import org.jeasy.rules.api.Rules;
import org.jeasy.rules.api.RulesEngine;
import org.jeasy.rules.core.DefaultRulesEngine;
import org.jeasy.rules.core.RulesEngineParameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author alexouyang
 * @Date 2019-07-26
 */
@RestController
public class DynamicRuleDemo {

    @Autowired
    DbRulesGroupLoaderFactory dbRulesGroupLoaderFactory;

    @Autowired
    SimpleDbRulesLoader simpleDbRulesLoader;
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

    /**
     * 演示用引擎参数控制规则引擎的能力
     **/
    @ApiOperation(value="演示用引擎参数控制规则引擎的能力",notes = "Dynamic Rule")
    @PostMapping("engine-rules-control-test-dynamic")
    public String singleRulesTest2(@RequestBody String json){
        StringBuilder sb = new StringBuilder();
        Facts facts = new Facts();
        JSONObject jsonObject = JSONObject.parseObject(json);
        List<SimCardGroup> matchedSimCardGroups = new ArrayList<>();
        facts.put("matchedSimCardGroups",matchedSimCardGroups);
        facts.put("country",jsonObject.get("country"));
        facts.put("isVIP",jsonObject.get("isVIP"));
        facts.put("partner",jsonObject.get("partner"));

        Boolean skipOnFirstApply = jsonObject.get("skipOnFirstApply") == null ? null : (boolean) jsonObject.get("skipOnFirstApply");
        Boolean skipOnFirstNonTrigger = jsonObject.get("skipOnFirstNonTrigger") == null ? null : (boolean)jsonObject.get("skipOnFirstNonTrigger");

        RulesEngineParameters parameters = new RulesEngineParameters();
        //是否执行完一条后跳过剩下的rules
        if( skipOnFirstApply != null ){
            parameters.setSkipOnFirstAppliedRule((boolean)jsonObject.get("skipOnFirstApply"));
        }else{}
        //是否不触发就跳过剩下的rules
        if( skipOnFirstNonTrigger != null ){
            parameters.setSkipOnFirstNonTriggeredRule((boolean)jsonObject.get("skipOnFirstNonTrigger"));
        }else{}

        RulesEngine rulesEngine = new DefaultRulesEngine(parameters);
        Rules rules = new Rules();
        //执行过程是倒序，请注意
        rules.register(simpleDbRulesLoader.doLoad(4L));
        rules.register(simpleDbRulesLoader.doLoad(5L));
        rules.register(simpleDbRulesLoader.doLoad(6L));
        rulesEngine.fire(rules,facts);
        for(int i=0; i<matchedSimCardGroups.size(); i++){
            sb.append( (i+1) + ": " + matchedSimCardGroups.get(i).toString()+"\n");
        }
        return sb.toString();
    }

    /**
     * 演示用自定义参数控制规则引擎的能力
     **/
    @ApiOperation(value="演示用自定义参数控制规则引擎的能力",notes = "Dynamic Rule")
    @PostMapping("self-defined-rules-contorl-test-dynamic")
    public String singleRulesTest(@RequestBody String json){
        StringBuilder sb = new StringBuilder();
        Facts facts = new Facts();
        JSONObject jsonObject = JSONObject.parseObject(json);
        facts.put("sb",sb);
        facts.put("age",jsonObject.get("age"));
        facts.put("gender",jsonObject.get("gender"));
        facts.put("salary",jsonObject.get("salary"));
        ControlConstants control = new ControlConstants();
        control.setApplyIfEnabled(true);
        control.setChangeStrategyEnabled(true);
        facts.put("control", control);


        RulesEngine rulesEngine = new DefaultRulesEngine();
        Rules rules = new Rules();
        rules.register(simpleDbRulesLoader.doLoad(1L));
        rules.register(simpleDbRulesLoader.doLoad(2L));
        rules.register(simpleDbRulesLoader.doLoad(3L));
        rulesEngine.fire(rules,facts);
        return sb.toString();
    }
}
