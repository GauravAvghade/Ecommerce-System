package com.example.ecommerce.service;

import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository; // Import your ProductRepository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order createOrder(Order order) {
        List<Product> managedProducts = order.getProductList().stream()
                .map(product -> productRepository.findById(product.getProductId()).orElse(null)) // Find the product by ID
                .collect(Collectors.toList());

        order.setProductList(managedProducts);
        return orderRepository.save(order); // Now save the order
    }
}
