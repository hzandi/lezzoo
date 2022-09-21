package com.kurdestan.lezzoo.modules.order;

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
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    @Caching(evict = {
            @CacheEvict(value = "orderCache", allEntries = true),
    })
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "orderCache", allEntries = true),
    })
    public Order update(Order order) {
        Order lastSavedOrder = getById(order.getId());
        lastSavedOrder.setStatus(order.getStatus());
        lastSavedOrder.setOrderItems(order.getOrderItems());
        lastSavedOrder.setAddress(order.getAddress());
        lastSavedOrder.setSupplier(order.getSupplier());
        lastSavedOrder.setAppUser(order.getAppUser());
        return orderRepository.save(lastSavedOrder);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "orderCache", allEntries = true),
    })
    public void delete(Long id) {
        getById(id);
        orderRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "orderCache", key = "#id")
    public Order getById(Long id) {
        Optional<Order> optionalOrder = orderRepository.findById(id);
        if (optionalOrder.isEmpty()) {
            throw new NotFoundException("Order Not Found!");
        }
        return optionalOrder.get();
    }

    @Override
    public List<Order> getAllByAppUserId(Long appUserId) {
        return orderRepository.findAllByAppUser_Id(appUserId);
    }

    @Override
    public List<Order> getAllBySupplierId(Long supplierId) {
        return orderRepository.findAllBySupplier_Id(supplierId);
    }

    @Override
    public Page<Order> paging(Integer page, Integer size) {
        return orderRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
    }

    @Override
    public List<Order> search(List<SearchCriteria> searchCriteria) {
        SearchSpecification<Order> orderSpecification = new SearchSpecification<>();
        searchCriteria.forEach(criteria -> orderSpecification.add(criteria));
        return orderRepository.findAll(orderSpecification);
    }

}



