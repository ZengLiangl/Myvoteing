package com.voting.service.impl;

import com.voting.pojo.Options;
import com.voting.dao.OptionsMapper;
import com.voting.service.OptionsService;
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
public class OptionsServiceImpl extends ServiceImpl<OptionsMapper, Options> implements OptionsService {

}
