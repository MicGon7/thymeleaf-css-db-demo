package com.luv2code.springboot.thymeleafdemo.service

import com.luv2code.springboot.thymeleafdemo.entity.Employee


interface EmployeeService {
    fun findAll(): List<Employee>?

    fun findById(id: Int): Employee?

    fun save(employee: Employee)

    fun deleteById(id: Int)

    fun searchBy(theName: String?): List<Employee?>?

}