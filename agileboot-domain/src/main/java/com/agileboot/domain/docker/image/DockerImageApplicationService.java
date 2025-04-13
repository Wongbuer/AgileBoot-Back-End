package com.agileboot.domain.docker.image;

import com.agileboot.common.exception.ApiException;
import com.agileboot.common.exception.error.ErrorCode;
import com.agileboot.domain.docker.image.dto.DockerImageDTO;
import com.agileboot.domain.docker.image.model.DockerImageModelFactory;
import com.agileboot.domain.docker.image.query.DockerImageQuery;
import com.agileboot.domain.docker.instance.DockerClientFactory;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Image;
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
public class DockerImageApplicationService {
    private final DockerClientFactory dockerClientFactory;
    private final DockerImageModelFactory dockerImageModelFactory;

    public List<DockerImageDTO> getImageList(DockerImageQuery query) {
        if (query.getInstanceId() == null) {
            throw new ApiException(ErrorCode.Client.COMMON_REQUEST_PARAMETERS_INVALID, "instanceId");
        }
        DockerClient client = dockerClientFactory.get(query.getInstanceId());
        List<Image> imageList = client.listImagesCmd().exec();
        return imageList.stream().map(dockerImageModelFactory::toDTO).collect(Collectors.toList());
    }
}
