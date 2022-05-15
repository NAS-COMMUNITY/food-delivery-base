package com.example.fooddelivery.command;



import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class CustomerCommand {

    private String firstName;
    private String lastName;
    private String email;
    private Set<AddressCommand> addressCommands = new HashSet<>();
    private Set<String> orderEntities;
}
