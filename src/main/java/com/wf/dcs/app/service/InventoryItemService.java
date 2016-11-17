package com.wf.dcs.app.service;

import com.wf.dcs.app.dto.InventoryItemDto;
import com.wf.dcs.app.dto.PageDto;
import com.wf.dcs.app.dto.UserDto;
import org.springframework.data.domain.Pageable;

public interface InventoryItemService {

    InventoryItemDto save(InventoryItemDto userDto);

    InventoryItemDto get(Long id);

    PageDto<InventoryItemDto> get(Pageable pageable);
}
