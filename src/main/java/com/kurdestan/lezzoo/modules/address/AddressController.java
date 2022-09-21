package com.kurdestan.lezzoo.modules.address;

import com.kurdestan.lezzoo.common.PagingData;
import com.kurdestan.lezzoo.common.SearchCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/addresses/")
@AllArgsConstructor
public class AddressController {

    private final AddressService service;
    private AddressMapper mapper;

    @PostMapping("/v1")
    public ResponseEntity<?> save(@RequestBody AddressDTO addressDTO) {
        Address address = mapper.toAddress(addressDTO);
        service.save(address);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/v1")
    public ResponseEntity<?> update(@RequestBody AddressDTO addressDTO) {
        Address address = mapper.toAddress(addressDTO);
        service.update(address);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<AddressDTO> getById(@PathVariable Long id) {
        Address address = service.getById(id);
        AddressDTO addressDTO = mapper.toAddressDTO(address);
        return ResponseEntity.ok(addressDTO);
    }

    @DeleteMapping("/v1/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/paging/{page}/{size}")
    public ResponseEntity<PagingData<AddressDTO>> getAllPaging(@PathVariable Integer page, @PathVariable Integer size) {
        Page<Address> addressPage = service.paging(page, size);
        return getPagingDataResponseEntity(page, addressPage);
    }

    @PostMapping("/v1/search")
    public ResponseEntity<List<AddressDTO>> search(@RequestBody List<SearchCriteria> searchCriteria) {
        List<Address> addressList = service.search(searchCriteria);
        List<AddressDTO> addressDTOS = mapper.toAddressDTOList(addressList);
        return ResponseEntity.ok(addressDTOS);
    }

    private ResponseEntity<PagingData<AddressDTO>> getPagingDataResponseEntity(@PathVariable Integer page, Page<Address> addressPage) {
        int totalPage = addressPage.getTotalPages();
        List<Address> data = addressPage.getContent();
        List<AddressDTO> addressDTOS = mapper.toAddressDTOList(data);
        PagingData<AddressDTO> pagingData = new PagingData<>(totalPage, page, addressDTOS);
        return ResponseEntity.ok(pagingData);
    }

}



