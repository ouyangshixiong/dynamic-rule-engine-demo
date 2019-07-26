package com.example.ruleengine.dynamicrules.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author alexouyang
 * @Date 2019-07-26
 */
@Entity
@Table(name="chain_rules_group_relation_table")
@Data
@NoArgsConstructor
@ToString
public class ChainRulesGroupRelationTable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chain_info_id")
    private Long chainInfoId;

    @Column(name = "rules_group_info_id")
    private Long rulesGroupInfoId;

    @Column(name = "begin_point")
    private Boolean beginPoint;

    @Column(name = "end_point")
    private Boolean endPoint;

    @Column(name = "next_rules_group_id")
    private Long nextRulesGroupId;
}
