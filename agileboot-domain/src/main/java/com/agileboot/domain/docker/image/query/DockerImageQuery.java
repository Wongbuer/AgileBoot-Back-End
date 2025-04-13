package com.agileboot.domain.docker.image.query;

import com.agileboot.common.core.page.AbstractQuery;
import com.agileboot.domain.docker.image.dto.DockerImageDTO;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author Wongbuer
 * @date 2025/4/8
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class DockerImageQuery extends AbstractQuery<DockerImageDTO> {
    private Long instanceId;

    @Override
    public QueryWrapper<DockerImageDTO> addQueryCondition() {
        return null;
    }
}
