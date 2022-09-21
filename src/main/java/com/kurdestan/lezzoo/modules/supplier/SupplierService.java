package com.kurdestan.lezzoo.modules.supplier;

import com.kurdestan.lezzoo.common.SearchCriteria;
import org.springframework.data.domain.Page;

import java.util.List;


public interface SupplierService {
    Supplier save(Supplier supplier);
    Supplier update(Supplier supplier);
    void delete(Long id);
    Supplier getById(Long id);
    Page<Supplier> paging(Integer page, Integer size);
    List<Supplier> search(List<SearchCriteria> searchCriteria);
}
