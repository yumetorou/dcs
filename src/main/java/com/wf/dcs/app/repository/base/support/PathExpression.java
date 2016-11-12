/*
 * Copyright (c) 2014. Medcurial, Inc.
 * All rights reserved.
 */

package com.wf.dcs.app.repository.base.support;

import com.querydsl.core.types.Expression;
import com.querydsl.core.types.dsl.BooleanExpression;
import cz.jirutka.rsql.parser.ast.ComparisonNode;

/**
 * Interface for path type converters.
 *
 * @param <T> class type of path
 */
public interface PathExpression<T extends Expression> {

    /**
     * Convert a comparison expression to query predicate.
     *
     * @param path entity path
     * @param expr comparison expression
     * @return the {@link BooleanExpression}
     */
    BooleanExpression convert(T path, ComparisonNode expr);

}
