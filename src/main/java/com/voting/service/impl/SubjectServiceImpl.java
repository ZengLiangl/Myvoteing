package com.voting.service.impl;

import com.voting.pojo.Subject;
import com.voting.dao.SubjectMapper;
import com.voting.service.SubjectService;
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
 * @since 2019-10-23
 */
@Service
public class SubjectServiceImpl extends ServiceImpl<SubjectMapper, Subject> implements SubjectService {

    @Override
    public List<Map<String, Object>> queryAllSubjectInfo(Subject subject) {
        SubjectMapper subjectMapper = this.getBaseMapper();
        
        return subjectMapper.queryAllSubjectInfo(subject);
    }
}
