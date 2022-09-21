package com.kurdestan.lezzoo.modules.supplier;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SupplierRepository extends
        PagingAndSortingRepository<Supplier, Long>,
        JpaSpecificationExecutor<Supplier> {

    Supplier findByTitle(String title);
    Page<Supplier> findAll(Pageable pageable);
    List<Supplier> findAll(Specification<Supplier> specification);
}