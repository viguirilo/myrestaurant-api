package com.myrestaurant.api.service;

import com.myrestaurant.api.entity.City;
import com.myrestaurant.api.repository.CityRepository;
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

    public City save(CityRequestVO cityRequestVO) {
        return cityRepository.save(new City(cityRequestVO));
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
            City kitchen = cityOptional.get();
            BeanUtils.copyProperties(cityRequestVO, kitchen, "id");
            return cityRepository.save(kitchen);
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
