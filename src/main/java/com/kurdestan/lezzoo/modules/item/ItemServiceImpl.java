package com.kurdestan.lezzoo.modules.item;

import com.kurdestan.lezzoo.common.SearchCriteria;
import com.kurdestan.lezzoo.common.SearchSpecification;
import com.kurdestan.lezzoo.common.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
@AllArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final ItemRepository itemRepository;

    @Override
    @Caching(evict = {
            @CacheEvict(value = "itemCache", allEntries = true),
    })
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "itemCache", allEntries = true),
    })
    public Item update(Item item) {
        Item lastSavedItem = getById(item.getId());
        lastSavedItem.setName(item.getName());
        lastSavedItem.setPrice(item.getPrice());
        lastSavedItem.setImage(item.getImage());
        lastSavedItem.setSupplierCategory(item.getSupplierCategory());
        return itemRepository.save(lastSavedItem);
    }

    @Override
    @Caching(evict = {
            @CacheEvict(value = "itemCache", allEntries = true),
    })
    public void delete(Long id) {
        getById(id);
        itemRepository.deleteById(id);
    }

    @Override
    @Cacheable(value = "itemCache", key = "#id")
    public Item getById(Long id) {
        Optional<Item> optionalItem = itemRepository.findById(id);
        if (optionalItem.isEmpty()) {
            throw new NotFoundException("Item Not Found!");
        }
        return optionalItem.get();
    }

    @Override
    public Page<Item> paging(Integer page, Integer size) {
        return itemRepository.findAll(PageRequest.of(page, size, Sort.by("id").descending()));
    }

    @Override
    public List<Item> search(List<SearchCriteria> searchCriteria) {
        SearchSpecification<Item> itemSpecification = new SearchSpecification<>();
        searchCriteria.forEach(criteria -> itemSpecification.add(criteria));
        return itemRepository.findAll(itemSpecification);
    }

}


