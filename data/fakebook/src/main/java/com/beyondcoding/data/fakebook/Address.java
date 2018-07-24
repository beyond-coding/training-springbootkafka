package com.beyondcoding.data.fakebook;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity
@Builder
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

}
