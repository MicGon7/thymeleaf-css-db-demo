package com.luv2code.springboot.thymeleafdemo.dao

import com.luv2code.springboot.thymeleafdemo.entity.Employee
import org.springframework.data.jpa.repository.JpaRepository


// Provides CRUD features automatically
interface EmployeeRepository : JpaRepository<Employee, Int> {

    // add a method to sort by last name
    // Spring Data JPA will parse the signature and define a method behind the scenes automatically
    fun findAllByOrderByLastNameAsc(): List<Employee>
    // search by name
    fun findByFirstNameContainsOrLastNameContainsAllIgnoreCase(name: String?, lName: String?): List<Employee?>
}


