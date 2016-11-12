package com.wf.dcs.app.repository.base;

import com.querydsl.core.JoinType;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Path;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.io.Serializable;
import java.util.Map;

/**
 * @author ddevera
 */
@NoRepositoryBean
public interface BaseJpaRepository<T, ID extends Serializable> extends JpaRepository<T, ID>,
        PagingAndSortingRepository<T, ID>,
        QueryDslPredicateExecutor<T> {

    /**
     * Find all entities.
     *
     * @param fieldMapping field mapping
     * @param page         the page configuration
     * @return page of T
     */
    Page<T> findAll(Map<String, Path> fieldMapping, Pageable page);

    /**
     * Search entities based on the parsed RQL query string.
     *
     * @param rql          the RQL query string
     * @param fieldMapping field mapping
     * @param joinPath     join paths
     * @param joinType     join type
     * @param page         the page configuration
     * @return page of T
     */
    Page<T> searchRql(String rql, Map<String, Path> fieldMapping, Map<EntityPath, EntityPath> joinPath,
                      JoinType joinType, Pageable page);

    /**
     * Count entities based on the parsed RQL query string.
     *
     * @param rql          the RQL query string
     * @param fieldMapping field mapping
     * @param joinPath     join paths
     * @param joinType     join type
     * @return entity count
     */
    long countRql(String rql, Map<String, Path> fieldMapping, Map<EntityPath, EntityPath> joinPath,
                  JoinType joinType);

    /**
     * Search entities based on the parsed RQL query string.
     *
     * @param rql          the RQL query string
     * @param fieldMapping field mapping
     * @param page         the page configuration
     * @return page of T
     */
    Page<T> searchRql(String rql, Map<String, Path> fieldMapping, Pageable page);

    /**
     * Count entities based on the parsed RQL query string.
     *
     * @param rql          the RQL query string
     * @param fieldMapping field mapping
     * @return entity count
     */
    long countRql(String rql, Map<String, Path> fieldMapping);


}
