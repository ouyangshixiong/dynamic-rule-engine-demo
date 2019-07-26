package com.example.ruleengine.dynamicrules.domain;

import com.example.ruleengine.dynamicrules.RulesGroupType;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

/**
 * @author alexouyang
 * @Date 2019-07-25
 */
@Entity
@Table(name="business_rule_definition")
@Data
@NoArgsConstructor
@ToString
public class BusinessRuleDefinition implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "business_rule_name")
    private String businessRuleName;

    @Column(name = "business_rule_desc")
    private String businessRuleDesc;

}
