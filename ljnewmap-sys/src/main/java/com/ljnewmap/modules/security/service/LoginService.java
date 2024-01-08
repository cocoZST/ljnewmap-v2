package com.ljnewmap.modules.security.service;

import com.ljnewmap.common.utils.RT;
import com.ljnewmap.modules.security.dto.LoginDTO;

public interface LoginService {

    RT login(LoginDTO login);
}
