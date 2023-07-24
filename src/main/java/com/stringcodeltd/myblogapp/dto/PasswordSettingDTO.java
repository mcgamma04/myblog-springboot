package com.stringcodeltd.myblogapp.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PasswordSettingDTO {
    private String oldpassword;
    private String newpassword;
    private String confirmpassord;
}
