package com.cogent.discount;

import com.cogent.ecommerce.model.Account;
import com.cogent.ecommerce.model.Discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    @Autowired
    private AccountJpaRepository accountJpaRepository;

    //getting discount for user if it exists for that user and code matches
    public Discount getDiscountAtUser(int id,String code){

        Account account = accountJpaRepository.findById(id).orElse(null);
        if(account!=null){
            Discount discount = account.getDiscount();
            if(discount==null){
                return null;
            }else{
                if(discount.getDiscountCode().equals(code)){
                    return discount;
                }else {
                    return null;
                }
            }
        }
        return null;
    }
    //use the discount: on order submit:
    public Boolean useDiscount(int id,String code){

        Account account = accountJpaRepository.findById(id).orElse(null);
        if(account!=null){
            Discount discount = account.getDiscount();
            if(discount==null){
                return false;
            }else{
                if(discount.getDiscountCode().equals(code)){
                    account.setDiscount(null);
                    accountJpaRepository.save(account);
                    return true;
                }
                return false;
            }
        }
        return false;
    }



}
