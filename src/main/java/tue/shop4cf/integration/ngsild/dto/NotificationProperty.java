package tue.shop4cf.integration.ngsild.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NotificationProperty {
    @JsonInclude(JsonInclude.Include. NON_NULL)
    @NotBlank
    @JsonProperty("format")
    private String format;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @NotBlank
    @JsonProperty("endpoint")
    private EndpointProperty endpointProperty;



}
