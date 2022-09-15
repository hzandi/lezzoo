package com.kurdestan.lezzoo.modules.address;

import com.kurdestan.lezzoo.common.BaseEntity;
import com.kurdestan.lezzoo.modules.order.Order;
import com.kurdestan.lezzoo.modules.user.AppUser;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "tbl_address")
@Entity
@Data
public class Address extends BaseEntity {

    @Column(name = "value")
    private String value;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "address", cascade = CascadeType.ALL)
    private List<Order> orders;

}
