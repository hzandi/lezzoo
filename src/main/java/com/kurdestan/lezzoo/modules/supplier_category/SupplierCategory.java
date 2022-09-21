package com.kurdestan.lezzoo.modules.supplier_category;

import com.kurdestan.lezzoo.common.BaseEntity;
import com.kurdestan.lezzoo.modules.item.Item;
import com.kurdestan.lezzoo.modules.supplier.Supplier;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;


@Table(name = "tbl_supplier_categories")
@Data
@Entity
public class SupplierCategory extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supplierCategory", cascade = CascadeType.ALL)
    private List<Item> items;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

}