package com.kurdestan.lezzoo.modules.order;

import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toOrder(OrderDTO orderDTO);
    OrderDTO toOrderDTO(Order order);
    List<Order> toOrderList(List<OrderDTO> orderDTOS);
    List<OrderDTO> toOrderDTOList(List<Order> orders);
}
