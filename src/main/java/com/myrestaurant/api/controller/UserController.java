package com.myrestaurant.api.controller;

import com.myrestaurant.api.entity.User;
import com.myrestaurant.api.service.UserService;
import com.myrestaurant.api.vo.UserRequestVO;
import com.myrestaurant.api.vo.UserResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.NoSuchAlgorithmException;
import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserResponseVO> save(@RequestBody UserRequestVO userRequestVO) throws NoSuchAlgorithmException {
        return ResponseEntity.ok().body(new UserResponseVO(userService.save(userRequestVO)));
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return ResponseEntity.ok().body(userService.findAll());
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<UserResponseVO> findById(@PathVariable Long id) {
        UserResponseVO userResponseVO = userService.findById(id);
        if (userResponseVO == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok().body(userResponseVO);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody UserRequestVO userRequestVO) {
        User user = userService.update(id, userRequestVO);
        if (user == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok().body(user);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        User user = userService.delete(id);
        if (user == null) return ResponseEntity.notFound().build();
        else return ResponseEntity.ok().build();
    }

}
