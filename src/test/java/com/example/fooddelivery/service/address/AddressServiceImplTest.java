package com.example.fooddelivery.service.address;

import com.example.fooddelivery.command.AddressCommand;
import com.example.fooddelivery.dto.AddressDto;
import com.example.fooddelivery.mapper.AddressMapper;
import com.example.fooddelivery.model.Address;
import com.example.fooddelivery.repository.AddressRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.assertj.core.api.Java6Assertions.assertThat;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;



@RunWith(SpringJUnit4ClassRunner.class)
@ExtendWith(MockitoExtension.class)
@Slf4j
class AddressServiceImplTest {

    @Mock
    private AddressRepository addressRepository;

    @Mock
    private AddressService addressService;

    @Mock
    private AddressMapper addressMapper;

    @Test
    public void should_i_get_all_address(){
        PageRequest pageRequest = PageRequest.of(1, 10);
        List<Address> addresses = Arrays.asList(new Address("oujda", "oujda", "maroc"));

        Page<Address> addresses1 = new PageImpl<>(addresses);

        Page<AddressDto> addressDtos = addresses1.map(addressMapper::toAddressDto);
        Mockito.lenient().when(addressService.getAll(pageRequest)).thenReturn(addressDtos);

        assertThat(addressDtos).hasSize(1);
    }
    @Test
    public void should_can_i_update_address(){
        Address address = new Address();
        address.setId("add");
        address.setCity("oujda");
        address.setStreet("oujda");
        address.setCountry("Maroc");

        log.info(address.getId());

        AddressCommand addressCommand = new AddressCommand();
        addressCommand.setCity("oujda");
        addressCommand.setStreet("Oujda");
        addressCommand.setCountry("UK");
        //address.update(addressCommand);
        addressService.update(  address.getId(),  addressCommand);

        assertThat(address.getCountry()).isEqualTo("UK");
    }
    @Test
    public void can_i_delete_address(){
        Address address = new Address();
        address.setId("add");
        address.setCity("oujda");
        address.setStreet("oujda");
        address.setCountry("Maroc");

        addressService.deleteAddress(address.getId());

        //Mockito.lenient().when(addressService.deleteAddress(address.getId())).thenReturn(address);
        assertThat(address.getDeleted()).isEqualTo(true);
    }
}