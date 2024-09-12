package com.myrestaurant.api.service;

import com.myrestaurant.api.entity.Group;
import com.myrestaurant.api.repository.GroupRepository;
import com.myrestaurant.api.vo.GroupRequestVO;
import com.myrestaurant.api.vo.GroupResponseVO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class GroupService {

    private final GroupRepository groupRepository;

    public Group save(GroupRequestVO groupRequestVO) {
        return groupRepository.save(new Group(groupRequestVO));
    }

    public List<Group> findAll() {
        return groupRepository.findAll();
    }

    public GroupResponseVO findById(Long id) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        return optionalGroup.map(GroupResponseVO::new).orElse(null);
    }

    public Group update(Long id, GroupRequestVO groupRequestVO) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isPresent()) {
            Group group = optionalGroup.get();
            BeanUtils.copyProperties(groupRequestVO, group, "id");
            return groupRepository.save(group);
        } else return null;
    }

    public Group delete(Long id) {
        Optional<Group> optionalGroup = groupRepository.findById(id);
        if (optionalGroup.isPresent()) {
            Group group = optionalGroup.get();
            groupRepository.delete(group);
            return group;
        } else return null;
    }

}
