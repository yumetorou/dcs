package com.wf.dcs.app.resource;

import com.wf.dcs.app.dto.InventoryItemDto;
import com.wf.dcs.app.dto.PageDto;
import com.wf.dcs.app.service.InventoryItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping("/inventory-item")
public class InventoryItemResource {

    @Autowired
    private InventoryItemService inventoryItemService;

    @RequestMapping(method = POST)
    public ResponseEntity<InventoryItemDto> save(@RequestBody InventoryItemDto inventoryItemDto) {
        return new ResponseEntity<>(inventoryItemService.save(inventoryItemDto), HttpStatus.OK);
    }

    @RequestMapping(method = GET)
    public ResponseEntity<PageDto<InventoryItemDto>> get(Pageable pageable) {
        return new ResponseEntity<>(inventoryItemService.get(pageable), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<InventoryItemDto> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(inventoryItemService.get(id), HttpStatus.OK);
    }
}
