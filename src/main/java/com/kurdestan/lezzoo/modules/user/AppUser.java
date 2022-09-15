package com.kurdestan.lezzoo.modules.user;

import com.kurdestan.lezzoo.common.BaseEntity;
import com.kurdestan.lezzoo.modules.address.Address;
import com.kurdestan.lezzoo.modules.order.Order;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Table(name = "tbl_users")
@Data
@Entity
public class AppUser extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "username")
    private String username;

    @Column(name = "phone")
    private String phone;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "appUser", cascade = CascadeType.ALL)
    private List<Order> orders;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "appUser", cascade = CascadeType.ALL)
    private List<Address> addressList;
}
