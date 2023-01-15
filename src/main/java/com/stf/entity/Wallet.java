package com.stf.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Wallet {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private Double balance;

    /**
     * 使用@OneToOne注解建立于Author的关系(optional)
     *      1.mappedBy：
     *          这里的意思是Wallet通过Author类中的wallet field来建立与我的联系
     */
    @OneToOne(mappedBy = "wallet")
    @JsonIgnore//add this annotation to fix (StackOverflowError) to stop looping
    private Author author;

    public Wallet() {
    }

    public Wallet(double balance) {
        this.balance = balance;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

}
