package com.eurodyn.betterfactory.process.dto;

import com.eurodyn.betterfactory.process.common.BaseDTO;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
public class EntityDTO extends BaseDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String idPattern;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String type;
}
