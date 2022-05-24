package com.example.fooddelivery.command;




import com.example.fooddelivery.enums.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CustomerCommand {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    private Set<AddressCommand> addressCommands;
}
