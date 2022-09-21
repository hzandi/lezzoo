package com.kurdestan.lezzoo.modules.order;

import com.kurdestan.lezzoo.modules.address.Address;
import com.kurdestan.lezzoo.modules.invoce.Invoice;
import com.kurdestan.lezzoo.modules.order_item.OrderItem;
import com.kurdestan.lezzoo.modules.supplier.Supplier;
import com.kurdestan.lezzoo.modules.user.AppUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class OrderDTO {

    @ApiModelProperty(required = true, hidden = false)
    private OrderStatus status;

    @ApiModelProperty(required = true, hidden = false)
    private List<OrderItem> orderItems;

    @ApiModelProperty(required = true, hidden = false)
    private Supplier supplier;

    @ApiModelProperty(required = true, hidden = false)
    private Address address;

    @ApiModelProperty(required = true, hidden = false)
    private AppUser appUser;

    @ApiModelProperty(required = true, hidden = false)
    private Invoice invoice;
}
