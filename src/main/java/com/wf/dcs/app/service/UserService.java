package com.wf.dcs.app.service;

import com.wf.dcs.app.dto.PageDto;
import com.wf.dcs.app.dto.UserDto;
import org.springframework.data.domain.Pageable;

public interface UserService {

    UserDto save(UserDto userDto);

    UserDto get(Long id);

    PageDto<UserDto> get(Pageable pageable);

    UserDto findUsernameDto(String username);
}
