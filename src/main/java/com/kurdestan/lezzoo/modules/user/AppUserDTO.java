package com.kurdestan.lezzoo.modules.user;

import com.kurdestan.lezzoo.modules.address.Address;
import com.kurdestan.lezzoo.modules.order.Order;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import java.util.List;

public class AppUserDTO {

    @ApiModelProperty(required = true, hidden = false)
    private String name;

    @ApiModelProperty(required = true, hidden = false)
    private String email;

    @ApiModelProperty(required = true, hidden = false)
    private String username;

    @ApiModelProperty(required = false, hidden = true)
    private String password;

    @ApiModelProperty(required = true, hidden = false)
    private String phone;

    @ApiModelProperty(required = false, hidden = true)
    private List<Order> orders;

    @ApiModelProperty(required = false, hidden = true)
    private List<Address> addresses;
}
