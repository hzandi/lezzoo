package com.kurdestan.lezzoo.modules.order;

import com.kurdestan.lezzoo.common.SearchCriteria;
import org.springframework.data.domain.Page;

import java.util.List;


public interface OrderService {
    Order save(Order order);
    Order update(Order order);
    void delete(Long id);
    Order getById(Long id);
    List<Order> getAllByAppUserId(Long appUserId);
    List<Order> getAllBySupplierId(Long supplierId);
    Page<Order> paging(Integer page, Integer size);
    List<Order> search(List<SearchCriteria> searchCriteria);
}
