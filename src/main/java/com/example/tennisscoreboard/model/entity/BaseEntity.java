package com.example.tennisscoreboard.model.entity;

import lombok.Data;

import javax.persistence.*;

import java.io.Serializable;

@MappedSuperclass
@Data
public class BaseEntity<T extends Serializable> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private T id;
}
