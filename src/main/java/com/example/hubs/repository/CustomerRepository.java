package com.example.hubs.repository;

import com.example.hubs.entity.Customer;
import com.example.hubs.model.CustomerModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Query(value = "select c from Customer c where (c.id is null or c.id = :#{#model.id})" +
            " and (c.name is null or c.name = :#{#model.name})" +
            " and (c.surname is null or c.surname = :#{#model.surname})" +
            " and (c.username is null or c.username = :#{#model.username})" +
            " and (c.role is null or c.role = :#{#model.role})")
    List<Customer> findCustomersByModel(CustomerModel model);

    Optional<Customer> findCustomerByUsername(String username);
}
