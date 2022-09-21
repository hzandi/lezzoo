package com.kurdestan.lezzoo.modules.category;

import com.kurdestan.lezzoo.common.BaseDTO;
import com.kurdestan.lezzoo.modules.supplier.Supplier;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO extends BaseDTO {

    @ApiModelProperty(required = true, hidden = false)
    private String title;

    @ApiModelProperty(required = true, hidden = false)
    private String image;

    @ApiModelProperty(required = true, hidden = false)
    private List<Supplier> suppliers;
}
