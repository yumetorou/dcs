package com.wf.dcs.app.resource;

import com.wf.dcs.app.dto.UserDto;
import com.wf.dcs.app.service.UserService;
import com.wf.dcs.app.util.UserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
public class AuthenticationResource {

    private static final Logger LOG = LoggerFactory.getLogger(AuthenticationResource.class);

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/currentUser", method = GET)
    public ResponseEntity<UserDto> currentlyLoggedInUser() {
        return new ResponseEntity<>(userService.findUsernameDto(UserUtil.getCurrentUserName()), OK);
    }
}
