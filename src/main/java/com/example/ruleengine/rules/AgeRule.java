package com.example.ruleengine.rules;

import org.jeasy.rules.annotation.*;
import org.jeasy.rules.api.Facts;

/**
 * @author alexouyang
 * 2019-07-24
 */
@Rule
public class AgeRule {

    private StringBuilder sb;

    public AgeRule(StringBuilder sb) {
        this.sb = sb;
    }

    @Condition
    public boolean above30(@Fact("age") int age ){
        return age > 30 ? false : true;
    }

    @Action
    public void printAge(Facts facts){
        sb.append("age is " + facts.get("age") +", pass AgeRule" + "\n");
    }

    @Priority
    public int getPriority(){
        return 0;
    }

}
