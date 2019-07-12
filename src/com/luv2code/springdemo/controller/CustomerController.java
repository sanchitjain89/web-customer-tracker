package com.luv2code.springdemo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springdemo.entity.Customer;
import com.luv2code.springdemo.service.CustomerService;

@Controller
@RequestMapping("/customer")
public class CustomerController {
	
	//inject customer service
	@Autowired
	private CustomerService customerService;
	

	@RequestMapping("/list")
	public String listCustomers(Model theModel) {
		
		//get customers from service
		List<Customer> theCustomers = customerService.getCutomers();
		
		theModel.addAttribute("customers", theCustomers);
		
		return "list-customers";
	}
	
	@GetMapping("/showFormForAdd")
	public String showFromForAdd(Model model) {
		Customer theCustomer = new Customer();
		
		model.addAttribute("customer", theCustomer);
		
		return "customer-form";
	}
	
	@PostMapping("/saveCustomer")
	public String saveCustomer(@ModelAttribute("customer") Customer theCustomer) {
		customerService.saveCustomer(theCustomer);
		return "redirect:/customer/list";
	}
	
	
	@GetMapping("/showFormUpdate")
	public String showFormForUpdating(@RequestParam("customerId") int theId,
			Model theModel) {
		//get the customer from service
		System.out.println(theId);
		Customer theCustomer = customerService.getCustomer(theId);
		System.out.println(theCustomer);
		
		//set customer as a model attribute to pre-populate the form
		theModel.addAttribute("customer", theCustomer);
		
		//send over to our form
		return "customer-form";
	}
	
	@GetMapping("/delete")
	public String deleteCustomer(@RequestParam("customerId") int theId,
				Model theModel) {
		
		customerService.delete(theId);
		
		return "redirect:/customer/list";
		
	}
	
}
