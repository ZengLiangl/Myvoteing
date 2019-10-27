package com.voting.service.impl;

import com.voting.pojo.Item;
import com.voting.dao.ItemMapper;
import com.voting.service.ItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ll
 * @since 2019-10-24
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

    @Override
    public List<Map<String, Object>> queryItemStatistical(Integer sid) {
        ItemMapper itemMapper = this.getBaseMapper();
        return itemMapper. queryItemStatistical(sid);
    }
}
