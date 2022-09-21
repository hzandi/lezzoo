package com.kurdestan.lezzoo.modules.supplier_category;

import com.kurdestan.lezzoo.common.SearchCriteria;
import org.springframework.data.domain.Page;

import java.util.List;


public interface SupplierCategoryService {
    SupplierCategory save(SupplierCategory supplierCategory);
    SupplierCategory update(SupplierCategory supplierCategory);
    void delete(Long id);
    SupplierCategory getById(Long id);
    SupplierCategory getByTitle(String title);
    List<SupplierCategory> getAllBySupplierId(Long supplierId);
    Page<SupplierCategory> paging(Integer page, Integer size);
    List<SupplierCategory> search(List<SearchCriteria> searchCriteria);
}