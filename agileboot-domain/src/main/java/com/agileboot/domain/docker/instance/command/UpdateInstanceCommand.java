package com.agileboot.domain.docker.instance.command;

import com.agileboot.common.core.base.DomainCommand;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.math3.analysis.function.Add;

/**
 * @author Wongbuer
 * @date 2025/4/8
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UpdateInstanceCommand extends AddInstanceCommand implements DomainCommand {
    private Long id;
}
