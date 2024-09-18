package com.myrestaurant.api.controller;


import com.myrestaurant.api.entity.Kitchen;
import com.myrestaurant.api.service.KitchenService;
import com.myrestaurant.api.vo.KitchenRequestVO;
import com.myrestaurant.api.vo.KitchenResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/kitchens")
@RequiredArgsConstructor
public class KitchenController {

    private final KitchenService kitchenService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Kitchen save(@RequestBody KitchenRequestVO kitchenRequestVO) {
        return kitchenService.save(kitchenRequestVO);
    }

    @GetMapping
    public ResponseEntity<List<Kitchen>> findAll() {
        return ResponseEntity.ok().body(kitchenService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<KitchenResponseVO> findById(@PathVariable Long id) {
        KitchenResponseVO kitchenResponseVO = kitchenService.findById(id);
        if (kitchenResponseVO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(kitchenResponseVO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Kitchen> update(@PathVariable Long id, @RequestBody KitchenRequestVO kitchenRequestVO) {
        Kitchen kitchen = kitchenService.update(id, kitchenRequestVO);
        if (kitchen == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok().body(kitchen);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            Kitchen kitchen = kitchenService.delete(id);
            if (kitchen == null) return ResponseEntity.notFound().build();
            else return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

    //    Aulas 4.33 e 4.34 ensinam como fazer o UPDATE parcial usando o PATCH.


}
