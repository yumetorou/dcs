package com.wf.dcs.app.repository;

import com.wf.dcs.app.model.InventoryItem;
import com.wf.dcs.app.repository.base.BaseJpaRepository;

/**
 * Created by rbandioque on 11/17/16.
 */
public interface InventoryItemRepository extends BaseJpaRepository<InventoryItem, Long> {

    InventoryItem findById(Long id);
}
