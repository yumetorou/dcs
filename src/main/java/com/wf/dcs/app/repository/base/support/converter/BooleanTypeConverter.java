package com.wf.dcs.app.repository.base.support.converter;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.wf.dcs.app.repository.base.support.PathExpression;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import org.apache.commons.lang3.BooleanUtils;

import static cz.jirutka.rsql.parser.ast.RSQLOperators.EQUAL;
import static cz.jirutka.rsql.parser.ast.RSQLOperators.NOT_EQUAL;


public class BooleanTypeConverter implements PathExpression<BooleanExpression> {

    @Override
    public BooleanExpression convert(BooleanExpression path, ComparisonNode expr) {
        Boolean arg = BooleanUtils.toBoolean(expr.getArguments().get(0));
        if (expr.getOperator() == EQUAL) {
            return path.eq(arg);
        } else if (expr.getOperator() == NOT_EQUAL) {
            return path.ne(arg).or(path.isNull());
        } else {
            throw new IllegalArgumentException("Unsupported operator:" + expr);
        }
    }
}
