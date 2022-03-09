package tue.shop4cf.integration.ngsild.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;

@Validated
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionDTO{
    @NotBlank
    @JsonProperty("id")
    private String id;

    @NotBlank
    @JsonProperty("description")
    private String description;

    @NotBlank
    @JsonProperty("type")
    private String type;

    @NotBlank
    @JsonProperty("entities")
    private BaseProperty[] entities;

    @NotBlank
    @JsonProperty("notification")
    private NotificationProperty notificationProperty;

    @JsonProperty("@context")
    private String[] context;
//    private String[] context = {"https://smartdatamodels.org/context.jsonld","https://raw.githubusercontent.com/shop4cf/data-models/master/docs/shop4cfcontext.jsonld" };


}
