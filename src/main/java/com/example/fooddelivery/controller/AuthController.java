package com.example.fooddelivery.controller;


import com.example.fooddelivery.dto.CustomerDto;
import com.example.fooddelivery.mapper.CustomerMapper;
import com.example.fooddelivery.model.Customer;
import com.example.fooddelivery.payload.*;
import com.example.fooddelivery.security.UserDetailsServiceImpl;
import com.example.fooddelivery.service.customer.CustomerService;
import com.example.fooddelivery.util.TokenHandler;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static com.example.fooddelivery.cons.ResourcePath.AUTH;


@RestController
@RequestMapping(AUTH)
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsServiceImpl userDetailsService;
    private final TokenHandler tokenHandler;
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody @Valid final JwtRequest jwtRequest){
        // If the authentication process is successful,
        // we can get Users information such as email, password, authorities from an Authentication
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(jwtRequest.getEmail(), jwtRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserDetailsImpl base = (UserDetailsImpl) authentication.getPrincipal();
        //log.info("base {}", JSONUtil.toJSON(base));
        final UserDetails userDetails = userDetailsService.loadUserByUsername(jwtRequest.getEmail());
        List<String> roles = base.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());
        //
        final String token = tokenHandler.generateToken(authentication);

        log.info("authority {}", userDetails.getAuthorities());

        return ResponseEntity.ok().body(new JwtResponse(token, base.getUsername(), roles));
    }
    @PostMapping("/signup")
    public ResponseEntity<CustomerDto> signup(@RequestBody @Valid JwtSignUp jwtSignUp){
        final Customer customer = customerService.signup(jwtSignUp);

        return ResponseEntity.ok(customerMapper.toCustomerDto(customer));
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logout(){
        ResponseCookie cookie = tokenHandler.getCleanJwtCookie();
        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
                .body(new ResponseMsg("You've been signed out!"));
    }
}
