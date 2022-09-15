package com.kurdestan.lezzoo.modules.category;


import com.kurdestan.lezzoo.common.BaseEntity;
import com.kurdestan.lezzoo.modules.supplier.Supplier;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Table(name = "tbl_categories")
@Data
@Entity
public class Category extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "image")
    private String image;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category", cascade = CascadeType.ALL)
    private List<Supplier> suppliers;
}
