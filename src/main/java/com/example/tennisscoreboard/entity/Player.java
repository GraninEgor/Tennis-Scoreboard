package com.example.tennisscoreboard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Player extends BaseEntity<Long>{
    @Column(unique = true, nullable = false)
    private String name;
}
