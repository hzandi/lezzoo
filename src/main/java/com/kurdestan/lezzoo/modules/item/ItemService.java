package com.kurdestan.lezzoo.modules.item;

import com.kurdestan.lezzoo.common.SearchCriteria;
import org.springframework.data.domain.Page;

import java.util.List;


public interface ItemService {
    Item save(Item item);
    Item update(Item item);
    void delete(Long id);
    Item getById(Long id);
    Page<Item> paging(Integer page, Integer size);
    List<Item> search(List<SearchCriteria> searchCriteria);
}
