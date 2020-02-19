package com.luv2code.springboot.thymeleafdemo.entity

import javax.persistence.*

// TODO: Figure out how to use kotlin jpa plugin to generate no args constructor
@Entity
@Table(name = "employee")
data class Employee(
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        var id: Int = 0,
        @Column(name = "first_name")
        var firstName: String = "",
        @Column(name = "last_name")
        var lastName: String = "",
        @Column(name = "email")
        var email: String = ""
)

