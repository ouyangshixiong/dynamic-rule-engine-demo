package com.example.ruleengine.rules;

import org.jeasy.rules.annotation.*;
import org.jeasy.rules.api.Facts;

/**
 * @author alexouyang
 * @Date 2019-07-24
 */
@Rule
public class GenderRule {

    @Condition
    public boolean onlyMale(@Fact("gender") String gender ){
        return gender.equalsIgnoreCase("male")?true:false;
    }

    @Action
    public void printGender(Facts facts){
        System.out.println("gender is " + facts.get("gender") +", pass GenderRule");
    }

    @Priority
    public int getPriority(){
        return 5;
    }
}
