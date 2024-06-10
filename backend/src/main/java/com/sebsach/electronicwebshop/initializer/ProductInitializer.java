package com.sebsach.electronicwebshop.initializer;

import com.sebsach.electronicwebshop.product.ProductRequest;
import com.sebsach.electronicwebshop.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Component
@RequiredArgsConstructor
public class ProductInitializer implements ApplicationRunner {
    private final ProductService productService;
    private final ProductCategoryInitializer productCategoryInitializer;
    private final ProducerInitializer producerInitializer;

    private static final Logger logger = LoggerFactory.getLogger(ProductInitializer.class);

    @Override
    public void run(ApplicationArguments args) {
        producerInitializer.run();
        productCategoryInitializer.run();
        try (BufferedReader reader = new BufferedReader(new FileReader("./backend/src/main/resources/products.csv"))) {

            reader.readLine();

            String line;

            while ((line = reader.readLine()) != null) {
                String[] fields = line.split(";");

                if (fields.length >= 6) {
                    String name = fields[0];
                    double price = Double.parseDouble(fields[1]);
                    int quantity = Integer.parseInt(fields[2]);
                    String producer = fields[3];
                    String description = fields[4];
                    String category = fields[5];

                    ProductRequest newProduct = new ProductRequest(name, description, price, quantity, category, producer);
                    logger.info("Added product: {}", newProduct);
                    productService.add(newProduct);


                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
