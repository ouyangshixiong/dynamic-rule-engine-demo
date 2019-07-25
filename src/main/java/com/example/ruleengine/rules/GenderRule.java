package com.example.ruleengine.rules;

import org.jeasy.rules.annotation.*;
import org.jeasy.rules.api.Facts;

/**
 * @author alexouyang
 * 2019-07-24
 */
@Rule
public class GenderRule {

    @Condition
    public boolean onlyMale(@Fact("gender") String gender ){
        return gender.equalsIgnoreCase("male");
    }

    @Action
    public void printGender(Facts facts){
        StringBuilder sb = facts.get("sb");
        sb.append("gender is " + facts.get("gender") +", pass GenderRule" + "\n");
    }

    @Priority
    public int getPriority(){
        return 5;
    }
}
