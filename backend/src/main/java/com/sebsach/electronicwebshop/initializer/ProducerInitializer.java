package com.sebsach.electronicwebshop.initializer;

import com.sebsach.electronicwebshop.product.Producer;
import com.sebsach.electronicwebshop.repository.ProducerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
@RequiredArgsConstructor
public class ProducerInitializer {

    private final ProducerRepository producerRepository;

    public void run(){

        List<String> producers = List.of("APPLE", "HP", "SAMSUNG", "SONY");
        for (String producerName : producers){
            if (producerRepository.findByName(producerName).isEmpty()) {
                Producer producer = new Producer();
                producer.setName(producerName);
                producerRepository.save(producer);
            }
        }

    }
}