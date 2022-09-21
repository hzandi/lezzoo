package com.kurdestan.lezzoo.modules.invoce;

import com.kurdestan.lezzoo.common.BaseEntity;
import com.kurdestan.lezzoo.modules.order.Order;
import com.kurdestan.lezzoo.modules.user.AppUser;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Table(name = "tbl_invoices")
@Data
@Entity
public class Invoice extends BaseEntity {

    @Column(name = "total_price")
    private BigDecimal totalPrice;

    @NotNull
    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

}
