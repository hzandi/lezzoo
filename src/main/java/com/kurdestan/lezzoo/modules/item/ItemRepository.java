package com.kurdestan.lezzoo.modules.item;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ItemRepository extends
        PagingAndSortingRepository<Item, Long>,
        JpaSpecificationExecutor<Item> {

    Item findByName(String name);
    Page<Item> findAll(Pageable pageable);
    List<Item> findAll(Specification<Item> specification);
}