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
@Table(name="rules_group_info")
@Data
@NoArgsConstructor
@ToString
public class RulesGroupInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rule_group_name")
    private String ruleGroupName;

    @Column(name = "rule_group_desc")
    private String ruleGroupDesc;

    @Column(name = "rule_group_type")
    @Enumerated(EnumType.STRING)
    private RulesGroupType ruleGroupType;
}
