package tue.shop4cf.integration.ngsild.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonValueDTO {

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @JsonProperty("@value")
    private String value = new Date().toString();

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @JsonProperty("@type")
    private String type;
}
