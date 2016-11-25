package com.wf.dcs.app.service.impl;

import com.google.common.collect.Lists;
import com.wf.dcs.app.dto.DeliveryDto;
import com.wf.dcs.app.dto.PageDto;
import com.wf.dcs.app.mapper.OrikaBeanMapper;
import com.wf.dcs.app.model.Customer;
import com.wf.dcs.app.model.Delivery;
import com.wf.dcs.app.model.DeliveryItems;
import com.wf.dcs.app.repository.CustomerRepository;
import com.wf.dcs.app.repository.DeliveryRepository;
import com.wf.dcs.app.service.DeliveryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DeliveryServiceImpl implements DeliveryService {

    @Autowired
    private OrikaBeanMapper mapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Override
    public DeliveryDto save(DeliveryDto deliveryDto) {
        Delivery delivery = deliveryRepository.findById(deliveryDto.getId());

        if (delivery == null) {
            delivery = new Delivery();
        }
        mapper.map(deliveryDto, delivery);

        BigDecimal total = new BigDecimal(0);

        for (DeliveryItems deliveryItems : delivery.getItems()) {
            total = total.add(deliveryItems.getPrice().multiply(new BigDecimal(deliveryItems.getQuantity())));
        }

        delivery.setTotalDelivery(total);

        Customer customer = delivery.getCustomer();
        customer.setAccountBalance(customer.getAccountBalance().add(total));
        customer.setUnreturnedBottles(customer.getUnreturnedBottles() + delivery.getDeliveredBottles());
        customerRepository.save(customer);

        return mapper.map(deliveryRepository.save(delivery), DeliveryDto.class);
    }

    @Override
    public DeliveryDto get(Long id) {
        return mapper.map(deliveryRepository.findById(id), DeliveryDto.class);
    }

    @Override
    public PageDto<DeliveryDto> get(Pageable pageable) {
        Page<Delivery> deliveries = deliveryRepository.findAll(pageable);
        List<DeliveryDto> deliveryDtos = Lists.newArrayList();

        for (Delivery customer : deliveries) {
            deliveryDtos.add(mapper.map(customer, DeliveryDto.class));
        }

        return PageDto.newPageInfo(deliveries, deliveryDtos);
    }

    @Override
    public DeliveryDto closeDelivery(DeliveryDto deliveryDto) throws Exception {

        Delivery delivery = deliveryRepository.findById(deliveryDto.getId());
        if (delivery == null) {
            throw new Exception("Delivery does not Exists!");
        }
        if (delivery.getPaid()) {
            throw new Exception("Delivery already paid!");
        }

        delivery.setPaid(Boolean.TRUE);
        delivery.setBalance(delivery.getTotalDelivery().subtract(delivery.getPayment()));

        Customer customer = delivery.getCustomer();
        customer.setAccountBalance(customer.getAccountBalance().subtract(delivery.getPayment()));
        customer.setUnreturnedBottles(customer.getUnreturnedBottles() - delivery.getReturnedBottles());
        customerRepository.save(customer);

        delivery = deliveryRepository.save(delivery);

        return mapper.map(delivery, DeliveryDto.class);
    }

    @Override
    public DeliveryDto voidDelivery(Long id) throws Exception {
        Delivery delivery = deliveryRepository.findById(id);
        if (delivery == null) {
            throw new Exception("Delivery does not Exists!");
        }

        delivery.setVoid(Boolean.TRUE);
        Customer customer = delivery.getCustomer();

        customer.setAccountBalance(customer.getAccountBalance().subtract(delivery.getTotalDelivery()));
        customer.setUnreturnedBottles(customer.getUnreturnedBottles() - delivery.getDeliveredBottles());
        customerRepository.save(customer);

        delivery = deliveryRepository.save(delivery);
        return mapper.map(delivery, DeliveryDto.class);
    }
}
