package com.myrestaurant.api.controller;

import com.myrestaurant.api.entity.State;
import com.myrestaurant.api.service.StateService;
import com.myrestaurant.api.vo.StateRequestVO;
import com.myrestaurant.api.vo.StateResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/states")
@RequiredArgsConstructor
public class StateController {

    private final StateService stateService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public State save(@RequestBody StateRequestVO stateRequestVO) {
        return stateService.save(stateRequestVO);
    }

    @GetMapping
    public ResponseEntity<List<State>> findAll() {
        return ResponseEntity.ok().body(stateService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<StateResponseVO> findById(@PathVariable Long id) {
        StateResponseVO stateResponseVO = stateService.findById(id);
        if (stateResponseVO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(stateResponseVO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<State> update(@PathVariable Long id, @RequestBody StateRequestVO stateRequestVO) {
        State state = stateService.update(id, stateRequestVO);
        if (state != null) return ResponseEntity.ok().body(state);
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            State state = stateService.delete(id);
            if (state == null) return ResponseEntity.notFound().build();
            else return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
