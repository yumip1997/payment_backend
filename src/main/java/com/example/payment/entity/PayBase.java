package com.example.payment.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class PayBase {

    @Id
    private String payNo;

}
