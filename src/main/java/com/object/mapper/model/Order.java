package com.object.mapper.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @Column(name = "order_id", columnDefinition = "INT", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int orderId;
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "customerId")
    private Customer customer;
    @OneToMany(mappedBy = "productId", fetch = FetchType.EAGER)
    @ElementCollection
    private List<Product> products;
    @Column(name = "date", columnDefinition = "VARCHAR(255)")
    private LocalDate orderDate;
    @Column(name = "address", columnDefinition = "VARCHAR(255)")
    private String shippingAddress;
    @Column(name = "total_price", columnDefinition = "NUMERIC")
    private double totalPrice;
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private Status orderStatus;

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(orderDate, format);
        this.orderDate = date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "customer=" + customer +
                ", orderDate=" + orderDate +
                ", shippingAddress='" + shippingAddress + '\'' +
                ", totalPrice=" + totalPrice +
                ", orderStatus=" + orderStatus +
                '}';
    }
}