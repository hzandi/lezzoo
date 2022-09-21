package com.kurdestan.lezzoo.modules.category;

import com.kurdestan.lezzoo.common.SearchCriteria;
import org.springframework.data.domain.Page;

import java.util.List;


public interface CategoryService {
    Category save(Category category);
    Category update(Category category);
    void delete(Long id);
    Category getById(Long id);
    Page<Category> paging(Integer page, Integer size);
    List<Category> search(List<SearchCriteria> searchCriteria);
}
