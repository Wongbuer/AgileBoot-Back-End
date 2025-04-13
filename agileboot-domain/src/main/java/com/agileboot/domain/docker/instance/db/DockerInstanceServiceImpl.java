package com.agileboot.domain.docker.instance.db;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.docker.instance.DockerClientFactory;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.dockerjava.api.DockerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Docker实例表 服务实现类
 * </p>
 *
 * @author Wongbuer
 * @since 2025-04-08
 */
@Service
@RequiredArgsConstructor
public class DockerInstanceServiceImpl extends ServiceImpl<DockerInstanceMapper, DockerInstanceEntity> implements DockerInstanceService {
    private final DockerClientFactory dockerClientFactory;

    @Override
    public void testConnectivity(Long id) {
        DockerClient client = dockerClientFactory.get(id);
        try {
            client.pingCmd().exec();
        } catch (Exception e) {
            throw new ApiException(ErrorCode.Business.DOCKER_INSTANCE_PING_FAILED);
        }
    }
}
