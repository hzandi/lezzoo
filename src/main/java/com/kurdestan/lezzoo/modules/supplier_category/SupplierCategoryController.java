package com.kurdestan.lezzoo.modules.supplier_category;

import com.kurdestan.lezzoo.common.PagingData;
import com.kurdestan.lezzoo.common.SearchCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/supplier_categories/")
@AllArgsConstructor
public class SupplierCategoryController {

    private final SupplierCategoryService service;
    private SupplierCategoryMapper mapper;

    @PostMapping("/v1")
    public ResponseEntity<?> save(@RequestBody SupplierCategoryDTO supplierCategoryDTO) {
        SupplierCategory supplierCategory = mapper.toSupplierCategory(supplierCategoryDTO);
        service.save(supplierCategory);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/v1")
    public ResponseEntity<?> update(@RequestBody SupplierCategoryDTO supplierCategoryDTO) {
        SupplierCategory supplierCategory = mapper.toSupplierCategory(supplierCategoryDTO);
        service.update(supplierCategory);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<SupplierCategoryDTO> getById(@PathVariable Long id) {
        SupplierCategory supplierCategory = service.getById(id);
        SupplierCategoryDTO supplierCategoryDTO = mapper.toSupplierCategoryDTO(supplierCategory);
        return ResponseEntity.ok(supplierCategoryDTO);
    }

    @DeleteMapping("/v1/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/paging/{page}/{size}")
    public ResponseEntity<PagingData<SupplierCategoryDTO>> getAllPaging(@PathVariable Integer page, @PathVariable Integer size) {
        Page<SupplierCategory> supplierCategoryPage = service.paging(page, size);
        return getPagingDataResponseEntity(page, supplierCategoryPage);
    }

    @PostMapping("/v1/search")
    public ResponseEntity<List<SupplierCategoryDTO>> search(@RequestBody List<SearchCriteria> searchCriteria) {
        List<SupplierCategory> supplierCategoryList = service.search(searchCriteria);
        List<SupplierCategoryDTO> supplierCategoryDTOS = mapper.toSupplierCategoryDTOList(supplierCategoryList);
        return ResponseEntity.ok(supplierCategoryDTOS);
    }

    private ResponseEntity<PagingData<SupplierCategoryDTO>> getPagingDataResponseEntity(@PathVariable Integer page, Page<SupplierCategory> supplierCategoryPage) {
        int totalPage = supplierCategoryPage.getTotalPages();
        List<SupplierCategory> data = supplierCategoryPage.getContent();
        List<SupplierCategoryDTO> supplierCategoryDTOS = mapper.toSupplierCategoryDTOList(data);
        PagingData<SupplierCategoryDTO> pagingData = new PagingData<>(totalPage, page, supplierCategoryDTOS);
        return ResponseEntity.ok(pagingData);
    }

}



