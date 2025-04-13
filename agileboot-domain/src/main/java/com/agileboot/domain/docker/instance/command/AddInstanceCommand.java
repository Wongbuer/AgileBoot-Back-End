package com.agileboot.domain.docker.instance.command;

import com.agileboot.common.core.base.DomainCommand;
import lombok.Data;

/**
 * @author Wongbuer
 * @date 2025/4/8
 */
@Data
public class AddInstanceCommand implements DomainCommand {
    private String name;

    private String host;

    private Integer port;

    private String socketPath;

    private String description;

    private Boolean enabled = Boolean.TRUE;
}
