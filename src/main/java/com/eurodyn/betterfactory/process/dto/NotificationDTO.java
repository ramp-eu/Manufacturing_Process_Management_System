package com.eurodyn.betterfactory.process.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
public class NotificationDTO {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private UrlDTO http;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<String> attrs;
}
