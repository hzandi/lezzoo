package com.kurdestan.lezzoo.modules.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends
        PagingAndSortingRepository<Order, Long>,
        JpaSpecificationExecutor<Order> {

    List<Order> findAllByAppUser_Id(Long appUserId);
    List<Order> findAllBySupplier_Id(Long supplierId);
    Page<Order> findAll(Pageable pageable);
    List<Order> findAll(Specification<Order> specification);
}