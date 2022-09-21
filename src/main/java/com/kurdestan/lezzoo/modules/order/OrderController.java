package com.kurdestan.lezzoo.modules.order;

import com.kurdestan.lezzoo.common.PagingData;
import com.kurdestan.lezzoo.common.SearchCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/orders/")
@AllArgsConstructor
public class OrderController {

    private final OrderService service;
    private OrderMapper mapper;

    @PostMapping("/v1")
    public ResponseEntity<?> save(@RequestBody OrderDTO orderDTO) {
        Order order = mapper.toOrder(orderDTO);
        service.save(order);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/v1")
    public ResponseEntity<?> update(@RequestBody OrderDTO orderDTO) {
        Order order = mapper.toOrder(orderDTO);
        service.update(order);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<OrderDTO> getById(@PathVariable Long id) {
        Order order = service.getById(id);
        OrderDTO orderDTO = mapper.toOrderDTO(order);
        return ResponseEntity.ok(orderDTO);
    }

    @DeleteMapping("/v1/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/paging/{page}/{size}")
    public ResponseEntity<PagingData<OrderDTO>> getAllPaging(@PathVariable Integer page, @PathVariable Integer size) {
        Page<Order> orderPage = service.paging(page, size);
        return getPagingDataResponseEntity(page, orderPage);
    }

    @PostMapping("/v1/search")
    public ResponseEntity<List<OrderDTO>> search(@RequestBody List<SearchCriteria> searchCriteria) {
        List<Order> orderList = service.search(searchCriteria);
        List<OrderDTO> orderDTOS = mapper.toOrderDTOList(orderList);
        return ResponseEntity.ok(orderDTOS);
    }

    private ResponseEntity<PagingData<OrderDTO>> getPagingDataResponseEntity(@PathVariable Integer page, Page<Order> orderPage) {
        int totalPage = orderPage.getTotalPages();
        List<Order> data = orderPage.getContent();
        List<OrderDTO> orderDTOS = mapper.toOrderDTOList(data);
        PagingData<OrderDTO> pagingData = new PagingData<>(totalPage, page, orderDTOS);
        return ResponseEntity.ok(pagingData);
    }

}





