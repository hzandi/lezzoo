package com.kurdestan.lezzoo.modules.invoce;

import com.kurdestan.lezzoo.common.BaseDTO;
import com.kurdestan.lezzoo.modules.order.Order;
import com.kurdestan.lezzoo.modules.user.AppUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;


@Data
public class InvoiceDTO extends BaseDTO {

    @ApiModelProperty(required = true, hidden = false)
    private BigDecimal totalPrice;

    @ApiModelProperty(required = true, hidden = false)
    private Order order;

    @ApiModelProperty(required = true, hidden = false)
    private AppUser appUser;
}
