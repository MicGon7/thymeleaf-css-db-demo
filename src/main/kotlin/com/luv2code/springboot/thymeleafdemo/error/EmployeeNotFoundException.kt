package com.luv2code.springboot.thymeleafdemo.error

class EmployeeNotFoundException(override val message: String?) : RuntimeException(message)
