package com.wf.dcs.app.service.impl;

import com.wf.dcs.app.dto.PageDto;
import com.wf.dcs.app.dto.UserDto;
import com.wf.dcs.app.mapper.OrikaBeanMapper;
import com.wf.dcs.app.model.User;
import com.wf.dcs.app.repository.UserRepository;
import com.wf.dcs.app.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private OrikaBeanMapper mapper;

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto save(UserDto userDto) {
        User user = userRepository.findById(userDto.getId());

        if (user == null) {
            user = new User();
        }

        mapper.map(userDto, user);

        return mapper.map(userRepository.save(user), UserDto.class);
    }

    @Override
    public UserDto get(Long id) {
        return mapper.map(userRepository.findById(id), UserDto.class);
    }

    @Override
    public PageDto<UserDto> get(Pageable pageable) {
        Page<User> users = userRepository.findByEnabledFalse(pageable);
        return PageDto.newPageInfo(
            users,
            users
                .getContent()
                .stream()
                .map(user -> mapper.map(user, UserDto.class))
                .collect(Collectors.toList()));
    }

    @Override
    public UserDto findUsernameDto(String username) {
        return mapper.map(userRepository.findByUsername(username), UserDto.class);
    }
}
