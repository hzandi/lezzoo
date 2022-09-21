package com.kurdestan.lezzoo.modules.address;

import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toAddress(AddressDTO addressDTO);
    AddressDTO toAddressDTO(Address address);
    List<Address> toAddressList(List<AddressDTO> addressDTOS);
    List<AddressDTO> toAddressDTOList(List<Address> categories);
}