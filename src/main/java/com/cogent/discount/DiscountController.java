package com.cogent.discount;

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

        Discounts discounts = discountService.getDiscountAtUser(id,code);
        if(discounts==null){
            return ResponseEntity.ok().body(false);
        }
        return ResponseEntity.ok().body(discounts.getDiscountPercent());
    }

    @GetMapping("/useCode/{id}/{code}")
    public ResponseEntity<?> useDiscountCode(@PathVariable int id,@PathVariable String code){
        Boolean usedDiscount = discountService.useDiscount(id,code);
        if(!usedDiscount){
            return ResponseEntity.badRequest().body(false);
        }
        return ResponseEntity.ok().body(true);
    }

    @PostMapping("/addCodeToUser/{accountId}")
    public ResponseEntity<?> addDiscountCodeToUser(@PathVariable int accountId,@RequestBody DiscountDTO discountDTO){
        Discounts toAdd = new Discounts();
        toAdd.setDiscountCode(discountDTO.getCode());
        toAdd.setDiscountPercent(discountDTO.getDiscountPercent());
        toAdd.setAccountId(accountId);
        return ResponseEntity.ok().body(discountService.addDiscountToUser(toAdd));
    }

}
