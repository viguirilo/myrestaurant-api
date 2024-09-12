package com.myrestaurant.api.controller;

import com.myrestaurant.api.entity.Group;
import com.myrestaurant.api.service.GroupService;
import com.myrestaurant.api.vo.GroupRequestVO;
import com.myrestaurant.api.vo.GroupResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupService groupService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Group save(@RequestBody GroupRequestVO groupRequestVO) {
        return groupService.save(groupRequestVO);
    }

    @GetMapping
    public ResponseEntity<List<Group>> findAll() {
        return ResponseEntity.ok().body(groupService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<GroupResponseVO> findById(@PathVariable Long id) {
        GroupResponseVO groupResponseVO = groupService.findById(id);
        if (groupResponseVO == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok().body(groupResponseVO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Group> update(@PathVariable Long id, @RequestBody GroupRequestVO groupRequestVO) {
        Group group = groupService.update(id, groupRequestVO);
        if (group != null) return ResponseEntity.ok().body(group);
        else return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        try {
            Group group = groupService.delete(id);
            if (group == null) return ResponseEntity.notFound().build();
            else return ResponseEntity.noContent().build();
        } catch (DataIntegrityViolationException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
