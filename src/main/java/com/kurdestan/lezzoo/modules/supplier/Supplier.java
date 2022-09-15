package com.kurdestan.lezzoo.modules.supplier;


import com.kurdestan.lezzoo.common.BaseEntity;
import com.kurdestan.lezzoo.modules.address.Address;
import com.kurdestan.lezzoo.modules.category.Category;
import com.kurdestan.lezzoo.modules.order.Order;
import com.kurdestan.lezzoo.modules.supplier_category.SupplierCategory;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Table(name = "tbl_suppliers")
@Data
@Entity
public class Supplier extends BaseEntity {

    @NotNull
    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    @NotNull
    @OneToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<SupplierCategory> supplierCategories;

}
