package com.kurdestan.lezzoo.modules.supplier_category;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface SupplierCategoryRepository extends
        PagingAndSortingRepository<SupplierCategory, Long>,
        JpaSpecificationExecutor<SupplierCategory> {

    Optional<SupplierCategory> findByTitle(String title);
    List<SupplierCategory> findAllBySupplier_Id(Long supplierId);
    Page<SupplierCategory> findAll(Pageable pageable);
    List<SupplierCategory> findAll(Specification<SupplierCategory> specification);
}