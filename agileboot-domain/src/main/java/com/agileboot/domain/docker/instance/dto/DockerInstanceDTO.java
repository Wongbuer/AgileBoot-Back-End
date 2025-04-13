package com.agileboot.domain.docker.instance.dto;

import lombok.Data;

/**
 * @author Wongbuer
 * @date 2025/4/8
 */
@Data
public class DockerInstanceDTO {
    private Long id;

    private String name;

    private String dockerHost;

    private String description;

    private Boolean enabled;
}
