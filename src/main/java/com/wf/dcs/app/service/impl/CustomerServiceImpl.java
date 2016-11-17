package com.wf.dcs.app.service.impl;

import com.wf.dcs.app.dto.CustomerDto;
import com.wf.dcs.app.dto.PageDto;
import com.wf.dcs.app.mapper.OrikaBeanMapper;
import com.wf.dcs.app.model.Customer;
import com.wf.dcs.app.repository.CustomerRepository;
import com.wf.dcs.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private OrikaBeanMapper mapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public CustomerDto save(CustomerDto customerDto) {
        Customer customer = customerRepository.findById(customerDto.getId());

        if (customer == null) {
            customer = new Customer();
        }

        mapper.map(customerDto, customer);

        return mapper.map(customerRepository.save(customer), CustomerDto.class);
    }

    @Override
    public CustomerDto get(Long id) {
        return null;
    }

    @Override
    public PageDto<CustomerDto> get(Pageable pageable) {
        return null;
    }
}
