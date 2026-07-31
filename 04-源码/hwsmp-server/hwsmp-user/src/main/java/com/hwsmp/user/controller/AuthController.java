package com.hwsmp.user.controller;

import com.hwsmp.common.Result;
import com.hwsmp.user.model.dto.LoginRequest;
import com.hwsmp.user.model.vo.LoginVO;
import com.hwsmp.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

/**
 * 用户认证控制器
 */
@Tag(name = "用户认证", description = "登录/验证码/Token刷新")
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @Operation(summary = "发送短信验证码")
    @PostMapping("/sms-code")
    public Result<Void> sendSmsCode(@RequestParam String phone) {
        userService.sendSmsCode(phone);
        return Result.ok("验证码已发送");
    }

    @Operation(summary = "手机号验证码登录")
    @PostMapping("/login")
    public Result<LoginVO> login(@Valid @RequestBody LoginRequest request) {
        return Result.ok(userService.loginByPhone(request));
    }

    @Operation(summary = "退出登录")
    @PostMapping("/logout")
    public Result<Void> logout(@RequestHeader("X-User-Id") Long userId) {
        userService.logout(userId);
        return Result.ok();
    }
}
