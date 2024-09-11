package com.myrestaurant.api.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.myrestaurant.api.enums.OrderStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "order")
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonRootName(value = "order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "id")
    private Long id;

    @Column(name = "sub_total", nullable = false)
    @JsonProperty(value = "subTotal")
    private BigDecimal subTotal;

    @Column(name = "ship_rate", nullable = false)
    @JsonProperty(value = "shipRate")
    private BigDecimal shipRate;

    @Column(name = "total_value", nullable = false)
    @JsonProperty(value = "totalValue")
    private BigDecimal totalValue;

    @CreationTimestamp
    @Column(name = "creation_date", nullable = false, columnDefinition = "datetime")
    @JsonProperty(value = "creationDate")
    private LocalDateTime creationDate;

    @Column(name = "confirmation_date", columnDefinition = "datetime")
    @JsonProperty(value = "confirmationDate")
    private LocalDateTime confirmationDate;

    @Column(name = "cancellation_date", columnDefinition = "datetime")
    @JsonProperty(value = "cancellationDate")
    private LocalDateTime cancellationDate;

    @Column(name = "delivery_date", columnDefinition = "datetime")
    @JsonProperty(value = "deliveryDate")
    private LocalDateTime deliveryDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @JsonProperty(value = "restaurant")
    private Restaurant restaurant;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    @JsonProperty(value = "customer")
    private User customer;

    @ManyToOne
    @JoinColumn(name = "payment_method_id", nullable = false)
    @JsonProperty(value = "paymentMethod")
    private PaymentMethod paymentMethod;

    @Embedded
    private Address address;

    @Column(name = "status", nullable = false)
    @JsonProperty(value = "status")
    private OrderStatus status;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<ItemOrdered> itemsOrdered = new ArrayList<>();

}
