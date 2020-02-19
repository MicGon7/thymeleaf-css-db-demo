package com.luv2code.springboot.thymeleafdemo.controller

import com.luv2code.springboot.thymeleafdemo.entity.Employee
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.*


@Controller
@RequestMapping("/employees")
class EmployeeController(theEmployeeService: EmployeeService) {
    val employeeService = theEmployeeService

    // add mapping for "/list"
    @GetMapping("/list")
    fun listEmployees(model: Model): String {
        val employees = employeeService.findAll()
        model.addAttribute("employees", employees)

        return "employees/list-employees"
    }

    @GetMapping("/showFormForAdd")
    fun showFormForAdd(model: Model): String {

        /// create model attribute to bind the form data
        val employee = Employee()

        // tymeleaf will access the employee attribute to bind
        model.addAttribute("employee", employee)

        return "employees/employee-form"
    }

    @GetMapping("/showFormForUpdate")
    fun showFormForUpdate(@RequestParam("employeeId") id: Int, model: Model): String {
        // get the employee fro the service (request param passed in from embedded link using employeeId)
        val employee = employeeService.findById(id)

        // set the employee as a model attribute to pre-populate the form
        model.addAttribute("employee", employee)

        // send over to our form
        return "employees/employee-form"
    }
    @GetMapping("/delete")
    fun delete(@RequestParam("employeeId") id: Int): String {
        employeeService.deleteById(id)
        return "redirect:/employees/list"
    }

    @GetMapping("/search")
    fun searchByName(@RequestParam("employeeName") name: String?,
                     theModel: Model): String? {
        val employees = employeeService.searchBy(name)
        // add to the spring model
        theModel.addAttribute("employees", employees)
        // send to /employees/list
        return "/employees/list-employees"
    }

    @PostMapping("/save")
    fun saveEmployee(@ModelAttribute("employee") employee: Employee): String {

        // save the employee
        employeeService.save(employee)

        // use a redirect to prevent duplicate submissions (Post/Redirect/Get Pattern)
        return "redirect:/employees/list"
    }
}