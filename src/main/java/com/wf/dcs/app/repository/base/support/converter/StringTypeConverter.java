package com.wf.dcs.app.repository.base.support.converter;

import com.wf.dcs.app.repository.base.support.PathExpression;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.StringExpression;
import cz.jirutka.rsql.parser.ast.ComparisonNode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static cz.jirutka.rsql.parser.ast.RSQLOperators.EQUAL;
import static cz.jirutka.rsql.parser.ast.RSQLOperators.NOT_EQUAL;

public class StringTypeConverter implements PathExpression<StringExpression> {

    private static final String WILDCARD = "*";
    private static final String FUZZY = "~";
    private static final Pattern BOOST_PATTERN = Pattern.compile("(.*)\\^([\\d.]*$)");
    private static final String NULL = "NULL";

    @Override
    public BooleanExpression convert(StringExpression path, ComparisonNode expr) {
        // Convert fuzzy search to "contains" due to non-support for database
        String value = expr.getArguments().get(0);
        Matcher matcher = BOOST_PATTERN.matcher(value);
        if (matcher.find()) {
            value = matcher.group(1);
        }
        value = value.endsWith(FUZZY) ? value.replace(FUZZY, "").concat("*") : value;
        if (expr.getOperator() == EQUAL) {
            return NULL.equalsIgnoreCase(value) ? path.isNull() : equal(path, value);
        } else if (expr.getOperator() == NOT_EQUAL) {
            return NULL.equalsIgnoreCase(value) ? path.isNotNull() : equal(path, value).not().or(path.isNull());
        } else {
            throw new IllegalArgumentException("Unsupported operator:" + expr);
        }
    }

    private BooleanExpression equal(StringExpression path, String argument) {
        if (argument.startsWith(WILDCARD) && argument.endsWith(WILDCARD)) {
            return path.containsIgnoreCase(sanitizeWildcard(argument));
        } else if (argument.endsWith(WILDCARD)) {
            return path.startsWithIgnoreCase(sanitizeWildcard(argument));
        } else if (argument.startsWith(WILDCARD)) {
            return path.endsWithIgnoreCase(sanitizeWildcard(argument));
        } else {
            return path.equalsIgnoreCase(argument);
        }
    }

    private String sanitizeWildcard(String argument) {
        return argument.replace(WILDCARD, "");
    }

}
