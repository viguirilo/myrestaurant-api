package com.myrestaurant.api.service;

import com.myrestaurant.api.entity.Kitchen;
import com.myrestaurant.api.repository.KitchenRepository;
import com.myrestaurant.api.vo.KitchenRequestVO;
import com.myrestaurant.api.vo.KitchenResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class KitchenService {

    private final KitchenRepository kitchenRepository;

    public Kitchen save(KitchenRequestVO kitchenRequestVO) {
        return kitchenRepository.save(new Kitchen(kitchenRequestVO));
    }

    public List<Kitchen> findAll() {
        return kitchenRepository.findAll();
    }

    public KitchenResponseVO findById(Long id) {
        Optional<Kitchen> kitchenOptional = kitchenRepository.findById(id);
        return kitchenOptional.map(KitchenResponseVO::new).orElse(null);
    }

    public Kitchen update(Long id, KitchenRequestVO kitchenRequestVO) {
        Optional<Kitchen> kitchenOptional = kitchenRepository.findById(id);
        if (kitchenOptional.isPresent()) {
            Kitchen kitchen = kitchenOptional.get();
            BeanUtils.copyProperties(kitchenRequestVO, kitchen, "id");
            return kitchenRepository.save(kitchen);
        } else return null;
    }

    public Kitchen delete(Long id) {
        Optional<Kitchen> kitchenOptional = kitchenRepository.findById(id);
        if (kitchenOptional.isPresent()) {
            Kitchen kitchen = kitchenOptional.get();
            kitchenRepository.delete(kitchen);
            return kitchen;
        } else return null;
    }

}
