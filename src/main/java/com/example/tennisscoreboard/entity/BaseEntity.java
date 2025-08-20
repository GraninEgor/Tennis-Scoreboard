package com.example.tennisscoreboard.entity;

import javax.persistence.*;

import java.io.Serializable;

@MappedSuperclass
public class BaseEntity<T extends Serializable> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;
}
