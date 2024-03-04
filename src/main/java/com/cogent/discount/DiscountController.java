package com.cogent.discount;

import com.cogent.ecommerce.model.Discount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/discount")
@CrossOrigin(origins = "http://localhost:4200")
public class DiscountController {

    @Autowired
    DiscountService discountService;

    @GetMapping("/checkCode/{id}/{code}")
    public ResponseEntity<?> checkDiscountCode(@PathVariable int id,@PathVariable String code){

        Discount discount = discountService.getDiscountAtUser(id,code);
        if(discount==null){
            return ResponseEntity.ok().body(false);
        }
        return ResponseEntity.ok().body(discount.getDiscountPercent());
    }

    @GetMapping("/useCode/{id}/{code}")
    public ResponseEntity<?> useDiscountCode(@PathVariable int id,@PathVariable String code){
        Boolean usedDiscount = discountService.useDiscount(id,code);
        if(!usedDiscount){
            return ResponseEntity.badRequest().body(false);
        }
        return ResponseEntity.ok().body(true);
    }


}
