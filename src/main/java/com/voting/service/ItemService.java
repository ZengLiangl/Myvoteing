package com.voting.service;

import com.voting.pojo.Item;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ll
 * @since 2019-10-24
 */
public interface ItemService extends IService<Item> {

    List<Map<String, Object>> queryItemStatistical(Integer sid);
}
