package com.voting.dao;

import com.voting.pojo.Item;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ll
 * @since 2019-10-24
 */
public interface ItemMapper extends BaseMapper<Item> {

    List<Map<String, Object>> queryItemStatistical(Integer sid);
}
