package com.kurdestan.lezzoo.modules.address;

import com.kurdestan.lezzoo.modules.order.Order;
import com.kurdestan.lezzoo.modules.supplier.Supplier;
import com.kurdestan.lezzoo.modules.user.AppUser;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

import java.util.List;

@Data
public class AddressDTO {

    @ApiModelProperty(required = true, hidden = false)
    private String title;

    @ApiModelProperty(required = true, hidden = false)
    private String value;

    @ApiModelProperty(required = true, hidden = false)
    private Point<G2D> location;

    @ApiModelProperty(required = true, hidden = false)
    private AppUser appUser;

    @ApiModelProperty(required = true, hidden = false)
    private Supplier supplier;

    @ApiModelProperty(required = false, hidden = true)
    private List<Order> orders;
}
