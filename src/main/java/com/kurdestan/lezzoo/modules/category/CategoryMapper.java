package com.kurdestan.lezzoo.modules.category;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    Category toCategory(CategoryDTO categoryDTO);
    CategoryDTO toCategoryDTO(Category category);
    List<Category> toCategoryList(List<CategoryDTO> categoryDTOS);
    List<CategoryDTO> toCategoryDTOList(List<Category> categories);
}
