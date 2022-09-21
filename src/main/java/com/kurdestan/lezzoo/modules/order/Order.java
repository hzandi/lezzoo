package com.kurdestan.lezzoo.modules.order;

import com.kurdestan.lezzoo.common.BaseEntity;
import com.kurdestan.lezzoo.modules.address.Address;
import com.kurdestan.lezzoo.modules.invoce.Invoice;
import com.kurdestan.lezzoo.modules.order_item.OrderItem;
import com.kurdestan.lezzoo.modules.supplier.Supplier;
import com.kurdestan.lezzoo.modules.user.AppUser;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Table(name = "tbl_orders")
@Data
@Entity
public class Order extends BaseEntity {

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private Invoice invoice;

}
