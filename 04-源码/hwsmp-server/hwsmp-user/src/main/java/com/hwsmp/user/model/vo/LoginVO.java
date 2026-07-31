package com.hwsmp.user.model.vo;

import lombok.Builder;
import lombok.Data;

/**
 * 登录响应
 */
@Data
@Builder
public class LoginVO {
    private Long userId;
    private String username;
    private String realName;
    private String phone;
    private String roleCode;
    private String accessToken;
    private String refreshToken;
    private Long expiresIn;
}
