package com.cg.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "withdraws")
public class Withdraw {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_customer", referencedColumnName = "id", nullable = false)
    private Customer customer;
    private Date createAt = new Date();
    @Column(name = "withdraw", precision = 10, scale = 0, nullable = false)
    private BigDecimal withdraw;


    public Withdraw() {
    }

    public Withdraw(Customer customer, BigDecimal withdraw) {
        this.customer = customer;
        this.withdraw = withdraw;
    }

    public Withdraw(int id, Customer customer, Date createAt, BigDecimal withdraw) {
        this.id = id;
        this.customer = customer;
        this.createAt = createAt;
        this.withdraw = withdraw;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public BigDecimal getWithdraw() {
        return withdraw;
    }

    public void setWithdraw(BigDecimal withdraw) {
        this.withdraw = withdraw;
    }
}
