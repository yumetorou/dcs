package com.wf.dcs.app.service;

import com.wf.dcs.app.dto.DeliveryDto;
import com.wf.dcs.app.dto.PageDto;
import org.springframework.data.domain.Pageable;

public interface DeliveryService {

    DeliveryDto save(DeliveryDto userDto);

    DeliveryDto get(Long id);

    PageDto<DeliveryDto> get(Pageable pageable);

    DeliveryDto closeDelivery(DeliveryDto deliveryDto) throws Exception;

    DeliveryDto voidDelivery(Long id) throws Exception;
}
