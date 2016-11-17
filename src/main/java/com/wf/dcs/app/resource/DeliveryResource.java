package com.wf.dcs.app.resource;

import com.wf.dcs.app.dto.DeliveryDto;
import com.wf.dcs.app.dto.PageDto;
import com.wf.dcs.app.service.DeliveryService;
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
@RequestMapping("/delivery")
public class DeliveryResource {

    @Autowired
    private DeliveryService deliveryService;

    @RequestMapping(method = POST)
    public ResponseEntity<DeliveryDto> save(@RequestBody DeliveryDto deliveryDto) {
        return new ResponseEntity<>(deliveryService.save(deliveryDto), HttpStatus.OK);
    }

    @RequestMapping(method = GET)
    public ResponseEntity<PageDto<DeliveryDto>> get(Pageable pageable) {
        return new ResponseEntity<>(deliveryService.get(pageable), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<DeliveryDto> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(deliveryService.get(id), HttpStatus.OK);
    }
}
