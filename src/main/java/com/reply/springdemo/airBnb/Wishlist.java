package com.reply.springdemo.airBnb;

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

    @OneToMany(mappedBy = "wishlist")
    private Set<Propertyincludedinwishlist> propertyincludedinwishlists = new LinkedHashSet<>();

}