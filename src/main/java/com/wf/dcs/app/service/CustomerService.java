package com.wf.dcs.app.service;

import com.wf.dcs.app.dto.CustomerDto;
import com.wf.dcs.app.dto.PageDto;
import com.wf.dcs.app.dto.UserDto;
import org.springframework.data.domain.Pageable;

public interface CustomerService {

    CustomerDto save(CustomerDto userDto);

    CustomerDto get(Long id);

    PageDto<CustomerDto> get(Pageable pageable);
}
