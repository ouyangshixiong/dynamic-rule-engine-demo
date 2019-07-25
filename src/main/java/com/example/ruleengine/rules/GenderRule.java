package com.example.ruleengine.rules;

import org.jeasy.rules.annotation.*;
import org.jeasy.rules.api.Facts;

/**
 * @author alexouyang
 * @Date 2019-07-24
 */
@Rule
public class GenderRule {

    private StringBuilder sb;

    public GenderRule(StringBuilder sb) {
        this.sb = sb;
    }

    @Condition
    public boolean onlyMale(@Fact("gender") String gender ){
        return gender.equalsIgnoreCase("male")?true:false;
    }

    @Action
    public void printGender(Facts facts){
        sb.append("gender is " + facts.get("gender") +", pass GenderRule" + "\n");
    }

    @Priority
    public int getPriority(){
        return 5;
    }
}
