package com.example.ruleengine.rules;

import org.jeasy.rules.annotation.*;
import org.jeasy.rules.api.Facts;

/**
 * @author alexouyang
 * 2019-07-24
 */
@Rule
public class AgeRule {

    @Condition
    public boolean above30(@Fact("age") int age ){
        return age > 30 ? false : true;
    }

    @Action
    public void printAge(Facts facts){
        System.out.println("age is " + facts.get("age") +", pass AgeRule");
    }

    @Priority
    public int getPriority(){
        return 0;
    }

}
