package com.object.mapper.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Table(name = "customers")
public class Customer implements Serializable {
    @Id
    @Column(name = "customer_id", columnDefinition = "INT", nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int customerId;
    @Column(name = "firstName", columnDefinition = "VARCHAR(255)")
    private String firstName;
    @Column(name = "lastName", columnDefinition = "VARCHAR(255)")
    private String lastName;
    @Column(name = "email", columnDefinition = "VARCHAR(255)")
    private String email;
    @Column(name = "contactNumber", columnDefinition = "VARCHAR(255)")
    private String contactNumber;

    public Customer(String firstName, String lastName, String email, String contactNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.contactNumber = contactNumber;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }
}
