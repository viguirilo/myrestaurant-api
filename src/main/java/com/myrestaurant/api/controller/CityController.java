package com.myrestaurant.api.controller;

import com.myrestaurant.api.entity.City;
import com.myrestaurant.api.service.CityService;
import com.myrestaurant.api.vo.CityRequestVO;
import com.myrestaurant.api.vo.CityResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<City> save(@RequestBody CityRequestVO cityRequestVO) {
        City city = cityService.save(cityRequestVO);
        if (city == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok().body(city);
    }

    @GetMapping
    public ResponseEntity<List<City>> findAll() {
        return ResponseEntity.ok().body(cityService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<CityResponseVO> findById(@PathVariable Long id) {
        CityResponseVO cityResponseVO = cityService.findById(id);
        if (cityResponseVO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(cityResponseVO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<City> update(@PathVariable Long id, @RequestBody CityRequestVO cityRequestVO) {
        City city = cityService.update(id, cityRequestVO);
        if (city != null) return ResponseEntity.ok().body(city);
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            City city = cityService.delete(id);
            if (city == null) return ResponseEntity.notFound().build();
            else return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

//    Aulas 4.33 e 4.34 ensinam como fazer o UPDATE parcial usando o PATCH.

}
