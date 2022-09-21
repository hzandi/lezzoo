package com.kurdestan.lezzoo.modules.supplier_category;

import com.kurdestan.lezzoo.modules.item.Item;
import com.kurdestan.lezzoo.modules.supplier.Supplier;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class SupplierCategoryDTO {

    @ApiModelProperty(required = true, hidden = false)
    private String title;

    @ApiModelProperty(required = true, hidden = false)
    private String image;

    @ApiModelProperty(required = true, hidden = false)
    private List<Item> items;

    @ApiModelProperty(required = true, hidden = false)
    private Supplier supplier;
}
