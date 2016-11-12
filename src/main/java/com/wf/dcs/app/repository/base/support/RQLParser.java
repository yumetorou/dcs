package com.wf.dcs.app.repository.base.support;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.BooleanPath;
import com.querydsl.core.types.dsl.DatePath;
import com.querydsl.core.types.dsl.DateTimePath;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.core.types.dsl.StringPath;
import com.querydsl.core.types.dsl.TimePath;
import com.wf.dcs.app.repository.base.support.converter.BooleanTypeConverter;
import com.wf.dcs.app.repository.base.support.converter.DateTimeTypeConverter;
import com.wf.dcs.app.repository.base.support.converter.DateTypeConverter;
import com.wf.dcs.app.repository.base.support.converter.EnumTypeConverter;
import com.wf.dcs.app.repository.base.support.converter.NumberTypeConverter;
import com.wf.dcs.app.repository.base.support.converter.StringTypeConverter;
import com.wf.dcs.app.repository.base.support.converter.TimeTypeConverter;
import cz.jirutka.rsql.parser.RSQLParser;
import cz.jirutka.rsql.parser.ast.ComparisonNode;
import cz.jirutka.rsql.parser.ast.LogicalNode;
import cz.jirutka.rsql.parser.ast.Node;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.Map;

import static cz.jirutka.rsql.parser.ast.LogicalOperator.AND;
import static cz.jirutka.rsql.parser.ast.LogicalOperator.OR;
import static org.apache.commons.lang3.reflect.FieldUtils.getField;

public final class RQLParser {
    private static final Field SORT_PROPERTY_FIELD = getField(Sort.Order.class, "property", true);
    private static ImmutableMap<Class, PathExpression> pathExprConverter = ImmutableMap.<Class, PathExpression>builder()
            .put(StringPath.class, new StringTypeConverter())
            .put(NumberPath.class, new NumberTypeConverter())
            .put(BooleanPath.class, new BooleanTypeConverter())
            .put(EnumPath.class, new EnumTypeConverter())
            .put(DateTimePath.class, new DateTimeTypeConverter())
            .put(DatePath.class, new DateTypeConverter())
            .put(TimePath.class, new TimeTypeConverter())
            .build();

    private RQLParser() {
    }

    public static Pageable mapSortField(EntityPath entityPath, Map<String, Path> fieldMapping,
                                        Map<EntityPath, EntityPath> joinPath, Pageable page) {
        if (page.getSort() != null) {
            Map<EntityPath, String> joinFullPath = Maps.newHashMap();
            if (joinPath != null) {
                for (Map.Entry<EntityPath, EntityPath> entry : joinPath.entrySet()) {
                    if (entry.getKey().getRoot().equals(entityPath)) {
                        joinFullPath.put(entry.getValue(), entry.getKey().toString());
                    } else {
                        joinFullPath.put(entry.getValue(), entry.getKey().toString().replace(
                                entry.getKey().toString().split("\\.")[0], joinFullPath.get(entry.getKey().getRoot())));
                    }
                }
            }
            for (Sort.Order order : page.getSort()) {
                Path path = fieldMapping.get(order.getProperty());
                if (path != null) {
                    String sortPath = joinFullPath.isEmpty() || path.getRoot().equals(entityPath)
                            ? path.toString()
                            : path.toString().replace(path.toString().split("\\.")[0], joinFullPath.get(path.getRoot()).toString());
                    ReflectionUtils.setField(SORT_PROPERTY_FIELD, order, sortPath.substring(
                            sortPath.indexOf(".") + 1));
                }
            }
        }
        return page;
    }

    public static BooleanExpression evaluate(ComparisonNode expr, Map<String, Path> fieldMapping) {
        Path path = fieldMapping.get(expr.getSelector());
        PathExpression pathExpression = path != null ? pathExprConverter.get(path.getClass()) : null;
        if (pathExpression == null) {
            throw new IllegalArgumentException("Unsupported path expression: " + expr);
        }
        return pathExpression.convert(path, expr);
    }

    public static BooleanExpression evaluate(LogicalNode expr, Map<String, Path> fieldMapping) {
        BooleanExpression left = evaluate(expr.getChildren().get(0), fieldMapping);
        BooleanExpression right = evaluate(expr.getChildren().get(1), fieldMapping);
        if (expr.getOperator() == AND) {
            return left.and(right);
        } else if (expr.getOperator() == OR) {
            return left.or(right);
        } else {
            throw new IllegalArgumentException("Unsupported logical operator: " + expr.getOperator());
        }
    }

    public static BooleanExpression evaluate(Node expr, Map<String, Path> fieldMapping) {
        if (expr instanceof ComparisonNode) {
            return evaluate((ComparisonNode) expr, fieldMapping);
        } else if (expr instanceof LogicalNode) {
            return evaluate((LogicalNode) expr, fieldMapping);
        } else {
            throw new IllegalArgumentException("Unsupported expression: " + expr);
        }
    }

    public static BooleanExpression evaluateRQL(String rql, Map<String, Path> fieldMapping) {
        return evaluate(new RSQLParser().parse(rql), fieldMapping);
    }
}
