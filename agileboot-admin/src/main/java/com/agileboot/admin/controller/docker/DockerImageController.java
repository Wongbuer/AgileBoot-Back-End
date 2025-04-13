package com.agileboot.admin.controller.docker;

import com.agileboot.common.core.base.BaseController;
import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.domain.docker.image.DockerImageApplicationService;
import com.agileboot.domain.docker.image.dto.DockerImageDTO;
import com.agileboot.domain.docker.image.query.DockerImageQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "Docker镜像API", description = "Docker镜像查询")
@RestController
@RequestMapping("/image")
@Validated
@RequiredArgsConstructor
public class DockerImageController extends BaseController {

    private final DockerImageApplicationService dockerImageApplicationService;

    @Operation(summary = "镜像列表查询")
    @PreAuthorize("@permission.has('docker:image:list')")
    @GetMapping("/list")
    public ResponseDTO<List<DockerImageDTO>> list(@Valid DockerImageQuery query) {
        List<DockerImageDTO> imageList = dockerImageApplicationService.getImageList(query);
        return ResponseDTO.ok(imageList);
    }
}
