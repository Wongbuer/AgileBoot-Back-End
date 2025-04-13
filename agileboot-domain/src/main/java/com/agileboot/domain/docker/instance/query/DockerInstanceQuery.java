package com.agileboot.domain.docker.instance.query;

import cn.hutool.core.util.StrUtil;
import com.agileboot.common.core.page.AbstractPageQuery;
import com.agileboot.domain.docker.instance.db.DockerInstanceEntity;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

/**
 * @author Wongbuer
 * @date 2025/4/8
 */
public class DockerInstanceQuery extends AbstractPageQuery<DockerInstanceEntity> {
    private String instanceName;
    private Boolean enabled;
    @Override
    public QueryWrapper<DockerInstanceEntity> addQueryCondition() {
        QueryWrapper<DockerInstanceEntity> wrapper = new QueryWrapper<>();
        wrapper.like(StrUtil.isNotEmpty(instanceName), "name", instanceName)
                .eq(enabled != null, "enabled", enabled);
        return wrapper;
    }
}
