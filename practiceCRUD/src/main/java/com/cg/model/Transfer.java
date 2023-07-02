package com.cg.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
@Entity
@Table(name = "transfers")
public class Transfer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne
    @JoinColumn(name = "id_sender", referencedColumnName = "id", nullable = false)
    private Customer sender;
    private String nameSender;
    @ManyToOne
    @JoinColumn(name = "id_recipient", referencedColumnName = "id", nullable = false)
    private Customer recipinet;
    private String nameRecipinet;
    private Date createAt=new Date();
    private double fees=10.0;
    @Column(name = "transfer_amount", precision = 10, scale = 0, nullable = false)

    private BigDecimal transferAmount;
    @Column(name = "transaction_amount", precision = 10, scale = 0, nullable = false)

    private BigDecimal transactionAmount;

    public Transfer() {
    }

    public Transfer(Customer sender, String nameSender, Customer recipinet, String nameRecipinet, BigDecimal transferAmount, BigDecimal transactionAmount) {
        this.sender = sender;
        this.nameSender = nameSender;
        this.recipinet = recipinet;
        this.nameRecipinet = nameRecipinet;
        this.transferAmount = transferAmount;
        this.transactionAmount = transactionAmount;
    }

    public Transfer(int id, Customer sender, String nameSender, Customer recipinet, String nameRecipinet, Date createAt, double fees, BigDecimal transferAmount, BigDecimal transactionAmount) {
        this.id = id;
        this.sender = sender;
        this.nameSender = nameSender;
        this.recipinet = recipinet;
        this.nameRecipinet = nameRecipinet;
        this.createAt = createAt;
        this.fees = fees;
        this.transferAmount = transferAmount;
        this.transactionAmount = transactionAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getSender() {
        return sender;
    }

    public void setSender(Customer sender) {
        this.sender = sender;
    }

    public String getNameSender() {
        return nameSender;
    }

    public void setNameSender(String nameSender) {
        this.nameSender = nameSender;
    }

    public Customer getRecipinet() {
        return recipinet;
    }

    public void setRecipinet(Customer recipinet) {
        this.recipinet = recipinet;
    }

    public String getNameRecipinet() {
        return nameRecipinet;
    }

    public void setNameRecipinet(String nameRecipinet) {
        this.nameRecipinet = nameRecipinet;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public double getFees() {
        return fees;
    }

    public void setFees(double fees) {
        this.fees = fees;
    }

    public BigDecimal getTransferAmount() {
        return transferAmount;
    }

    public void setTransferAmount(BigDecimal transferAmount) {
        this.transferAmount = transferAmount;
    }

    public BigDecimal getTransactionAmount() {
        return transactionAmount;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount;
    }
}
