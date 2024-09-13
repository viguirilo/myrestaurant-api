package com.myrestaurant.api.service;

import com.myrestaurant.api.entity.State;
import com.myrestaurant.api.repository.StateRepository;
import com.myrestaurant.api.vo.StateRequestVO;
import com.myrestaurant.api.vo.StateResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StateService {

    private final StateRepository stateRepository;

    public State save(StateRequestVO stateRequestVO) {
        return stateRepository.save(new State(stateRequestVO));
    }

    public List<State> findAll() {
        return stateRepository.findAll();
    }

    public StateResponseVO findById(Long id) {
        Optional<State> stateOptional = stateRepository.findById(id);
        return stateOptional.map(StateResponseVO::new).orElse(null);
    }

    public State update(Long id, StateRequestVO stateRequestVO) {
        Optional<State> stateOptional = stateRepository.findById(id);
        if (stateOptional.isPresent()) {
            State state = stateOptional.get();
            BeanUtils.copyProperties(stateRequestVO, state, "id");
            return stateRepository.save(state);
        } else return null;
    }

    public State delete(Long id) {
        Optional<State> cityOptional = stateRepository.findById(id);
        if (cityOptional.isPresent()) {
            State state = cityOptional.get();
            stateRepository.delete(state);
            return state;
        } else return null;
    }

}
