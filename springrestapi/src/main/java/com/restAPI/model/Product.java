package com.restAPI.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;
import org.hibernate.validator.constraints.Range;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is required")
    @Size(min = 3, message = "Name must be atLeast 3 character")
    private String name;

    @Pattern(regexp = "^[a-zA-Z\\s]+$", message = "Brand must contain only letters")
    @NotBlank(message = "Brand is required")
    @Size(min = 5, message = "Brand must be atLeast 5 character")
    private String brand;

    @NotNull(message = "quantity is required")
    @Range(min = 1000,max = 100000,message = "price is between 1000 to 100000")
    private int price;

    @NotNull(message = "quantity is required")
    @PositiveOrZero(message = "quantity should be positive or zero")
    private int quantity;

}
