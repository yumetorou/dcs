package com.wf.dcs.app.service.impl;

import com.wf.dcs.app.dto.DeliveryDto;
import com.wf.dcs.app.dto.PageDto;
import com.wf.dcs.app.dto.UserDto;
import com.wf.dcs.app.mapper.OrikaBeanMapper;
import com.wf.dcs.app.repository.DeliveryRepository;
import com.wf.dcs.app.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private OrikaBeanMapper mapper;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public DeliveryDto save(DeliveryDto userDto) {
        return null;
    }

    @Override
    public DeliveryDto get(Long id) {
        return null;
    }

    @Override
    public PageDto<DeliveryDto> get(Pageable pageable) {
        return null;
    }
}
