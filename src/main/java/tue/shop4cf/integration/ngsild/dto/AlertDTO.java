package tue.shop4cf.integration.ngsild.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.lang.Nullable;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;


@Validated
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true, value = {"validTo"})
public class AlertDTO extends NgsildEntityDTO{
    @JsonInclude(JsonInclude.Include. NON_NULL)
    @JsonProperty("alertSource")
    private RelationshipProperty alertSource;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @JsonProperty("category")
    private CommonProperty category;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @JsonProperty("dateIssued")
    private CommonTypeProperty dateIssued;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @JsonProperty("dateModified")
    private CommonTypeProperty dateModified;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @JsonProperty("severity")
    private CommonProperty severity;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @Nullable
    @JsonProperty("source")
    private CommonProperty source;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @Nullable
    @JsonProperty("subCategory")
    private CommonProperty subCategory;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @JsonProperty("validFrom")
    private CommonTypeProperty validFrom;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @JsonProperty("validTo")
    private CommonTypeProperty validTo;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @Nullable
    @JsonProperty("humanVerified")
    private CommonProperty humanVerified;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @JsonProperty("description")
    private CommonProperty description;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @JsonProperty("seeAlso")
    private CommonProperty seeAlso;

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @JsonProperty("location")
    private LocationProperty location = new LocationProperty();

    @JsonInclude(JsonInclude.Include. NON_NULL)
    @JsonProperty("@context")
    private String[] context;
}
