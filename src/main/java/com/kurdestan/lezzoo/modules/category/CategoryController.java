package com.kurdestan.lezzoo.modules.category;

import com.kurdestan.lezzoo.common.PagingData;
import com.kurdestan.lezzoo.common.SearchCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping(value = "/categories/")
@AllArgsConstructor
public class CategoryController {

    private final CategoryService service;
    private CategoryMapper mapper;

    @PostMapping("/v1")
    public ResponseEntity<?> save(@RequestBody CategoryDTO categoryDTO) {
        Category category = mapper.toCategory(categoryDTO);
        service.save(category);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/v1")
    public ResponseEntity<?> update(@RequestBody CategoryDTO categoryDTO) {
        Category category = mapper.toCategory(categoryDTO);
        service.update(category);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<CategoryDTO> getById(@PathVariable Long id) {
        Category category = service.getById(id);
        CategoryDTO categoryDTO = mapper.toCategoryDTO(category);
        return ResponseEntity.ok(categoryDTO);
    }

    @DeleteMapping("/v1/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/paging/{page}/{size}")
    public ResponseEntity<PagingData<CategoryDTO>> getAllPaging(@PathVariable Integer page, @PathVariable Integer size) {
        Page<Category> categoryPage = service.paging(page, size);
        return getPagingDataResponseEntity(page, categoryPage);
    }

    @PostMapping("/v1/search")
    public ResponseEntity<List<CategoryDTO>> search(@RequestBody List<SearchCriteria> searchCriteria) {
        List<Category> categoryList = service.search(searchCriteria);
        List<CategoryDTO> categoryDTOS = mapper.toCategoryDTOList(categoryList);
        return ResponseEntity.ok(categoryDTOS);
    }

    private ResponseEntity<PagingData<CategoryDTO>> getPagingDataResponseEntity(@PathVariable Integer page, Page<Category> categoryPage) {
        int totalPage = categoryPage.getTotalPages();
        List<Category> data = categoryPage.getContent();
        List<CategoryDTO> categoryDTOS = mapper.toCategoryDTOList(data);
        PagingData<CategoryDTO> pagingData = new PagingData<>(totalPage, page, categoryDTOS);
        return ResponseEntity.ok(pagingData);
    }

}



