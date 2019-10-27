package com.voting.service;

import com.voting.pojo.Subject;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author ll
 * @since 2019-10-23
 */
public interface SubjectService extends IService<Subject> {

    List<Map<String, Object>> queryAllSubjectInfo(Subject subject);
}
