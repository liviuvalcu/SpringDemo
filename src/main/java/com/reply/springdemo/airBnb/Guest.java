package com.reply.springdemo.airBnb;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "guest")
public class Guest {
    @Id
    @Column(name = "AirBnBUID", nullable = false)
    private Integer id;

    @MapsId
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "AirBnBUID", nullable = false)
    private Airbnbuser airbnbuser;

    @Column(name = "AvgRating", precision = 2, scale = 1)
    private BigDecimal avgRating;

    @Column(name = "NumOfRatings")
    private Integer numOfRatings;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "CreditCardNum", nullable = false)
    private Creditcard creditCardNum;

    @OneToMany(mappedBy = "guest")
    private Set<Photoforpropertyreview> photoforpropertyreviews = new LinkedHashSet<>();

    @OneToMany(mappedBy = "guest")
    private Set<Propertyincludedinwishlist> propertyincludedinwishlists = new LinkedHashSet<>();

    @OneToMany(mappedBy = "guest")
    private Set<Wishlist> wishlists = new LinkedHashSet<>();

    @OneToMany(mappedBy = "guest")
    private Set<Message> messages = new LinkedHashSet<>();

    @OneToMany(mappedBy = "guest")
    private Set<Reviewforproperty> reviewforproperties = new LinkedHashSet<>();

    @OneToMany(mappedBy = "guest")
    private Set<Reviewforuser> reviewforusers = new LinkedHashSet<>();

    @OneToMany(mappedBy = "guestUID")
    private Set<Booking> bookings = new LinkedHashSet<>();

}