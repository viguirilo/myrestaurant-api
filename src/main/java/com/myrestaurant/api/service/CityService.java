package com.myrestaurant.api.service;

import com.myrestaurant.api.entity.City;
import com.myrestaurant.api.entity.State;
import com.myrestaurant.api.repository.CityRepository;
import com.myrestaurant.api.repository.StateRepository;
import com.myrestaurant.api.vo.CityRequestVO;
import com.myrestaurant.api.vo.CityResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CityService {

    private final CityRepository cityRepository;
    private final StateRepository stateRepository;

    public City save(CityRequestVO cityRequestVO) {
        Optional<State> stateOptional = stateRepository.findById(cityRequestVO.getStateId());
        if (stateOptional.isPresent()) {
            State state = stateOptional.get();
            return cityRepository.save(new City(cityRequestVO, state));
        } else return null;
    }

    public List<City> findAll() {
        return cityRepository.findAll();
    }

    public CityResponseVO findById(Long id) {
        Optional<City> cityOptional = cityRepository.findById(id);
        return cityOptional.map(CityResponseVO::new).orElse(null);
    }

    public City update(Long id, CityRequestVO cityRequestVO) {
        Optional<City> cityOptional = cityRepository.findById(id);
        if (cityOptional.isPresent()) {
            City city = cityOptional.get();
            BeanUtils.copyProperties(cityRequestVO, city, "id");
            return cityRepository.save(city);
        } else return null;
    }

    public City delete(Long id) {
        Optional<City> cityOptional = cityRepository.findById(id);
        if (cityOptional.isPresent()) {
            City city = cityOptional.get();
            cityRepository.delete(city);
            return city;
        } else return null;
    }

}
