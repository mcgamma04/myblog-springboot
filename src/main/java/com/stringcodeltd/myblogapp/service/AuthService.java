package com.stringcodeltd.myblogapp.service;

import com.stringcodeltd.myblogapp.dto.*;

public interface AuthService {
    String login(LoginDTO loginDTO);
    String register(RegisterDTO registerDTO);

    String updateUserName(UpdateProfileDTO updateProfileDTO);

    String passwordSetting(PasswordSettingDTO passwordSettingDTO);

    UserDetailsDTO profile();

//    TODO forget password


}
