package com.stringcodeltd.myblogapp.service;

import com.stringcodeltd.myblogapp.dto.LoginDTO;

public interface AuthService {
    String login(LoginDTO loginDTO);
}
