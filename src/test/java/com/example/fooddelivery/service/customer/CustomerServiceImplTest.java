package com.example.fooddelivery.service.customer;

import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.command.CustomerCommand;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.Customer;
import com.example.fooddelivery.repository.CustomerRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;


@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(MockitoExtension.class)
@Slf4j
class CustomerServiceImplTest {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private CustomerService customerService;

    private CustomerCommand customerCommand;
    private Customer customer;

    private AddressCommand addressCommand;

    private Set<AddressCommand> addressCommands;

    @Before
    public void setUp() throws Exception {
        addressCommand = new AddressCommand();
        addressCommand.setCity("oujda");
        addressCommand.setStreet("oujda");
        addressCommand.setCountry("Maroc");

        addressCommands = new HashSet<>();

        addressCommands.add(addressCommand);

        customerCommand = new CustomerCommand();
        customerCommand.setFirstName("anas");
        customerCommand.setLastName("abbal");
        customerCommand.setEmail("anas.abbal10@gmail.com");
        customerCommand.setAddressCommands(addressCommands);

        customer = Customer.createOne(customerCommand);

        customerRepository.save(customer);
    }

    @After
    public void tearDown() throws Exception {
        customerRepository.deleteAll();
    }
    @Test
    public void should_can_i_create_customer(){

        Mockito.lenient()
                .when(customerService.createCustomer(any(CustomerCommand.class)))
                .thenReturn(customer);
        assertThat(customer.getEmail()).isEqualTo("anas.abbal10@gmail.com");
    }
}