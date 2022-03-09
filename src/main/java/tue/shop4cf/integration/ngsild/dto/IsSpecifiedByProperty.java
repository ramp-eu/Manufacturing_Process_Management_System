package tue.shop4cf.integration.ngsild.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IsSpecifiedByProperty {
    @JsonProperty("type")
    private String type = "Property";

    @JsonProperty("value")
    private List<RelationshipProperty> value;
}
