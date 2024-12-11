package com.flow.baseQualification;

import java.util.UUID;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.collections4.CollectionUtils;
import org.hibernate.query.criteria.internal.CriteriaBuilderImpl;
import org.hibernate.query.criteria.internal.expression.LiteralExpression;

public class BasePredicateUtils {
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static <T> Predicate getPredicate(CriteriaBuilder cb, Root<T> root, Qualification qualification) {
		Predicate finalPredicate = null;
		if (qualification != null) {
			if (qualification instanceof RelationalQualification) {
				Expression expression = root.get(((RelationalQualification) qualification).getParamName());
				Object valueObj = ((RelationalQualification) qualification).getValue();
				switch (((RelationalQualification) qualification).getOperator()) {
				case Equal:
					try {
						valueObj = UUID.fromString(valueObj.toString());
						// do something
					} catch (IllegalArgumentException exception) {
						// handle the case where string is not valid UUID
					}
					finalPredicate = cb.equal(expression, valueObj);
					break;
				case Equal_Case_Insensitive:
					if (valueObj != null && valueObj instanceof String) {
						finalPredicate = cb.equal(cb.lower(expression), ((String) valueObj).toLowerCase());
					}
					break;
				case GreaterThan:
					finalPredicate = cb.greaterThan(expression, (Comparable) valueObj);
					break;
				case GreaterThanOrEqual:
					finalPredicate = cb.greaterThanOrEqualTo(expression, (Comparable) valueObj);
					break;
				case In:
					finalPredicate = expression.in(new LiteralExpression<>((CriteriaBuilderImpl) cb, valueObj));
					break;
				case LessThan:
					finalPredicate = cb.lessThan(expression, (Comparable) valueObj);
					break;
				case LessThanOrEqual:
					finalPredicate = cb.lessThanOrEqualTo(expression, (Comparable) valueObj);
					break;
				case Like:
					finalPredicate = cb.like(expression, (String) valueObj);
					break;
				case Not_Equal:
					finalPredicate = cb.notEqual(expression, valueObj);
					break;
				case Not_Equal_Case_Insensitive:
					if (valueObj != null && valueObj instanceof String) {
						finalPredicate = cb.notEqual(cb.lower(expression), ((String) valueObj).toLowerCase());
					}
					break;
				case Contains:
					finalPredicate = cb.isMember(valueObj, expression);
					break;
				default:
					break;
				}
			} else if (qualification instanceof BatchQualification) {
				if (CollectionUtils.isNotEmpty(((BatchQualification) qualification).getQualSet())) {
					for (Qualification batchQual : ((BatchQualification) qualification).getQualSet()) {
						Predicate predicate = getPredicate(cb, root, batchQual);
						if (predicate != null && finalPredicate == null) {
							finalPredicate = predicate;
						} else if (((BatchQualification) qualification).getBinaryOperator() == BinaryOperator.And) {
							finalPredicate = cb.and(predicate, finalPredicate);
						} else if (((BatchQualification) qualification).getBinaryOperator() == BinaryOperator.Or) {
							finalPredicate = cb.or(predicate, finalPredicate);
						}
					}
				}
			}
		}
		return finalPredicate;
	}

}
