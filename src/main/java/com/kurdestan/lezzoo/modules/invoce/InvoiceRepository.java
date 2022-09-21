package com.kurdestan.lezzoo.modules.invoce;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface InvoiceRepository extends
        PagingAndSortingRepository<Invoice, Long>,
        JpaSpecificationExecutor<Invoice> {

    List<Invoice> findAllByOrder_Id(Long orderId);
    List<Invoice> findAllByAppUser_Id(Long appUserId);
    Page<Invoice> findAll(Pageable pageable);
    List<Invoice> findAll(Specification<Invoice> specification);
}