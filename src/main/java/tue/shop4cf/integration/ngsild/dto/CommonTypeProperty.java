package tue.shop4cf.integration.ngsild.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;


@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonTypeProperty {

    @JsonInclude(JsonInclude.Include. NON_NULL)
    private String type;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    private CommonValueDTO value;
}
