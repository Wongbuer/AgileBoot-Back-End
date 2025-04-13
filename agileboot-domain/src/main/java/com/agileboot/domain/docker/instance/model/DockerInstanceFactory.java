package com.agileboot.domain.docker.instance.model;

import cn.hutool.core.bean.BeanUtil;
import com.agileboot.common.core.base.DomainCommand;
import com.agileboot.domain.docker.instance.db.DockerInstanceEntity;
import com.agileboot.domain.docker.instance.dto.DockerInstanceDTO;
import org.springframework.stereotype.Component;

/**
 * @author Wongbuer
 * @date 2025/4/8
 */
@Component
public class DockerInstanceFactory {
     public DockerInstanceModel toDomain(DockerInstanceEntity entity) {
         return BeanUtil.toBean(entity, DockerInstanceModel.class);
     }

     public DockerInstanceModel toDomain(DomainCommand domainCommand) {
         return BeanUtil.toBean(domainCommand, DockerInstanceModel.class);
     }

     public DockerInstanceEntity toEntity(DockerInstanceModel model) {
         return BeanUtil.toBean(model, DockerInstanceEntity.class);
     }

     public DockerInstanceDTO toDTO(DockerInstanceEntity entity) {
         DockerInstanceDTO target = new DockerInstanceDTO();
         target.setId(entity.getId());
         target.setName(entity.getName());
         target.setDescription(entity.getDescription());
         target.setEnabled(entity.getEnabled());
         if (entity.getHost().startsWith("unix")) {
             target.setDockerHost(entity.getSocketPath());
         } else {
             target.setDockerHost("tcp://" + entity.getHost() + ":" + entity.getPort());
         }
         return target;
     }
}
