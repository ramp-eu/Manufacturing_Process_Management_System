package tue.shop4cf.integration.ngsild.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class RelationshipProperty {
    @NotBlank
    @JsonProperty("type")
    private String type = "Relationship";

    @NotBlank
    @JsonProperty("object")
    private String object;
}
