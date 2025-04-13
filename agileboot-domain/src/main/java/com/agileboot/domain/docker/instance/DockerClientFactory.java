package com.agileboot.domain.docker.instance;

import cn.hutool.core.util.StrUtil;
import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.common.utils.ip.IpUtil;
import com.agileboot.domain.docker.instance.db.DockerInstanceEntity;
import com.agileboot.domain.docker.instance.db.DockerInstanceService;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static java.lang.String.format;

/**
 * TODO: please input file info
 *
 * @author 枕上江南 zhoutao825638@vip.qq.com
 * @date 2021/12/5 12:09 上午
 */
@Slf4j
@Service
public class DockerClientFactory implements ApplicationContextAware, CommandLineRunner {
    private static final Map<Long, DockerClient> clientMap = new HashMap<>();
    private static ApplicationContext context;

    @SneakyThrows
    public DockerClient get(Long instanceId) {
        DockerClient client = clientMap.get(instanceId);
        if (client == null) {
            // 查看数据库内是否有记录
            DockerInstanceEntity instance = getInstance(instanceId);
            if (instance == null) {
                throw new ApiException(ErrorCode.Business.DOCKER_CLIENT_NOT_FOUND);
            }
            instancingClient(instance);
        }
        return client;
    }

    private static DockerInstanceEntity getInstance(Long instanceId) {
        DockerInstanceService instanceService = context.getBean(DockerInstanceService.class);
        return instanceService.lambdaQuery()
                .eq(DockerInstanceEntity::getId, instanceId)
                .one();
    }

    private static void instancingClient(DockerInstanceEntity instance) throws URISyntaxException {
        if (StrUtil.isNotEmpty(instance.getSocketPath()) && instance.getSocketPath().startsWith("unix")) {
            ApacheDockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                    .dockerHost(new URI(instance.getSocketPath()))
                    .build();
            DockerClient client = DockerClientBuilder.getInstance().withDockerHttpClient(httpClient).build();
            clientMap.put(instance.getId(), client);
        } else if (isValidHost(instance.getHost())) {
            ApacheDockerHttpClient httpClient = new ApacheDockerHttpClient.Builder()
                    .dockerHost(new URI(format("tcp://%s:%s", instance.getHost(), instance.getPort())))

                    .build();
            DockerClient client = DockerClientBuilder.getInstance().withDockerHttpClient(httpClient).build();
            clientMap.put(instance.getId(), client);
        } else {
            throw new ApiException(ErrorCode.Business.DOCKER_CLIENT_CREATE_FAILED);
        }
    }

    @Override
    public void run(String... args) throws Exception {
        DockerInstanceService instanceService = context.getBean(DockerInstanceService.class);
        for (DockerInstanceEntity instance : instanceService.list()) {
            instancingClient(instance);
        }
    }

    private static boolean isValidHost(String host) {
        return IpUtil.isValidIpv4(host) || "localhost".equals(host);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }
}
