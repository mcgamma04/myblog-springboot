package com.stringcodeltd.myblogapp.service;

import com.stringcodeltd.myblogapp.dto.LoginDTO;
import com.stringcodeltd.myblogapp.dto.RegisterDTO;
import com.stringcodeltd.myblogapp.dto.UpdateProfileDTO;

public interface AuthService {
    String login(LoginDTO loginDTO);
    String register(RegisterDTO registerDTO);

    String updateUserName(UpdateProfileDTO updateProfileDTO);


}
