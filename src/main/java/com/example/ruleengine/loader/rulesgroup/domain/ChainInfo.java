package com.example.ruleengine.loader.rulesgroup.domain;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

/**
 * @author alexouyang
 * @Date 2019-07-26
 */
@Entity
@Table(name="chain_info")
@Data
@NoArgsConstructor
@ToString
public class ChainInfo {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "chain_name")
    private String chainName;

    @Column(name = "chain_desc")
    private String chainDesc;
}
