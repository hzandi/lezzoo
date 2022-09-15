package com.kurdestan.lezzoo.modules.order_item;

import com.kurdestan.lezzoo.common.BaseEntity;
import com.kurdestan.lezzoo.modules.item.Item;
import com.kurdestan.lezzoo.modules.order.Order;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "tbl_order_items")
@Data
@Entity
public class OrderItem extends BaseEntity {

    @Column(name = "count")
    private Integer count;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;
}
