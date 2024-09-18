package com.myrestaurant.api.controller;

import com.myrestaurant.api.entity.Permission;
import com.myrestaurant.api.service.PermissionService;
import com.myrestaurant.api.vo.PermissionRequestVO;
import com.myrestaurant.api.vo.PermissionResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Permission save(@RequestBody PermissionRequestVO permissionRequestVO) {
        return permissionService.save(permissionRequestVO);
    }

    @GetMapping
    public ResponseEntity<List<Permission>> findAll() {
        return ResponseEntity.ok().body(permissionService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<PermissionResponseVO> findById(@PathVariable Long id) {
        PermissionResponseVO permissionResponseVO = permissionService.findById(id);
        if (permissionResponseVO == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok().body(permissionResponseVO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Permission> update(@PathVariable Long id, @RequestBody PermissionRequestVO permissionRequestVO) {
        Permission permission = permissionService.update(id, permissionRequestVO);
        if (permission == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok().body(permission);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            Permission permission = permissionService.delete(id);
            if (permission == null) return ResponseEntity.notFound().build();
            else return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

//    Aulas 4.33 e 4.34 ensinam como fazer o UPDATE parcial usando o PATCH.

}
