package com.bigbell.springdemo.service;

import java.util.List;

import com.bigbell.springdemo.entity.Customer;

public interface CustomerService {
	
	public List<Customer> getCustomers();
	
	public Customer getCustomer(int id);

	public void saveCustomers(Customer customer);

	public void deleteCustomer(int id);

	public List<Customer> searchCustomers(String searchName);
}
