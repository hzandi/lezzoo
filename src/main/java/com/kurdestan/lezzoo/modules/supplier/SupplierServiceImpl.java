package com.kurdestan.lezzoo.modules.supplier;

import com.kurdestan.lezzoo.common.SearchCriteria;
import com.kurdestan.lezzoo.common.SearchSpecification;
import com.kurdestan.lezzoo.common.exception.NotFoundException;
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
public class SupplierServiceImpl implements SupplierService {

    private final SupplierRepository supplierRepository;

    @Override
    @Caching(evict = {
            @CacheEvict(value = "supplierCache", allEntries = true),
    })
    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "supplierCache", allEntries = true),
    })
    public Supplier update(Supplier supplier) {
        Supplier lastSavedSupplier = getById(supplier.getId());
        lastSavedSupplier.setTitle(supplier.getTitle());
        lastSavedSupplier.setImage(supplier.getImage());
        return supplierRepository.save(lastSavedSupplier);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "supplierCache", allEntries = true),
    })
    public void delete(Long id) {
        getById(id);
        supplierRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "supplierCache", key = "#id")
    public Supplier getById(Long id) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isEmpty()) {
            throw new NotFoundException("Supplier Not Found!");
        }
        return optionalSupplier.get();
    }

    @Override
    public Page<Supplier> paging(Integer page, Integer size) {
        return supplierRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
    }

    @Override
    public List<Supplier> search(List<SearchCriteria> searchCriteria) {
        SearchSpecification<Supplier> supplierSpecification = new SearchSpecification<>();
        searchCriteria.forEach(criteria -> supplierSpecification.add(criteria));
        return supplierRepository.findAll(supplierSpecification);
    }

}


