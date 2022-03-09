package tue.shop4cf.integration.ngsild.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class SubscriptionWADTO extends SubscriptionDTO{

    @JsonProperty("watchedAttributes")
    private List<String> watchedAttributes;

//    @JsonProperty("q")
//    private String query = "";


}
