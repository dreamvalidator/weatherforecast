package com.example.weatherforecast.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Entity
@Table(name = "weather_condition")
public class Condition {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    private String text;
    private String icon;
    private int code;

    @OneToOne(mappedBy = "condition")
    @JsonIgnore
    private Current current;
}

