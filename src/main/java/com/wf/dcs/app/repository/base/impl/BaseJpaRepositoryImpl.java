package com.wf.dcs.app.repository.base.impl;

import com.querydsl.core.JoinType;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.Predicate;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.JPQLQuery;
import com.wf.dcs.app.repository.base.BaseJpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.QueryDslJpaRepository;
import org.springframework.data.jpa.repository.support.Querydsl;
import org.springframework.data.querydsl.EntityPathResolver;
import org.springframework.data.querydsl.SimpleEntityPathResolver;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import static com.wf.dcs.app.repository.base.support.RQLParser.evaluateRQL;
import static com.wf.dcs.app.repository.base.support.RQLParser.mapSortField;
import static java.util.Collections.EMPTY_MAP;
import static org.apache.commons.lang3.StringUtils.isEmpty;

/**
 * @author ddevera
 */
public class BaseJpaRepositoryImpl<T, ID extends Serializable>
        extends QueryDslJpaRepository<T, ID>
        implements BaseJpaRepository<T, ID> {

    private static final EntityPathResolver DEFAULT_ENTITY_PATH_RESOLVER = SimpleEntityPathResolver.INSTANCE;

    private final Querydsl querydsl;
    private final EntityPath<T> path;
    private final PathBuilder<T> builder;

    public BaseJpaRepositoryImpl(JpaEntityInformation<T, ID> entityInformation, EntityManager entityManager) {
        super(entityInformation, entityManager);
        this.path = DEFAULT_ENTITY_PATH_RESOLVER.createPath(entityInformation.getJavaType());
        this.builder = new PathBuilder<>(path.getType(), path.getMetadata());
        this.querydsl = new Querydsl(entityManager, builder);
    }

    @Override
    public Page<T> findAll(Map<String, Path> fieldMapping, Pageable page) {
        return findAll(mapSortField(this.path, fieldMapping, null, page));
    }

    @Override
    public Page<T> searchRql(String rql, Map<String, Path> fieldMapping, Pageable page) {
        return searchRql(rql, fieldMapping, EMPTY_MAP, null, page);
    }

    @Override
    public long countRql(String rql, Map<String, Path> fieldMapping) {
        return countRql(rql, fieldMapping, EMPTY_MAP, null);
    }

    @Override
    public Page<T> searchRql(String rql, Map<String, Path> fieldMapping, Map<EntityPath, EntityPath> joinPath,
                             JoinType joinType, Pageable page) {
        if (page != null) {
            return isEmpty(rql) ? findAll(fieldMapping, page) : findAll(evaluateRQL(rql, fieldMapping), joinPath,
                    joinType, mapSortField(this.path, fieldMapping, joinPath, page));
        } else {
            return new PageImpl<>(isEmpty(rql) ? findAll() : findAll(evaluateRQL(rql, fieldMapping), joinPath, joinType));
        }
    }

    @Override
    public long countRql(String rql, Map<String, Path> fieldMapping, Map<EntityPath, EntityPath> joinPath,
                         JoinType joinType) {
        return isEmpty(rql) ? count() : countAll(evaluateRQL(rql, fieldMapping), joinPath,
                joinType);
    }

    private List<T> findAll(Predicate predicate, Map<EntityPath, EntityPath> joinPath, JoinType joinType) {
        return createQuery(joinPath, joinType, predicate).from(path).fetch();
    }

    private Page<T> findAll(Predicate predicate, Map<EntityPath, EntityPath> joinPath, JoinType joinType, Pageable pageable) {
        JPQLQuery countQuery = createQuery(joinPath, joinType, predicate);
        JPQLQuery query = querydsl.applyPagination(pageable, createQuery(joinPath, joinType, predicate));

        Long total = countQuery.fetchCount();
        List<T> content = total > pageable.getOffset() ? query.from(path).fetch() : Collections.emptyList();

        return new PageImpl(content, pageable, total);
    }

    private Long countAll(Predicate predicate, Map<EntityPath, EntityPath> joinPath, JoinType joinType) {
        JPQLQuery countQuery = createQuery(joinPath, joinType, predicate);
        return countQuery.fetchCount();
    }

    private JPQLQuery createQuery(Map<EntityPath, EntityPath> joinPath, JoinType joinType, Predicate predicate) {
        JPQLQuery query =
                querydsl
                        .createQuery(path)
                        .where(predicate);

        for (Map.Entry<EntityPath, EntityPath> entityPath : joinPath.entrySet()) {
            if (joinType == JoinType.LEFTJOIN) {
                query.leftJoin(entityPath.getKey(), entityPath.getValue());
            } else if (joinType == JoinType.RIGHTJOIN) {
                query.rightJoin(entityPath.getKey(), entityPath.getValue());
            }
        }

        return query;
    }

}