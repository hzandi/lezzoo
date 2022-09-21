package com.kurdestan.lezzoo.modules.supplier_category;

import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface SupplierCategoryMapper {

    SupplierCategory toSupplierCategory(SupplierCategoryDTO supplierCategoryDTO);
    SupplierCategoryDTO toSupplierCategoryDTO(SupplierCategory supplierCategory);
    List<SupplierCategory> toSupplierCategoryList(List<SupplierCategoryDTO> supplierCategoryDTOS);
    List<SupplierCategoryDTO> toSupplierCategoryDTOList(List<SupplierCategory> supplierCategories);
}