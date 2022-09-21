package com.kurdestan.lezzoo.modules.supplier;

import com.kurdestan.lezzoo.common.PagingData;
import com.kurdestan.lezzoo.common.SearchCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/suppliers/")
@AllArgsConstructor
public class SupplierController {

    private final SupplierService service;
    private SupplierMapper mapper;

    @PostMapping("/v1")
    public ResponseEntity<?> save(@RequestBody SupplierDTO supplierDTO) {
        Supplier supplier = mapper.toSupplier(supplierDTO);
        service.save(supplier);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/v1")
    public ResponseEntity<?> update(@RequestBody SupplierDTO supplierDTO) {
        Supplier supplier = mapper.toSupplier(supplierDTO);
        service.update(supplier);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<SupplierDTO> getById(@PathVariable Long id) {
        Supplier supplier = service.getById(id);
        SupplierDTO supplierDTO = mapper.toSupplierDTO(supplier);
        return ResponseEntity.ok(supplierDTO);
    }

    @DeleteMapping("/v1/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/paging/{page}/{size}")
    public ResponseEntity<PagingData<SupplierDTO>> getAllPaging(@PathVariable Integer page, @PathVariable Integer size) {
        Page<Supplier> supplierPage = service.paging(page, size);
        return getPagingDataResponseEntity(page, supplierPage);
    }

    @PostMapping("/v1/search")
    public ResponseEntity<List<SupplierDTO>> search(@RequestBody List<SearchCriteria> searchCriteria) {
        List<Supplier> supplierList = service.search(searchCriteria);
        List<SupplierDTO> supplierDTOS = mapper.toSupplierDTOList(supplierList);
        return ResponseEntity.ok(supplierDTOS);
    }

    private ResponseEntity<PagingData<SupplierDTO>> getPagingDataResponseEntity(@PathVariable Integer page, Page<Supplier> supplierPage) {
        int totalPage = supplierPage.getTotalPages();
        List<Supplier> data = supplierPage.getContent();
        List<SupplierDTO> supplierDTOS = mapper.toSupplierDTOList(data);
        PagingData<SupplierDTO> pagingData = new PagingData<>(totalPage, page, supplierDTOS);
        return ResponseEntity.ok(pagingData);
    }

}




