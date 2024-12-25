package sit.int202.classicmodels_2567.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @Column(name = "customerNumber", nullable = false)
    private Integer id;

    @Column(name = "customerName", nullable = false, length = 50)
    private String customerName;

    @Column(name = "contactLastName", nullable = false, length = 50)
    private String contactLastName;

    @Column(name = "contactFirstName", nullable = false, length = 50)
    private String contactFirstName;

    @Column(name = "phone", nullable = false, length = 50)
    private String phone;

    @Column(name = "addressLine1", nullable = false, length = 50)
    private String addressLine1;

    @Column(name = "addressLine2", length = 50)
    private String addressLine2;

    @Column(name = "city", nullable = false, length = 50)
    private String city;

    @Column(name = "state", length = 50)
    private String state;

    @Column(name = "postalCode", length = 15)
    private String postalCode;

    @Column(name = "country", nullable = false, length = 50)
    private String country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "salesRepEmployeeNumber")
    private Employee salesRepEmployeeNumber;

    @Column(name = "creditLimit", precision = 10, scale = 2)
    private BigDecimal creditLimit;

    @OneToMany(mappedBy = "customerNumber")
    @OrderBy("orderDate desc")
    private List<Order> orders;
//    @Column(name = "password", length = 128)
//    private String password;
//
//    @ColumnDefault("'User'")
//    @Column(name = "role", length = 25)
//    private String role;

}