package com.agileboot.domain.docker.image.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * @author Wongbuer
 * @date 2025/4/8
 */
@Data
public class DockerImageDTO {
    private String hash;

    private String name;

    private Long size;

    private String description;

    private Long ownerId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime created;

    private List<String> repoTags;

    private List<String> repoDigests;

    private Map<String, String> labels;

    private Integer containers;
}
