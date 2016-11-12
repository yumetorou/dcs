package com.wf.dcs.app.repository.base.support.converter;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.DateTimeExpression;
import com.wf.dcs.app.repository.base.support.PathExpression;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;

import static com.wf.dcs.app.util.DateUtil.DATE;
import static com.wf.dcs.app.util.DateUtil.toDateTime;
import static com.wf.dcs.app.util.DateUtil.toLocalDateTime;
import static cz.jirutka.rsql.parser.ast.RSQLOperators.EQUAL;
import static cz.jirutka.rsql.parser.ast.RSQLOperators.GREATER_THAN;
import static cz.jirutka.rsql.parser.ast.RSQLOperators.GREATER_THAN_OR_EQUAL;
import static cz.jirutka.rsql.parser.ast.RSQLOperators.LESS_THAN;
import static cz.jirutka.rsql.parser.ast.RSQLOperators.LESS_THAN_OR_EQUAL;
import static cz.jirutka.rsql.parser.ast.RSQLOperators.NOT_EQUAL;

public class DateTimeTypeConverter implements PathExpression<DateTimeExpression> {

    @Override
    public BooleanExpression convert(DateTimeExpression path, ComparisonNode expr) {
        Comparable[] args = argToComparable(path.getType(), expr.getArguments().get(0));
        boolean isDate = expr.getArguments().get(0).length() == DATE.length();
        if (expr.getOperator() == EQUAL) {
            return isDate ? path.between(args[0], args[2]) : path.eq(args[0]);
        } else if (expr.getOperator() == NOT_EQUAL) {
            return path.ne(args[0]);
        } else if (expr.getOperator() == GREATER_THAN) {
            return path.gt(args[0]);
        } else if (expr.getOperator() == GREATER_THAN_OR_EQUAL) {
            return path.goe(args[0]);
        } else if (expr.getOperator() == LESS_THAN) {
            return path.lt(isDate ? args[2] : args[1]);
        } else if (expr.getOperator() == LESS_THAN_OR_EQUAL) {
            return path.loe(isDate ? args[2] : args[1]);
        } else {
            throw new IllegalArgumentException("Unsupported operator:" + expr);
        }

    }

    private Comparable[] argToComparable(Class type, String argument) {
        Comparable[] comparables = new Comparable[3];
        if (type.equals(DateTime.class)) {
            comparables[0] = toDateTime(argument).withSecondOfMinute(0).withMillisOfSecond(0);
            comparables[1] = ((DateTime) comparables[0]).withSecondOfMinute(59).withMillisOfSecond(999);
            comparables[2] = ((DateTime) comparables[0]).withTime(23, 59, 59, 999);
        } else if (type.equals(LocalDateTime.class)) {
            comparables[0] = toLocalDateTime(argument).withSecondOfMinute(0).withMillisOfSecond(0);
            comparables[1] = ((LocalDateTime) comparables[0]).withSecondOfMinute(59).withMillisOfSecond(999);
            comparables[2] = ((LocalDateTime) comparables[0]).withTime(23, 59, 59, 999);
        } else {
            throw new IllegalArgumentException("Unsupported datetime type: " + argument);
        }
        return comparables;
    }

}
