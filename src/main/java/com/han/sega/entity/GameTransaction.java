package com.han.sega.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class GameTransaction implements Serializable {
    private static final long serialVersionUID = 1L;

    private String id;

    private String userId;

    private String transactionId;

    private String product;

    private float amount;
}
