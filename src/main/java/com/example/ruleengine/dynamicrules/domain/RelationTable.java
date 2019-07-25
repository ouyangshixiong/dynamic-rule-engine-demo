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
@Table(name="relation_table")
@Data
@NoArgsConstructor
@ToString
@Builder
public class RelationTable implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "business_rule_definition_id")
    private Long businessRuleDefinitionId;

    @Column(name = "dynamic_rule_id")
    private Long dynamicRuleId;

}
