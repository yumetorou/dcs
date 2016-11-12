package com.wf.dcs.app.repository.base.support.converter;

import com.wf.dcs.app.repository.base.support.PathExpression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.EnumExpression;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import org.apache.commons.lang3.EnumUtils;

import static cz.jirutka.rsql.parser.ast.RSQLOperators.EQUAL;
import static cz.jirutka.rsql.parser.ast.RSQLOperators.NOT_EQUAL;

public class EnumTypeConverter implements PathExpression<EnumExpression> {

    private static final String NULL = "NULL";

    @Override
    public BooleanExpression convert(EnumExpression path, ComparisonNode expr) {
        Enum arg = EnumUtils.getEnum(path.getType(), expr.getArguments().get(0).toUpperCase());
        if (arg == null && !NULL.equalsIgnoreCase(expr.getArguments().get(0))) {
            throw new IllegalArgumentException("Nonexistent enum value:" + expr);
        }
        if (expr.getOperator() == EQUAL) {
            return NULL.equalsIgnoreCase(expr.getArguments().get(0)) ? path.isNull()
                    : path.eq(arg);
        } else if (expr.getOperator() == NOT_EQUAL) {
            return NULL.equalsIgnoreCase(expr.getArguments().get(0)) ? path.isNotNull()
                    : path.ne(arg).or(path.isNull());
        } else {
            throw new IllegalArgumentException("Unsupported operator:" + expr);
        }
    }
}
