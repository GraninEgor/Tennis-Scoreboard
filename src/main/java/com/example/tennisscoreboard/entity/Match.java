package com.example.tennisscoreboard.entity;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Match extends BaseEntity<Long>{
    @ManyToOne
    private Player player1;

    @ManyToOne
    private Player player2;

    @ManyToOne
    private Player winner;
}
