package com.kurdestan.lezzoo.modules.address;

import com.kurdestan.lezzoo.common.SearchCriteria;
import com.kurdestan.lezzoo.common.SearchSpecification;
import com.kurdestan.lezzoo.common.exception.NotFoundException;
import com.kurdestan.lezzoo.modules.category.Category;
import com.kurdestan.lezzoo.modules.category.CategoryRepository;
import com.kurdestan.lezzoo.modules.category.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Override
    @Caching(evict = {
            @CacheEvict(value = "addressCache", allEntries = true),
    })
    public Address save(Address address) {
        return addressRepository.save(address);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "addressCache", allEntries = true),
    })
    public Address update(Address address) {
        Address lastSavedAddress = getById(address.getId());
        lastSavedAddress.setTitle(address.getTitle());
        lastSavedAddress.setValue(address.getValue());
        lastSavedAddress.setLocation(address.getLocation());
        lastSavedAddress.setAppUser(address.getAppUser());
        lastSavedAddress.setSupplier(address.getSupplier());
        return addressRepository.save(lastSavedAddress);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "addressCache", allEntries = true),
    })
    public void delete(Long id) {
        getById(id);
        addressRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "addressCache", key = "#id")
    public Address getById(Long id) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isEmpty()) {
            throw new NotFoundException("Address Not Found!");
        }
        return optionalAddress.get();
    }

    @Override
    public Page<Address> paging(Integer page, Integer size) {
        return addressRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
    }

    @Override
    public List<Address> search(List<SearchCriteria> searchCriteria) {
        SearchSpecification<Address> addressSpecification = new SearchSpecification<>();
        searchCriteria.forEach(criteria -> addressSpecification.add(criteria));
        return addressRepository.findAll(addressSpecification);
    }

}


