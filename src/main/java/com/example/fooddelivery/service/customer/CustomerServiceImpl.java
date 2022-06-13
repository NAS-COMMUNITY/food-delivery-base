package com.example.fooddelivery.service.customer;

import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.command.CustomerCommand;
import com.example.fooddelivery.dto.CustomerDto;
import com.example.fooddelivery.enums.Role;
import com.example.fooddelivery.exception.BusinessException;
import com.example.fooddelivery.exception.ExceptionFactory;
import com.example.fooddelivery.mapper.CustomerMapper;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.model.Customer;
import com.example.fooddelivery.payload.JwtSignUp;
import com.example.fooddelivery.repository.AddressRepository;
import com.example.fooddelivery.repository.CustomerRepository;
import com.example.fooddelivery.util.JSONUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
@Slf4j
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;

    private final PasswordEncoder passwordEncoder;
    private final CustomerMapper customerMapper;

    private final JavaMailSender mailSender;

    @Override
    public Page<CustomerDto> getAll(Pageable pageable){
        Page<Customer> customers = customerRepository.findAll(pageable);

        return customers.map(customerMapper::toCustomerDto);
    }

    @Transactional
    @Override
    public Address addAddressToCustomer(String customerId, AddressCommand addressCommand) {
        final Customer customer = findById(customerId);
        log.info("Begin creating and adding address with payload {} to customer with id {}", JSONUtil.toJSON(addressCommand), customerId);

        final Address address = addressRepository.save(customer.addAddress(addressCommand));
        log.info("New address has been added successfully to customer with id {}", customerId);

        return address;
    }
    @Override
    @Transactional
    public Customer createCustomer(final CustomerCommand customerCommand) {
        log.info("Begin creating customer with payload {}", JSONUtil.toJSON(customerCommand));
        final Customer customer = customerRepository.save(Customer.createOne(customerCommand));

        log.info("Creating Customer with payload {} successfully", JSONUtil.toJSON(customer));
        return customerRepository.save(customer);
    }
    @Override
    public Customer findById(String customerId) {
        log.info("Begin fetching customer with id {}", customerId);
        Customer customer = customerRepository.findById(customerId).orElseThrow(() ->
                new BusinessException(ExceptionFactory.INVALID_PAYLOAD.get()));

        log.info("Customer with id {} fetched successfully", customerId);

        return customer;
    }

    @Override
    public Customer update(String customerId, CustomerCommand customerCommand) {
        log.info("Begin updating customer with id {}", customerId);

        final Customer customer = findById(customerId);
        customer.update(customerCommand);

        log.info("updating customer with id {} successfully", customer.getId());

        return customer;
    }

    @Override
    public Customer deleteCustomer(String customerId) {
        log.info("Begin deleting customer with id {}", customerId);

        final Customer customer = findById(customerId);

        customer.delete();
        log.info("customer with id {} dleted successfully", customer.getId());

        return customerRepository.save(customer);
    }

    @Override
    public Customer signup(JwtSignUp jwtSignUp) {
        Boolean exist = customerRepository.selectExistsEmail(jwtSignUp.getEmail());

        if(exist){
            throw new BusinessException(ExceptionFactory.EMAIL_ALREADY_EXIST.get());
        }

        final Customer customer = new Customer();
        customer.setLastName(jwtSignUp.getLastName());
        customer.setFirstName(jwtSignUp.getFirstName());
        customer.setEmail(jwtSignUp.getEmail());
        customer.setPassword(passwordEncoder.encode(jwtSignUp.getPassword()));
        customer.setRole(Role.USER);
        customer.setAddresses(null);

        return customerRepository.save(customer);
    }
    /*public void sendVerificationEmail(@NonNull Customer customer, String siteURL) throws MessagingException, UnsupportedEncodingException {
        String toAddress = customer.getEmail();
        String fromAddress = "anas.abbal10@gmail.com";
        String senderName = "Xhub Code Kraken";
        String subject = "Please verify your registration";
        String content = "Dear [[name]],<br>"
                + "Please click the link below to verify your registration:<br>"
                + "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>"
                + "Thank you,<br>"
                + "Xhub.";

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message);
        helper.setFrom(fromAddress, senderName);
        helper.setTo(toAddress);
        helper.setSubject(subject);

        content = content.replace("[[name]]", customer.getFirstName());
        String verifyURL = siteURL + "/verify?code=" + customer.getVerificationCode();

        content = content.replace("[[URL]]", verifyURL);
        helper.setText(content, true);

        mailSender.send(message);
    }*/
    @Override
    public Customer changePasswordUser(String customerId, String newPassword) {
        log.info("Begin fetching customer with id {}", customerId);
        final Customer customer1 = findById(customerId);

        customer1.setPassword(newPassword);

        log.info("Changing password with new one {}", newPassword);

        return customer1;
    }

    @Override
    public Optional<Customer> findByEmail(String email) {
        return customerRepository.findByEmail(email);
    }
}
