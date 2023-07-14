package com.reply.springdemo.airBnb;

import com.reply.airbnbdemo.model.id.WishlistId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "wishlist")
public class Wishlist {

    @EmbeddedId
    private WishlistId id;

    @MapsId("airBnBUID")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "AirBnBUID", nullable = false)
    private Guest airBnBUID;

    @Column(name = "Privacy")
    private Character privacy;

    @OneToMany(mappedBy = "wishlistName")
    private Set<Propertyincludedinwishlist> propertyincludedinwishlists = new LinkedHashSet<>();

}