package com.kurdestan.lezzoo.modules.supplier_category;

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
public class SupplierCategoryServiceImpl implements SupplierCategoryService {

    private final SupplierCategoryRepository supplierCategoryRepository;

    @Override
    @Caching(evict = {
            @CacheEvict(value = "supplierCategoryCache", allEntries = true),
    })
    public SupplierCategory save(SupplierCategory supplierCategory) {
        return supplierCategoryRepository.save(supplierCategory);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "supplierCategoryCache", allEntries = true),
    })
    public SupplierCategory update(SupplierCategory supplierCategory) {
        SupplierCategory lastSavedSupplierCategory = getById(supplierCategory.getId());
        lastSavedSupplierCategory.setTitle(supplierCategory.getTitle());
        lastSavedSupplierCategory.setImage(supplierCategory.getImage());
        return supplierCategoryRepository.save(lastSavedSupplierCategory);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "supplierCategoryCache", allEntries = true),
    })
    public void delete(Long id) {
        getById(id);
        supplierCategoryRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "supplierCategoryCache", key = "#id")
    public SupplierCategory getById(Long id) {
        Optional<SupplierCategory> optionalSupplierCategory = supplierCategoryRepository.findById(id);
        if (optionalSupplierCategory.isEmpty()) {
            throw new NotFoundException("SupplierCategory Not Found!");
        }
        return optionalSupplierCategory.get();
    }

    @Override
    @Cacheable(value = "supplierCategoryCache", key = "#title")
    public SupplierCategory getByTitle(String title) {
        Optional<SupplierCategory> optionalSupplierCategory = supplierCategoryRepository.findByTitle(title);
        if (optionalSupplierCategory.isEmpty()) {
            throw new NotFoundException("SupplierCategory Not Found!");
        }
        return optionalSupplierCategory.get();
    }

    @Override
    public List<SupplierCategory> getAllBySupplierId(Long supplierId) {
        return supplierCategoryRepository.findAllBySupplier_Id(supplierId);
    }

    @Override
    public Page<SupplierCategory> paging(Integer page, Integer size) {
        return supplierCategoryRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
    }

    @Override
    public List<SupplierCategory> search(List<SearchCriteria> searchCriteria) {
        SearchSpecification<SupplierCategory> supplierCategorySpecification = new SearchSpecification<>();
        searchCriteria.forEach(criteria -> supplierCategorySpecification.add(criteria));
        return supplierCategoryRepository.findAll(supplierCategorySpecification);
    }

}


