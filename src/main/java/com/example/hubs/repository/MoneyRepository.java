package com.example.hubs.repository;

import com.example.hubs.entity.Money;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MoneyRepository extends JpaRepository<Money, Long> {

    Optional<Money> findByCustomerId(Long customerId);
}