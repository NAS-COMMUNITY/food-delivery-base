package com.example.fooddelivery.command;



import com.example.fooddelivery.model.Address;
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
    private Set<AddressCommand> addressCommands;
}
