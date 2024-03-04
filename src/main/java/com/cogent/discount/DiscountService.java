package com.cogent.discount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class DiscountService {

    @Autowired
    private DiscountJpaRepository discountJpaRepository;

    //admin usage
    public Discounts addDiscountToUser(Discounts discounts){
        return discountJpaRepository.save(discounts);
    }

    //getting discount for user if it exists for that user and code matches
    public Discounts getDiscountAtUser(int id,String code){

        Discounts discount = discountJpaRepository.findByAccountIdAndDiscountCode(id,code).orElse(null);
        if(discount!=null){
            return discount;
        }
        return null;
    }
    //use the discount: on order submit:
    public Boolean useDiscount(int id,String code){

        Discounts discount = discountJpaRepository.findByAccountIdAndDiscountCode(id,code).orElse(null);
        if(discount!=null){
            discountJpaRepository.delete(discount);
            return true;
        }
        return false;
    }


    //email the discount to user!
    @Value("${spring.mail.username}")
    private String email;
    @Autowired
    private EmailService emailService;

    public void sendDiscountEmail(String emailOfUser,Discounts discounts){
        SimpleMailMessage discountEmail = new SimpleMailMessage();
        discountEmail.setFrom(email);
        discountEmail.setTo(emailOfUser);
        discountEmail.setSubject("Discount Code!");
        discountEmail.setText("Congrats! You have received a discount code to use on your next order!\n" +
                "Use this code: \n"+
                discounts.getDiscountCode()+"\nWith this code you can get "+discounts.getDiscountPercent()+"%" +
                "off your next order!");
    }


}
