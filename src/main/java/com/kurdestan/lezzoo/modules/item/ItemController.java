package com.kurdestan.lezzoo.modules.item;

import com.kurdestan.lezzoo.common.PagingData;
import com.kurdestan.lezzoo.common.SearchCriteria;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(value = "/items/")
@AllArgsConstructor
public class ItemController {

    private final ItemService service;
    private ItemMapper mapper;

    @PostMapping("/v1")
    public ResponseEntity<?> save(@RequestBody ItemDTO itemDTO) {
        Item item = mapper.toItem(itemDTO);
        service.save(item);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/v1")
    public ResponseEntity<?> update(@RequestBody ItemDTO itemDTO) {
        Item item = mapper.toItem(itemDTO);
        service.update(item);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/{id}")
    public ResponseEntity<ItemDTO> getById(@PathVariable Long id) {
        Item item = service.getById(id);
        ItemDTO itemDTO = mapper.toItemDTO(item);
        return ResponseEntity.ok(itemDTO);
    }

    @DeleteMapping("/v1/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/v1/paging/{page}/{size}")
    public ResponseEntity<PagingData<ItemDTO>> getAllPaging(@PathVariable Integer page, @PathVariable Integer size) {
        Page<Item> itemPage = service.paging(page, size);
        return getPagingDataResponseEntity(page, itemPage);
    }

    @PostMapping("/v1/search")
    public ResponseEntity<List<ItemDTO>> search(@RequestBody List<SearchCriteria> searchCriteria) {
        List<Item> itemList = service.search(searchCriteria);
        List<ItemDTO> itemDTOS = mapper.toItemDTOList(itemList);
        return ResponseEntity.ok(itemDTOS);
    }

    private ResponseEntity<PagingData<ItemDTO>> getPagingDataResponseEntity(@PathVariable Integer page, Page<Item> itemPage) {
        int totalPage = itemPage.getTotalPages();
        List<Item> data = itemPage.getContent();
        List<ItemDTO> itemDTOS = mapper.toItemDTOList(data);
        PagingData<ItemDTO> pagingData = new PagingData<>(totalPage, page, itemDTOS);
        return ResponseEntity.ok(pagingData);
    }

}




