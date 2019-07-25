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
 * 2019-07-25
 */
@Entity
@Table(name="dynamic_rule")
@Data
@NoArgsConstructor
@ToString
@Builder
public class DynamicRule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rule_name")
    private String ruleName;

    @Column(name = "rule_group_type")
    @Enumerated(EnumType.STRING)
    private RulesGroupType ruleGroupType;

    @Column(name = "content")
    private String content;
}
