package tue.shop4cf.integration.ngsild.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class TaskDTO extends NgsildEntityDTO{
//    @NotBlank
//    @JsonProperty("id")
//    private String id;
//
//    @NotBlank
//    @JsonProperty("type")
//    private String type;

//    @JsonProperty ("https://raw.githubusercontent.com/shop4cf/data-models/master/docs/shop4cfdefinitions.jsonld#definitions/isDefinedBy")
    @JsonInclude(JsonInclude.Include. NON_NULL)
    @JsonProperty ("isDefinedBy")
    private RelationshipProperty isDefinedBy;

//    @JsonProperty ("https://raw.githubusercontent.com/shop4cf/data-models/master/docs/shop4cfdefinitions.jsonld#definitions/involves")
    @JsonInclude(JsonInclude.Include. NON_NULL)
    @JsonProperty ("involves")
    private InvolvesProperty involves;

//    @JsonProperty ("https://raw.githubusercontent.com/shop4cf/data-models/master/docs/shop4cfdefinitions.jsonld#definitions/happensAt")
    @JsonInclude(JsonInclude.Include. NON_NULL)
    @JsonProperty ("happensAt")
    private HappensAtProperty happensAt;

//    @JsonProperty ("https://raw.githubusercontent.com/shop4cf/data-models/master/docs/shop4cfdefinitions.jsonld#definitions/workParameters")
    @JsonInclude(JsonInclude.Include. NON_NULL)
    @JsonProperty ("workParameters")
    private WorkParametersProperty workParameters;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @JsonProperty ("status")
    private TaskStatusProperty status;

//    @JsonProperty ("https://raw.githubusercontent.com/shop4cf/data-models/master/docs/shop4cfdefinitions.jsonld#definitions/outputParameters")
    @JsonInclude(JsonInclude.Include. NON_NULL)
    @JsonProperty ("outputParameters")
    private OutputParametersProperty outputParameters;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @JsonProperty("@context")
    private String[] context;
//    private String[] context = {"https://smartdatamodels.org/context.jsonld"};
    //private String[] context = {"https://smartdatamodels.org/context.jsonld","https://raw.githubusercontent.com/shop4cf/data-models/master/docs/shop4cfcontext.jsonld" };

}
