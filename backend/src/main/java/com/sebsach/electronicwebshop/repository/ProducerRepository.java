package com.sebsach.electronicwebshop.repository;

import com.sebsach.electronicwebshop.product.Producer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface ProducerRepository extends JpaRepository<Producer, Long> {
    Optional<Producer> findByName(String name);

}
