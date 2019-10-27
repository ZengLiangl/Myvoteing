package com.voting.service.impl;

import com.voting.pojo.Users;
import com.voting.dao.UsersMapper;
import com.voting.service.UsersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ll
 * @since 2019-10-23
 */
@Service
public class UsersServiceImpl extends ServiceImpl<UsersMapper, Users> implements UsersService {

}
