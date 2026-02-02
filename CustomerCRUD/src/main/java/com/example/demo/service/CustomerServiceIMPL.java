package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exception.InvalidId;
import com.example.demo.exception.InvalidMobileNumber;
import com.example.demo.exception.InvalidName;
import com.example.demo.exception.InvalidTotal;
import com.example.demo.model.Customer;
import com.example.demo.repository.CustomerRepository;

@Service

public class CustomerServiceIMPL implements CustomerService {
	@Autowired
	private CustomerRepository cr;

	@Override
	public void add(Customer customer) {
		// TODO Auto-generated method stub
		Integer id = customer.getId();

		if (id > 0) {
			if (cr.existsById(id)) {
				throw new InvalidId("Duplicate id");

			}

		} else {
			throw new InvalidId("INVALID id NUMBER");

		}

		String name = customer.getName().trim();
		List<Customer> list = findByname(name);
		if (!list.isEmpty()) {
			throw new InvalidName(" Name is Already exits");

		}

		String mob = customer.getMob().trim();
		if (mob.startsWith("+91"))
			mob = mob.substring(3);

		if (mob.length() == 10) {
			if (mob.charAt(0) == '0' || mob.charAt(0) == '1' || mob.charAt(0) == '2' || mob.charAt(0) == '3'
					|| mob.charAt(0) == 4)
				throw new InvalidMobileNumber("Invalid Mobile Number");
			for (int i = 0; i < mob.length(); i++) {
				if (!Character.isDigit(mob.charAt(i)))
					throw new InvalidMobileNumber("INVALID MOBILE NUMBER");
			}
		} else
			throw new InvalidMobileNumber("INVALID MOBILE NUMBER");

		Float total = customer.getTotal();
		if (total <= 0)
			throw new InvalidTotal("INVALID TOTAL AMOUNT");

		cr.save(customer);

	}

	@Override
	public List<Customer> display() {
		// TODO Auto-generated method stub
		return cr.findAll();// select * from customer;
	}

	@Override
	public Customer delete(Integer id) {
		// TODO Auto-generated method stub
		// search
		if (cr.findById(id).isPresent()) {
			Customer temp = cr.findById(id).get();
			cr.deleteById(id);// delete
			return temp;
		}

		return null;
	}

	@Override
	public void update(Customer customer, Integer id) {
		// TODO Auto-generated method stub
		customer.setId(id);
		cr.save(customer);

	}

	@Override
	public Customer search(Integer id) {
		// TODO Auto-generated method stub
		if (cr.findById(id).isPresent()) {
			Customer temp = cr.findById(id).get();

			return temp;
		}

		return null;
	}

	@Override
	public void addAll(List<Customer> list) {

		cr.saveAll(list);

	}

	@Override
	public List findByAddress(String address) {

		return cr.findByAddress(address);
	}

	@Override
	public List<Customer> findByname(String name) {
		// TODO Auto-generated method stub
		return cr.findByName(name);
	}

	@Override
	public List<Customer> findByTotal(Float total) {
		// TODO Auto-generated method stub
		return cr.findByTotal(total);
	}

}
