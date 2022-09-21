package com.kurdestan.lezzoo.modules.address;

import com.kurdestan.lezzoo.common.SearchCriteria;
import org.springframework.data.domain.Page;

import java.util.List;

public interface AddressService {
    Address save(Address address);
    Address update(Address address);
    void delete(Long id);
    Address getById(Long id);
    Page<Address> paging(Integer page, Integer size);
    List<Address> search(List<SearchCriteria> searchCriteria);
}
