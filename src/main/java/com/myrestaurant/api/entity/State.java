package com.myrestaurant.api.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import com.myrestaurant.api.vo.StateRequestVO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "state")
@NoArgsConstructor
@AllArgsConstructor
@Data
@JsonRootName(value = "state")
public class State implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    @Column(name = "name", nullable = false)
    private String name;

    public State(StateRequestVO stateRequestVO) {
        this.name = stateRequestVO.getName();
    }

}
