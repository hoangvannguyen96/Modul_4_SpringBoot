package service;

import model.Customer;

import java.util.List;

public interface ICustomer {
    List<Customer> findAll();

    Customer getCustomerById(int id);

    void add(Customer customer);

    void update(Customer customer);
    void remove(int id);
}
