package com.cogent.discount;

import com.cogent.ecommerce.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiscountJpaRepository extends JpaRepository<Discount, Integer> {
}
