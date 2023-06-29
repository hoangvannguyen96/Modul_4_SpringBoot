package service;

import model.Customer;

import java.util.ArrayList;
import java.util.List;

public class CustomerService implements ICustomer {
    List<Customer> customers = new ArrayList<Customer>();
    public static Integer maxId = 1;

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public Customer getCustomerById(int id) {
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public void add(Customer customer) {
        customer.setId(maxId);
        customers.add(customer);
        maxId++;
    }

    @Override
    public void update(Customer customer) {
        int index = -1;
        for (int i = 0; i < customers.size(); i++) {
            if (customers.get(i).getId() == customer.getId()) {
                index = i;
            }
        }
        if (index > -1) {
            customers.set(index, customer);
        }
    }

    @Override
    public void remove(int id) {
        Customer customer = getCustomerById(id);
        customers.remove(id);
    }
}
