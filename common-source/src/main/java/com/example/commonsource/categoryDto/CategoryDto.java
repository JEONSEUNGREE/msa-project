package com.example.commonsource.categoryDto;


import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CategoryDto {

    public String categoryName;
    public String categoryId;
}
