package com.wf.dcs.app.repository;

import com.wf.dcs.app.model.User;
import com.wf.dcs.app.repository.base.BaseJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author ddevera
 */
public interface UserRepository extends BaseJpaRepository<User, Long> {

    User findById(Long id);

    User findByUsername(String username);

    Page<User> findByEnabledFalse(Pageable pageable);
}
