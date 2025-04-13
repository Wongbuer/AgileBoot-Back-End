package com.agileboot.admin.controller.docker;

import com.agileboot.admin.customize.aop.accessLog.AccessLog;
import com.agileboot.common.core.base.BaseController;
import com.agileboot.common.core.dto.ResponseDTO;
import com.agileboot.common.core.page.PageDTO;
import com.agileboot.common.enums.common.BusinessTypeEnum;
import com.agileboot.domain.common.command.BulkOperationCommand;
import com.agileboot.domain.docker.instance.DockerInstanceApplicationService;
import com.agileboot.domain.docker.instance.command.AddInstanceCommand;
import com.agileboot.domain.docker.instance.command.UpdateInstanceCommand;
import com.agileboot.domain.docker.instance.dto.DockerInstanceDTO;
import com.agileboot.domain.docker.instance.query.DockerInstanceQuery;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Tag(name = "Docker实例API", description = "Docker实例的增删查改")
@RestController
@RequestMapping("/instance")
@Validated
@RequiredArgsConstructor
public class DockerInstanceController extends BaseController {

    private final DockerInstanceApplicationService dockerInstanceApplicationService;

    @Operation(summary = "实例列表")
    @PreAuthorize("@permission.has('docker:instance:list')")
    @GetMapping("/list")
    public ResponseDTO<PageDTO<DockerInstanceDTO>> list(DockerInstanceQuery query) {
        PageDTO<DockerInstanceDTO> pageDTO = dockerInstanceApplicationService.getInstanceList(query);
        return ResponseDTO.ok(pageDTO);
    }

    @Operation(summary = "实例详情")
    @PreAuthorize("@permission.has('docker:instance:query')")
    @GetMapping("/{id}")
    public ResponseDTO<DockerInstanceDTO> getInfo(@PathVariable Long id) {
        DockerInstanceDTO instanceDTO = dockerInstanceApplicationService.getInstanceInfo(id);
        return ResponseDTO.ok(instanceDTO);
    }

    @Operation(summary = "新增实例")
    @PreAuthorize("@permission.has('docker:instance:add')")
    @AccessLog(title = "实例管理", businessType = BusinessTypeEnum.ADD)
    @PostMapping
    public ResponseDTO<Void> add(@RequestBody AddInstanceCommand addCommand) {
        dockerInstanceApplicationService.addInstance(addCommand);
        return ResponseDTO.ok();
    }

    @Operation(summary = "修改实例")
    @PreAuthorize("@permission.has('docker:instance:edit')")
    @AccessLog(title = "实例管理", businessType = BusinessTypeEnum.MODIFY)
    @PutMapping
    public ResponseDTO<Void> edit(@RequestBody UpdateInstanceCommand updateCommand) {
        dockerInstanceApplicationService.updateInstance(updateCommand);
        return ResponseDTO.ok();
    }

    @Operation(summary = "删除实例")
    @PreAuthorize("@permission.has('docker:instance:remove')")
    @AccessLog(title = "实例管理", businessType = BusinessTypeEnum.DELETE)
    @DeleteMapping
    public ResponseDTO<Void> remove(@RequestParam @NotNull @NotEmpty List<Long> ids) {
        dockerInstanceApplicationService.deleteInstance(new BulkOperationCommand<>(ids));
        return ResponseDTO.ok();
    }

    @Operation(summary = "测试实例连通性")
    @PreAuthorize("@permission.has('docker:instance:test')")
    @AccessLog(title = "实例管理", businessType = BusinessTypeEnum.OTHER)
    @GetMapping("/test/{id}")
    public ResponseDTO<Void> testConnectivity(@PathVariable Long id) {
        dockerInstanceApplicationService.testConnectivity(id);
        return ResponseDTO.ok();
    }
}
