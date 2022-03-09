package tue.shop4cf.integration.ngsild.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public class BaseProperty {
    @JsonInclude(JsonInclude.Include. NON_NULL)
    @JsonProperty("@type")
    private String type;
}
