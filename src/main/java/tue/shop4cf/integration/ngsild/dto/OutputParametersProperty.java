package tue.shop4cf.integration.ngsild.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OutputParametersProperty {

    @NotBlank
    @JsonProperty("type")
    private String type = "Property";

    @JsonProperty("value")
    private JsonNode value;

//    @JsonProperty("observedAt")
//    private Date observedAt;
}
