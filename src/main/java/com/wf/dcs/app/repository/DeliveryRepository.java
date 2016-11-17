package com.wf.dcs.app.repository;

import com.wf.dcs.app.model.Delivery;
import com.wf.dcs.app.repository.base.BaseJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by rbandioque on 11/17/16.
 */
public interface DeliveryRepository extends BaseJpaRepository<Delivery, Long> {

    Delivery findById(Long id);

    Page<Delivery> findByCustomer_id(Long id, Pageable pageables);
}
