package com.example.ruleengine.loader.rulesgroup.domain;

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
@Table(name="rules_group_relation_table")
@Data
@NoArgsConstructor
@ToString
public class RulesGroupRelationTable implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rules_group_info_id")
    private Long rulesGroupInfoId;

    @Column(name = "dynamic_rule_id")
    private Long dynamicRuleId;

}
