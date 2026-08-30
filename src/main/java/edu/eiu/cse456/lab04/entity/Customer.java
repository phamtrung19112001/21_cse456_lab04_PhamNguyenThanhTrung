package edu.eiu.cse456.lab04.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customers")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fullName;

    // Đề yêu cầu email phải duy nhất (unique) và không được để trống (nullable = false)
    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String phone;

    public Customer(String fullName, String email, String phone) {
        this.fullName = fullName;
        this.email = email;
        this.phone = phone;
    }
}