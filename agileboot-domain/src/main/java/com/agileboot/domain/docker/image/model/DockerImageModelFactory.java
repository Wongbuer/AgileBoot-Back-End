package com.agileboot.domain.docker.image.model;

import cn.hutool.core.date.LocalDateTimeUtil;
import com.agileboot.domain.docker.image.dto.DockerImageDTO;
import com.github.dockerjava.api.model.Image;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Wongbuer
 * @date 2025/4/8
 */
@Component
public class DockerImageModelFactory {
    public DockerImageDTO toDTO(Image image) {
        DockerImageDTO target = new DockerImageDTO();
        target.setHash(image.getId().substring(7));
        target.setCreated(LocalDateTimeUtil.of(image.getCreated()));
        target.setSize(image.getSize());
        if (image.getLabels() != null) {
            Map<String, String> labels = image.getLabels().entrySet().stream().map(entry -> {
                String[] parts = entry.getKey().split("\\.");
                String newKey = parts[parts.length - 1];
                return new AbstractMap.SimpleEntry<>(newKey, entry.getValue());
            }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
            target.setLabels(labels);
        }
        target.setContainers(image.getContainers());
        target.setRepoTags(Arrays.asList(image.getRepoTags()));
        target.setRepoDigests(Arrays.asList(image.getRepoDigests()));
        return target;
    }
}
