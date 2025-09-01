package com.example.tennisscoreboard.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Player extends BaseEntity<Long>{
    @Column(unique = true, nullable = false)
    @NonNull
    private String name;
}
