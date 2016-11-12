package com.wf.dcs.app.repository.base.support.converter;

import com.wf.dcs.app.repository.base.support.PathExpression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.NumberExpression;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import org.apache.commons.lang3.math.NumberUtils;

import static cz.jirutka.rsql.parser.ast.RSQLOperators.EQUAL;
import static cz.jirutka.rsql.parser.ast.RSQLOperators.GREATER_THAN;
import static cz.jirutka.rsql.parser.ast.RSQLOperators.GREATER_THAN_OR_EQUAL;
import static cz.jirutka.rsql.parser.ast.RSQLOperators.LESS_THAN;
import static cz.jirutka.rsql.parser.ast.RSQLOperators.LESS_THAN_OR_EQUAL;
import static cz.jirutka.rsql.parser.ast.RSQLOperators.NOT_EQUAL;

public class NumberTypeConverter implements PathExpression<NumberExpression> {

    private static final String NULL = "NULL";

    @Override
    public BooleanExpression convert(NumberExpression path, ComparisonNode expr) {
        String argument = expr.getArguments().get(0);
        Number arg = NULL.equalsIgnoreCase(argument) ? null
                : path.getType().equals(Long.class) ? NumberUtils.createLong(argument)
                : path.getType().equals(Double.class) ? NumberUtils.createDouble(argument)
                : NumberUtils.createNumber(argument);
        if (expr.getOperator() == EQUAL) {
            return arg == null ? path.isNull() : path.eq(arg);
        } else if (expr.getOperator() == NOT_EQUAL) {
            return arg == null ? path.isNotNull() : path.ne(arg);
        } else if (expr.getOperator() == GREATER_THAN) {
            return path.gt(arg);
        } else if (expr.getOperator() == GREATER_THAN_OR_EQUAL) {
            return path.goe(arg);
        } else if (expr.getOperator() == LESS_THAN) {
            return path.lt(arg);
        } else if (expr.getOperator() == LESS_THAN_OR_EQUAL) {
            return path.loe(arg);
        } else {
            throw new IllegalArgumentException("Unsupported operator:" + expr);
        }
    }
}
