package tue.shop4cf.integration.ngsild.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WorkParametersProperty {

    @NotBlank
    @JsonProperty("type")
    private String type = "Property";

    @JsonProperty("value")
    private Map<String,String> value;
}
