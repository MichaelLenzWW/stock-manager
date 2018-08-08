package com.lenz.stock.manager.order;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import com.lenz.stock.manager.persistence.SearchCriteria;

public class OrderSpecification implements Specification<Order> {

  private SearchCriteria criteria;

  /**
   * 
   * @param criteria
   */
  public OrderSpecification(final SearchCriteria criteria) {
    this.criteria = criteria;
  }

  /*
   * (non-Javadoc)
   * @see org.springframework.data.jpa.domain.Specification#toPredicate(javax.persistence.criteria.Root, javax.persistence.criteria.CriteriaQuery,
   * javax.persistence.criteria.CriteriaBuilder)
   */
  @Override
  public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder builder) {

    if (criteria.getOperation().equalsIgnoreCase(">")) {
      return builder.greaterThanOrEqualTo(root.<String> get(criteria.getKey()), criteria.getValue().toString());
    }
    if (criteria.getOperation().equalsIgnoreCase("<")) {
      return builder.lessThanOrEqualTo(root.<String> get(criteria.getKey()), criteria.getValue().toString());
    }

    if (criteria.getOperation().equalsIgnoreCase(":")) {
      if (root.get(criteria.getKey()).getJavaType() == String.class) {
        return builder.like(root.<String> get(criteria.getKey()), "%" + criteria.getValue() + "%");
      } else {
        return builder.equal(root.get(criteria.getKey()), criteria.getValue());
      }
    }
    return null;
  }
}