package com.myrestaurant.api.vo;

import com.myrestaurant.api.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserResponseVO {

    public Long id;
    public String name;
    public String email;
    public LocalDateTime creationDate;

    public UserResponseVO(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.creationDate = user.getCreationDate();
    }

}
