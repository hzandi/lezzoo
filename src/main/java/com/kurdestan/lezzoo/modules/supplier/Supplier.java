package com.kurdestan.lezzoo.modules.supplier;


import com.kurdestan.lezzoo.common.BaseEntity;
import com.kurdestan.lezzoo.modules.category.Category;
import com.kurdestan.lezzoo.modules.supplier_category.SupplierCategory;
import lombok.Data;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

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
    @Column(name = "address")
    private String address;

    @Column(name = "location")
    private Point<G2D> location;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "supplier", cascade = CascadeType.ALL)
    private List<SupplierCategory> supplierCategories;

}
