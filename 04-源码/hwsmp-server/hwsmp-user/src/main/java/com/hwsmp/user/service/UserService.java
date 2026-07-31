package com.hwsmp.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hwsmp.user.model.dto.LoginRequest;
import com.hwsmp.user.model.entity.User;
import com.hwsmp.user.model.vo.LoginVO;

/**
 * 用户服务接口
 */
public interface UserService extends IService<User> {
    /** 发送验证码 */
    void sendSmsCode(String phone);
    /** 手机号验证码登录 */
    LoginVO loginByPhone(LoginRequest request);
    /** 刷新Token */
    LoginVO refreshToken(String refreshToken);
    /** 退出登录 */
    void logout(Long userId);
}
