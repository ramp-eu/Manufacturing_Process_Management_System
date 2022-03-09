package tue.shop4cf.integration.ngsild.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Data
public class CommonLocationProperty {
    @JsonInclude(JsonInclude.Include. NON_NULL)
    @JsonProperty("type")
    private String type;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @NotBlank
    @JsonProperty("coordinates")
    private List<Double> coordinates = new ArrayList<Double>(Arrays.asList(0.0, 0.0, 0.0));
}
