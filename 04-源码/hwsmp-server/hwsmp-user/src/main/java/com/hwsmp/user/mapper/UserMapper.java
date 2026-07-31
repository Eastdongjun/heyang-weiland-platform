package com.hwsmp.user.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.hwsmp.user.model.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户 Mapper
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
