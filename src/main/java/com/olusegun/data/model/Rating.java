package com.olusegun.data.model;


import jakarta.persistence.*;
import jakarta.persistence.GenerationType;
import lombok.*;


@Data
@Entity(name = "rating")
@NoArgsConstructor
@AllArgsConstructor
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int rate;
    private String comment;

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
