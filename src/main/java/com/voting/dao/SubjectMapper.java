package com.voting.dao;

import com.voting.pojo.Subject;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ll
 * @since 2019-10-23
 */
public interface SubjectMapper extends BaseMapper<Subject> {

    List<Map<String, Object>> queryAllSubjectInfo(Subject subject);
}
