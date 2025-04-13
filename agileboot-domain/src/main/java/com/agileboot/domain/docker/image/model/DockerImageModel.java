package com.agileboot.domain.docker.image.model;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author Wongbuer
 * @date 2025/4/8
 */
public class DockerImageModel {
    private String hash;

    private String name;

    private Long size;

    private String description;

    private Long ownerId;

    private LocalDateTime created;

    private List<String> repoTags;

    private List<String> repoDigests;

    private Map<String, String> labels;

    private Integer containers;
}
