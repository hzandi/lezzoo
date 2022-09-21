package com.kurdestan.lezzoo.modules.item;

import org.mapstruct.Mapper;

import java.util.List;


@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item toItem(ItemDTO itemDTO);
    ItemDTO toItemDTO(Item item);
    List<Item> toItemList(List<ItemDTO> itemDTOS);
    List<ItemDTO> toItemDTOList(List<Item> items);
}
