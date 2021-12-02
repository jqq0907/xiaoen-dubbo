package com.example.convert;

import com.example.system.UserDto;
import com.example.entity.User;
import org.mapstruct.Mapper;

/**
 * @author jiangqiangqiang
 * @Description:
 * @date 2021/10/28 5:18 下午
 */
@Mapper(componentModel = "spring")
public interface UserConvert {


    UserDto entityToUserDto(User user);
}
