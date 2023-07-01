package com.sell.tea.repositories.specification;

import com.sell.tea.entities.CategoryEntity;
import com.sell.tea.entities.ProductEntity;
import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class ProductSpecification {
    public static Specification<ProductEntity> filter(String name, List<Long> categoryIds) {
        return new Specification<ProductEntity>() {
            @Override
            public Predicate toPredicate(Root<ProductEntity> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if (name != null && !(name.isEmpty())) {
                    predicates.add(criteriaBuilder.like(
                            criteriaBuilder.lower(root.get("name")), "%" + name.toLowerCase() + "%"
                    ));
                }

                if(categoryIds != null && !(categoryIds.isEmpty()) && categoryIds.size() > 0){
                    Join<ProductEntity, CategoryEntity> join = root.join("category");
                    predicates.add(criteriaBuilder.and(join.get("id").in(categoryIds)));
                }
                return  criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}
