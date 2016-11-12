package com.wf.dcs.app.repository.base.support.converter;

import com.wf.dcs.app.repository.base.support.PathExpression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.TimeExpression;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import org.joda.time.LocalTime;

import static com.wf.dcs.app.util.DateUtil.toLocalTime;
import static cz.jirutka.rsql.parser.ast.RSQLOperators.EQUAL;
import static cz.jirutka.rsql.parser.ast.RSQLOperators.GREATER_THAN;
import static cz.jirutka.rsql.parser.ast.RSQLOperators.GREATER_THAN_OR_EQUAL;
import static cz.jirutka.rsql.parser.ast.RSQLOperators.LESS_THAN;
import static cz.jirutka.rsql.parser.ast.RSQLOperators.LESS_THAN_OR_EQUAL;
import static cz.jirutka.rsql.parser.ast.RSQLOperators.NOT_EQUAL;

public class TimeTypeConverter implements PathExpression<TimeExpression> {

    @Override
    public BooleanExpression convert(TimeExpression path, ComparisonNode expr) {
        Comparable arg = argToComparable(path.getType(), expr.getArguments().get(0));
        if (expr.getOperator() == EQUAL) {
            return path.eq(arg);
        } else if (expr.getOperator() == NOT_EQUAL) {
            return path.ne(arg);
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

    private Comparable argToComparable(Class type, String argument) {
        if (type.equals(LocalTime.class)) {
            return toLocalTime(argument);
        } else {
            throw new IllegalArgumentException("Unsupported time type: " + argument);
        }
    }

}
