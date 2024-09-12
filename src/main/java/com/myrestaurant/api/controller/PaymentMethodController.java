package com.myrestaurant.api.controller;

import com.myrestaurant.api.entity.PaymentMethod;
import com.myrestaurant.api.service.PaymentMethodService;
import com.myrestaurant.api.vo.PaymentMethodRequestVO;
import com.myrestaurant.api.vo.PaymentMethodResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/paymentMethods")
@RequiredArgsConstructor
public class PaymentMethodController {

    private final PaymentMethodService paymentMethodService;

    @PostMapping
    public PaymentMethod save(@RequestBody PaymentMethodRequestVO paymentMethodRequestVO) {
        return paymentMethodService.save(paymentMethodRequestVO);
    }

    @GetMapping
    public ResponseEntity<List<PaymentMethod>> findAll() {
        return ResponseEntity.ok().body(paymentMethodService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PaymentMethodResponseVO> findById(@PathVariable Long id) {
        PaymentMethodResponseVO paymentMethodResponseVO = paymentMethodService.findById(id);
        if (paymentMethodResponseVO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(paymentMethodResponseVO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<PaymentMethod> update(@PathVariable Long id, @RequestBody PaymentMethodRequestVO paymentMethodRequestVO) {
        PaymentMethod paymentMethod = paymentMethodService.update(id, paymentMethodRequestVO);
        if (paymentMethod != null) return ResponseEntity.ok().body(paymentMethod);
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            PaymentMethod paymentMethod = paymentMethodService.delete(id);
            if (paymentMethod == null) return ResponseEntity.notFound().build();
            else return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

//    Aulas 4.33 e 4.34 ensinam como fazer o UPDATE parcial usando o PATCH.

}
