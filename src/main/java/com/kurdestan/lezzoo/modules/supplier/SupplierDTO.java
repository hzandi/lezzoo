package com.kurdestan.lezzoo.modules.supplier;

import com.kurdestan.lezzoo.modules.address.Address;
import com.kurdestan.lezzoo.modules.category.Category;
import com.kurdestan.lezzoo.modules.supplier_category.SupplierCategory;
import com.kurdestan.lezzoo.modules.order.Order;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;


@Data
public class SupplierDTO {

    @ApiModelProperty(required = true, hidden = false)
    private String title;

    @ApiModelProperty(required = true, hidden = false)
    private String image;

    @ApiModelProperty(required = true, hidden = false)
    private Address address;

    @ApiModelProperty(required = true, hidden = false)
    private Category category;

    @ApiModelProperty(required = false, hidden = true)
    private List<SupplierCategory> supplierCategories;

    @ApiModelProperty(required = false, hidden = true)
    private List<Order> orders;

}