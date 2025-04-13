package com.agileboot.domain.docker.instance.db;

import com.agileboot.common.core.base.BaseEntity;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * Docker实例表
 * </p>
 *
 * @author Wongbuer
 * @since 2025-04-08
 */
@Getter
@Setter
@TableName("docker_instance")
@ApiModel(value = "DockerInstanceEntity对象", description = "Docker实例表")
public class DockerInstanceEntity extends BaseEntity<DockerInstanceEntity> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("实例ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty("实例名称")
    @TableField("`name`")
    private String name;

    @ApiModelProperty("Docker守护进程地址")
    @TableField("`host`")
    private String host;

    @ApiModelProperty("端口号")
    @TableField("`port`")
    private Integer port;

    @ApiModelProperty("Unix套接字路径")
    @TableField("socket_path")
    private String socketPath;

    @ApiModelProperty("描述信息")
    @TableField("description")
    private String description;

    @ApiModelProperty("是否启用")
    @TableField("enabled")
    private Boolean enabled;


    @Override
    public Serializable pkVal() {
        return this.id;
    }

}
