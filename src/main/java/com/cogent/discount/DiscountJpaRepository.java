package com.cogent.discount;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiscountJpaRepository extends JpaRepository<Discounts, Integer> {

    public Optional<Discounts> findByAccountIdAndDiscountCode(int accountId,String discountCode);

}
