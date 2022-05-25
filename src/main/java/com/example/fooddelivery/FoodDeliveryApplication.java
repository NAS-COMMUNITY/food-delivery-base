package com.example.fooddelivery;

import com.example.fooddelivery.enums.FoodType;
import com.example.fooddelivery.enums.Status;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.Customer;
import com.example.fooddelivery.model.OrderEntity;
import com.example.fooddelivery.model.Product;
import com.example.fooddelivery.repository.AddressRepository;
import com.example.fooddelivery.repository.CustomerRepository;
import com.example.fooddelivery.repository.OrderRepository;
import com.example.fooddelivery.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
@EnableJpaAuditing
public class FoodDeliveryApplication{

    public static void main(String[] args) {
        SpringApplication.run(FoodDeliveryApplication.class, args);
    }

   /* @Override
    public void run(String... args) throws Exception {
        Set<Address> addresses = new HashSet<>();

        Address address = new Address();
        address.setCity("Oujda");
        address.setCountry("Maroc");
        address.setStreet("22 rue limoun");
        addresses.add(address);




        Customer customer = new Customer();
        customer.setFirstName("anas");
        customer.setLastName("abbal");
        customer.setEmail("anas@gmail.com");
        customer.setAddresses(addresses);
        address.setCustomer(customer);

        OrderEntity order = new OrderEntity();
        order.setCustomer(customer);
        order.setBillingAddress(address);
        order.setShippingAddress(address);
        order.setStatus(Status.REJECTED);

        Product product = new Product();
        product.setType(FoodType.TACOS);

        product.setOrder(order);
        //order.setType(FoodType.TACOS);
        //order.setPrice(BigDecimal.valueOf(141.343));

        Set<OrderEntity> orderEntities = new HashSet<>();
        orderEntities.add(order);
        //customer.setOrderEntities(orderEntities);
        customerRepository.save(customer);

        addressRepository.save(address);
        //orderRepository.save(order);
    }*/
}
