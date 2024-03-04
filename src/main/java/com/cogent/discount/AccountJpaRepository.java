package com.cogent.discount;

import com.cogent.ecommerce.model.Account;
import com.cogent.ecommerce.model.Discount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountJpaRepository extends JpaRepository<Account, Integer> {

}
