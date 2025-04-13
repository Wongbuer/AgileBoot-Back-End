package com.agileboot.domain.docker.instance;

import com.agileboot.common.core.page.PageDTO;
import com.agileboot.domain.common.command.BulkOperationCommand;
import com.agileboot.domain.docker.instance.command.AddInstanceCommand;
import com.agileboot.domain.docker.instance.command.UpdateInstanceCommand;
import com.agileboot.domain.docker.instance.db.DockerInstanceEntity;
import com.agileboot.domain.docker.instance.db.DockerInstanceService;
import com.agileboot.domain.docker.instance.dto.DockerInstanceDTO;
import com.agileboot.domain.docker.instance.model.DockerInstanceFactory;
import com.agileboot.domain.docker.instance.model.DockerInstanceModel;
import com.agileboot.domain.docker.instance.query.DockerInstanceQuery;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Wongbuer
 * @date 2025/4/8
 */
@Service
@RequiredArgsConstructor
public class DockerInstanceApplicationService {

    private final DockerInstanceService dockerInstanceService;
    private final DockerInstanceFactory dockerInstanceFactory;

    public void addInstance(AddInstanceCommand addCommand) {
        DockerInstanceModel instanceModel = dockerInstanceFactory.toDomain(addCommand);
        dockerInstanceService.save(instanceModel);
    }

    public void updateInstance(UpdateInstanceCommand updateCommand) {
        DockerInstanceModel instanceModel = dockerInstanceFactory.toDomain(updateCommand);
        dockerInstanceService.updateById(instanceModel);
    }

    public DockerInstanceDTO getInstanceInfo(Long id) {
        DockerInstanceEntity entity = dockerInstanceService.getById(id);
        return dockerInstanceFactory.toDTO(entity);
    }

    public void deleteInstance(BulkOperationCommand<Long> ids) {
        dockerInstanceService.removeBatchByIds(ids.getIds());
    }

    public PageDTO<DockerInstanceDTO> getInstanceList(DockerInstanceQuery query) {
        Page<DockerInstanceEntity> page = dockerInstanceService.page(query.toPage(), query.toQueryWrapper());
        List<DockerInstanceDTO> records = page.getRecords().stream().map(dockerInstanceFactory::toDTO).collect(Collectors.toList());
        return new PageDTO<>(records, page.getTotal());
    }

    public void testConnectivity(Long id) {
        dockerInstanceService.testConnectivity(id);
    }
}
