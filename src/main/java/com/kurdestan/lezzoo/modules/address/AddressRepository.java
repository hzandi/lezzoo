package com.kurdestan.lezzoo.modules.address;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AddressRepository extends
        PagingAndSortingRepository<Address, Long>,
        JpaSpecificationExecutor<Address> {

    Address findByTitle(String title);
    Page<Address> findAll(Pageable pageable);
    List<Address> findAll(Specification<Address> specification);
}