package com.stringcodeltd.myblogapp.service;

import com.stringcodeltd.myblogapp.dto.LoginDTO;
import com.stringcodeltd.myblogapp.dto.RegisterDTO;

public interface AuthService {
    String login(LoginDTO loginDTO);
    String register(RegisterDTO registerDTO);
}
