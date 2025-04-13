package com.agileboot.domain.docker.instance.db;

import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * Docker实例表 服务类
 * </p>
 *
 * @author Wongbuer
 * @since 2025-04-08
 */
public interface DockerInstanceService extends IService<DockerInstanceEntity> {

    void testConnectivity(Long id);
}
