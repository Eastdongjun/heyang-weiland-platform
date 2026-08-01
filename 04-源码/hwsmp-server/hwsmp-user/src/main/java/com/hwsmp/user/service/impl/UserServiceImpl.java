package com.hwsmp.user.service.impl;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hwsmp.common.constant.SysConstants;
import com.hwsmp.common.exception.BizException;
import com.hwsmp.user.mapper.UserMapper;
import com.hwsmp.user.model.dto.LoginRequest;
import com.hwsmp.user.model.entity.User;
import com.hwsmp.user.model.vo.LoginVO;
import com.hwsmp.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * 用户服务实现
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired(required = false)
    private StringRedisTemplate redisTemplate;
    private static final String SMS_CODE_PREFIX = "sms:code:";
    private static final String SMS_RATE_PREFIX = "sms:rate:";

    @Override
    public void sendSmsCode(String phone) {
        // 开发环境：无 Redis 时直接返回成功
        if (redisTemplate == null) {
            log.info("📱 [DEV] 验证码发送到 {}: 888888 (无需Redis)", phone);
            return;
        }
        String rateKey = SMS_RATE_PREFIX + phone;
        if (StrUtil.isNotBlank(redisTemplate.opsForValue().get(rateKey))) {
            throw new BizException("验证码发送过于频繁，请60秒后重试");
        }
        String dailyKey = "sms:daily:" + phone;
        String dailyCount = redisTemplate.opsForValue().get(dailyKey);
        if (dailyCount != null && Integer.parseInt(dailyCount) >= 5) {
            throw new BizException("今日验证码发送次数已达上限");
        }

        String code = RandomUtil.randomNumbers(6);
        log.info("📱 验证码发送到 {}: {}", phone, code);

        redisTemplate.opsForValue().set(SMS_CODE_PREFIX + phone, code, 5, TimeUnit.MINUTES);
        redisTemplate.opsForValue().set(rateKey, "1", 60, TimeUnit.SECONDS);
        redisTemplate.opsForValue().increment(dailyKey);
        redisTemplate.expire(dailyKey, 1, TimeUnit.DAYS);
    }

    @Override
    public LoginVO loginByPhone(LoginRequest request) {
        String phone = request.getPhone();
        String code = request.getCode();

        // 开发环境万能验证码 888888
        if (!"888888".equals(code)) {
            if (redisTemplate == null) {
                throw new BizException("验证码错误 (开发环境请使用 888888)");
            }
            String cachedCode = redisTemplate.opsForValue().get(SMS_CODE_PREFIX + phone);
            if (cachedCode == null || !cachedCode.equals(code)) {
                throw new BizException("验证码错误或已过期");
            }
            redisTemplate.delete(SMS_CODE_PREFIX + phone);
        }

        User user = getOne(new LambdaQueryWrapper<User>().eq(User::getPhone, phone));
        if (user == null) {
            user = new User();
            user.setPhone(phone);
            user.setUsername("user_" + phone.substring(7));
            user.setRealName("用户" + phone.substring(7));
            user.setRoleCode(SysConstants.ROLE_RANGER);
            user.setStatus(1);
            save(user);
        }

        user.setLastLoginAt(LocalDateTime.now());
        updateById(user);

        String accessToken = "jwt_access_" + user.getId() + "_" + System.currentTimeMillis();
        String refreshToken = "jwt_refresh_" + user.getId() + "_" + System.currentTimeMillis();

        if (redisTemplate != null) {
            redisTemplate.opsForValue().set("token:access:" + user.getId(), accessToken, 30, TimeUnit.MINUTES);
            redisTemplate.opsForValue().set("token:refresh:" + user.getId(), refreshToken, 7, TimeUnit.DAYS);
        }

        return LoginVO.builder()
                .userId(user.getId()).username(user.getUsername())
                .realName(user.getRealName()).phone(user.getPhone())
                .roleCode(user.getRoleCode())
                .accessToken(accessToken).refreshToken(refreshToken)
                .expiresIn(1800L).build();
    }

    @Override
    public LoginVO refreshToken(String refreshToken) {
        throw new BizException("功能开发中");
    }

    @Override
    public void logout(Long userId) {
        if (redisTemplate != null) {
            redisTemplate.delete("token:access:" + userId);
            redisTemplate.delete("token:refresh:" + userId);
        }
    }
}
