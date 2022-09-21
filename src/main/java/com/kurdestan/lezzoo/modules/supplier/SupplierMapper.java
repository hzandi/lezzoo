package com.kurdestan.lezzoo.modules.supplier;

import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface SupplierMapper {

    Supplier toSupplier(SupplierDTO supplierDTO);
    SupplierDTO toSupplierDTO(Supplier supplier);
    List<Supplier> toSupplierList(List<SupplierDTO> supplierDTOS);
    List<SupplierDTO> toSupplierDTOList(List<Supplier> categories);
}
