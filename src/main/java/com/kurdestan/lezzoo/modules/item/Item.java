package com.kurdestan.lezzoo.modules.item;

import com.kurdestan.lezzoo.common.BaseEntity;
import com.kurdestan.lezzoo.modules.order_item.OrderItem;
import com.kurdestan.lezzoo.modules.category_supplier.CategorySupplier;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "tbl_items")
@Data
@Entity
public class Item extends BaseEntity {

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private String price;

    @Column(name = "image")
    private String image;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "supplier_category_id")
    private CategorySupplier categorySupplier;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "item", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

}
