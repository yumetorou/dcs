package com.wf.dcs.app.service.impl;

import com.google.common.collect.Lists;
import com.wf.dcs.app.dto.InventoryItemDto;
import com.wf.dcs.app.dto.PageDto;
import com.wf.dcs.app.mapper.OrikaBeanMapper;
import com.wf.dcs.app.model.InventoryItem;
import com.wf.dcs.app.repository.InventoryItemRepository;
import com.wf.dcs.app.service.InventoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryItemServiceImpl implements InventoryItemService {

    @Autowired
    private OrikaBeanMapper mapper;

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @Override
    public InventoryItemDto save(InventoryItemDto inventoryItemDto) {
        InventoryItem inventoryItem = inventoryItemRepository.findById(inventoryItemDto.getId());

        if (inventoryItem == null) {
            inventoryItem = new InventoryItem();
        }

        mapper.map(inventoryItemDto, inventoryItem);

        return mapper.map(inventoryItemRepository.save(inventoryItem), InventoryItemDto.class);
    }

    @Override
    public InventoryItemDto get(Long id) {
        return mapper.map(inventoryItemRepository.findById(id), InventoryItemDto.class);
    }

    @Override
    public PageDto<InventoryItemDto> get(Pageable pageable) {
        Page<InventoryItem> inventoryItems = inventoryItemRepository.findAll(pageable);
        List<InventoryItemDto> inventoryItemDtos = Lists.newArrayList();
        for (InventoryItem inventoryItem : inventoryItems) {
            inventoryItemDtos.add(mapper.map(inventoryItem, InventoryItemDto.class));
        }
        return PageDto.newPageInfo(inventoryItems, inventoryItemDtos);

    }
}
