package tue.shop4cf.integration.ngsild.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@Accessors(chain = true)
public class CommonProperty {

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @JsonProperty("type")
    private String type = "Property";

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @JsonProperty("value")
    private String value;
}
