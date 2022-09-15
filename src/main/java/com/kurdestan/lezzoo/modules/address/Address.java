package com.kurdestan.lezzoo.modules.address;

import com.kurdestan.lezzoo.common.BaseEntity;
import com.kurdestan.lezzoo.modules.invoce.Invoice;
import com.kurdestan.lezzoo.modules.order.Order;
import com.kurdestan.lezzoo.modules.supplier.Supplier;
import com.kurdestan.lezzoo.modules.user.AppUser;
import lombok.Data;
import org.geolatte.geom.G2D;
import org.geolatte.geom.Point;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;

@Table(name = "tbl_address")
@Entity
@Data
public class Address extends BaseEntity {

    @Column(name = "title")
    private String title;

    @Column(name = "value")
    private String value;

    @Column(name = "location")
    private Point<G2D> location;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser appUser;

    @OneToOne(mappedBy = "address")
    private Supplier supplier;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "address", cascade = CascadeType.ALL)
    private List<Order> orders;

}
