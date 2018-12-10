package com.niallmcq.hello.world.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "User")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // JPA generated Id may be overwritten by Liquibase
    @Column(name = "Id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "Forename", nullable = false)
    private String forename;

    @Column(name = "Surname", nullable = false)
    private String surname;
}
