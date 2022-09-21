package com.kurdestan.lezzoo.modules.item;

import com.kurdestan.lezzoo.modules.supplier_category.SupplierCategory;
import com.kurdestan.lezzoo.modules.order_item.OrderItem;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ItemDTO {

    @ApiModelProperty(required = true, hidden = false)
    private String name;

    @ApiModelProperty(required = true, hidden = false)
    private String price;

    @ApiModelProperty(required = true, hidden = false)
    private String image;

    @ApiModelProperty(required = true, hidden = false)
    private SupplierCategory supplierCategory;

    @ApiModelProperty(required = false, hidden = true)
    private List<OrderItem> orderItems;

}
