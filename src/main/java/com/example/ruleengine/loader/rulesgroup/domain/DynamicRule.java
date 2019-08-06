package com.example.ruleengine.loader.rulesgroup.domain;

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
public class DynamicRule implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rule_name")
    private String ruleName;

    @Column(name = "content")
    private String content;
}
