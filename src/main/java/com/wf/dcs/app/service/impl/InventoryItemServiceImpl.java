package com.wf.dcs.app.service.impl;

import com.wf.dcs.app.dto.InventoryItemDto;
import com.wf.dcs.app.dto.PageDto;
import com.wf.dcs.app.dto.UserDto;
import com.wf.dcs.app.mapper.OrikaBeanMapper;
import com.wf.dcs.app.repository.InventoryItemRepository;
import com.wf.dcs.app.service.InventoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class InventoryItemServiceImpl implements InventoryItemService {

    @Autowired
    private OrikaBeanMapper mapper;

    @Autowired
    private InventoryItemRepository inventoryItemRepository;

    @Override
    public InventoryItemDto save(InventoryItemDto userDto) {
        return null;
    }

    @Override
    public InventoryItemDto get(Long id) {
        return null;
    }

    @Override
    public PageDto<InventoryItemDto> get(Pageable pageable) {
        return null;
    }
}
