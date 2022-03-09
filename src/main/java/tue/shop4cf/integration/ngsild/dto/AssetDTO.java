package tue.shop4cf.integration.ngsild.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;


@Validated
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class AssetDTO extends BaseDTO{
    @NotBlank
    @JsonProperty("id")
    private String id;

    @NotBlank
    @JsonProperty("type")
    private String type;

    @JsonProperty("description")
    private CommonProperty description;

    @JsonProperty("state")
    private String state;

    @JsonProperty("isSpecifiedBy")
    private IsSpecifiedByProperty isSpecifiedBy;

    @JsonProperty("@context")
//    private String[] context = {"https://smartdatamodels.org/context.jsonld"};
    private String[] context = {"https://smartdatamodels.org/context.jsonld","https://raw.githubusercontent.com/shop4cf/data-models/master/docs/shop4cfcontext.jsonld" };

}
