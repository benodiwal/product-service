package com.example.productservices.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreDto {
    private Long id;
    private String title;
    private String description;
    private String category;
    private String image;
    private double price;
}
