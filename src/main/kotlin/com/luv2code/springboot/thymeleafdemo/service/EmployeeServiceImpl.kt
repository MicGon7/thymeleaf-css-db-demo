package com.luv2code.springboot.thymeleafdemo.service

import com.luv2code.springboot.thymeleafdemo.dao.EmployeeRepository
import com.luv2code.springboot.thymeleafdemo.entity.Employee
import com.luv2code.springboot.thymeleafdemo.error.EmployeeNotFoundException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.CacheConfig
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service


@Service // service is a specialize @component that will also scan the class path for required classes
@CacheConfig(cacheNames = ["employees"])
class EmployeeServiceImpl(val employeeRepository: EmployeeRepository) : EmployeeService {

    @Autowired
    val cacheManager: CacheManager? = null

    // Removed @Transactional since JpaRepository provides this functionality automatically
    @Override
    @Cacheable
    override fun findAll(): List<Employee>? {
        return employeeRepository.findAllByOrderByLastNameAsc()
    }

    @Override
    override fun findById(id: Int): Employee? {
        if (employeeRepository.findById(id).isPresent) {
            return employeeRepository.findById(id).get() // throws no such element exception
        } else {
            throw EmployeeNotFoundException("Employee id $id not found")
        }
    }

    @Override
    @CacheEvict(allEntries = true)
    override fun save(employee: Employee) {
        employeeRepository.save(employee)
    }

    @Override
    @CacheEvict(allEntries = true)
    override fun deleteById(id: Int) {
        employeeRepository.deleteById(id)
    }

    override fun searchBy(theName: String?): List<Employee?>? {
        return if (theName != null && theName.trim { it <= ' ' }.isNotEmpty()) {
            employeeRepository.findByFirstNameContainsOrLastNameContainsAllIgnoreCase(theName, theName)
        } else {
            findAll()
        }
    }
}