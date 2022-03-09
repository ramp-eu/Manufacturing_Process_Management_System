package tue.shop4cf.integration.ngsild.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;


@Data
public class LocationProperty{

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @JsonProperty("type")
    private String type;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    private CommonLocationProperty value;
}
