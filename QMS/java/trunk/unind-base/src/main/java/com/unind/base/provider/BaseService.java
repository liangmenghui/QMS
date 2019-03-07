package com.unind.base.provider;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springside.modules.persistence.SearchFilter;
import org.springside.modules.utils.Collections3;

public class BaseService {
	public static <T> Specification<T> and(final Collection<SearchFilter> filters, final Class<T> entityClazz) {
		return bySearchFilter(filters, entityClazz, "and");
	}

	public static <T> Specification<T> or(final Collection<SearchFilter> filters, final Class<T> entityClazz) {
		return bySearchFilter(filters, entityClazz, "or");
	}

	/**
     * 扩展查询条件封装：默认仅支持and连接，添加连接操作字段，可支持and或or
     * @param filters
     * @param entityClazz
     * @param String linkOper "and" / "or"
     * @return
     */
	public static <T> Specification<T> bySearchFilter(final Collection<SearchFilter> filters, final Class<T> entityClazz, final String linkOper) {
		return new Specification<T>() {
			@SuppressWarnings({ "unchecked", "rawtypes" })
			public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				if (Collections3.isNotEmpty(filters)) {

					List<Predicate> predicates = new ArrayList<Predicate>();
					for (SearchFilter filter : filters) {
						// nested path translate, 如Task的名为"user.name"的filedName, 转换为Task.user.name属性
						String[] names = StringUtils.split(filter.fieldName, ".");
						Path expression = root.get(names[0]);
						for (int i = 1; i < names.length; i++) {
							expression = expression.get(names[i]);
						}

						// logic operator
						switch (filter.operator) {
						case EQ:
							predicates.add(builder.equal(expression, filter.value));
							break;
						case LIKE:
							predicates.add(builder.like(expression, "%" + filter.value + "%"));
							break;
						case GT:
							predicates.add(builder.greaterThan(expression, (Comparable) filter.value));
							break;
						case LT:
							predicates.add(builder.lessThan(expression, (Comparable) filter.value));
							break;
						case GTE:
							predicates.add(builder.greaterThanOrEqualTo(expression, (Comparable) filter.value));
							break;
						case LTE:
							predicates.add(builder.lessThanOrEqualTo(expression, (Comparable) filter.value));
							break;
						}
					}
					// 将所有条件用 and 联合起来
					if (!predicates.isEmpty()) {
						if(null != linkOper && "or".equals(linkOper.toLowerCase().trim())) {
							return builder.or(predicates.toArray(new Predicate[predicates.size()]));
						}
						return builder.and(predicates.toArray(new Predicate[predicates.size()]));
					}
				}
				return builder.conjunction();
			}
		};
	}
}
