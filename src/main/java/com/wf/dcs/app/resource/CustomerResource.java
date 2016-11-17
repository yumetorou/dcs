package com.wf.dcs.app.resource;

import com.wf.dcs.app.dto.CustomerDto;
import com.wf.dcs.app.dto.PageDto;
import com.wf.dcs.app.dto.UserDto;
import com.wf.dcs.app.service.CustomerService;
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
@RequestMapping("/customer")
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(method = POST)
    public ResponseEntity<CustomerDto> save(@RequestBody CustomerDto customerDto) {
        return new ResponseEntity<>(customerService.save(customerDto), HttpStatus.OK);
    }

    @RequestMapping(method = GET)
    public ResponseEntity<PageDto<CustomerDto>> get(Pageable pageable) {
        return new ResponseEntity<>(customerService.get(pageable), HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = GET)
    public ResponseEntity<CustomerDto> get(@PathVariable("id") Long id) {
        return new ResponseEntity<>(customerService.get(id), HttpStatus.OK);
    }
}
