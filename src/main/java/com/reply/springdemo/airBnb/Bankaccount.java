package com.reply.springdemo.airBnb;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "bankaccount")
public class Bankaccount {
    @Id
    @Column(name = "AccountNUMBER", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "RoutingNum", nullable = false)
    private Integer routingNum;

    @Size(max = 20)
    @NotNull
    @Column(name = "AccountType", nullable = false, length = 20)
    private String accountType;

    @OneToOne(mappedBy = "bankAccountNumber")
    private Host host;

}