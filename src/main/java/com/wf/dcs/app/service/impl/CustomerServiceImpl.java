package com.wf.dcs.app.service.impl;

import com.google.common.collect.Lists;
import com.wf.dcs.app.dto.CustomerDto;
import com.wf.dcs.app.dto.PageDto;
import com.wf.dcs.app.mapper.OrikaBeanMapper;
import com.wf.dcs.app.model.Customer;
import com.wf.dcs.app.repository.CustomerRepository;
import com.wf.dcs.app.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return mapper.map(customerRepository.findById(id), CustomerDto.class);
    }

    @Override
    public PageDto<CustomerDto> get(Pageable pageable) {
        Page<Customer> customers = customerRepository.findAll(pageable);
        List<CustomerDto> customersDto = Lists.newArrayList();

        for (Customer customer : customers) {
            customersDto.add(mapper.map(customer, CustomerDto.class));
        }

        return PageDto.newPageInfo(customers, customersDto);
    }
}
