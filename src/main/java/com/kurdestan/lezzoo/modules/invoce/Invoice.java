package com.kurdestan.lezzoo.modules.invoce;

import com.kurdestan.lezzoo.common.BaseEntity;
import com.kurdestan.lezzoo.modules.order.Order;
import com.kurdestan.lezzoo.modules.user.AppUser;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "tbl_invoices")
@Data
@Entity
public class Invoice extends BaseEntity {

    @NotNull
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

}
