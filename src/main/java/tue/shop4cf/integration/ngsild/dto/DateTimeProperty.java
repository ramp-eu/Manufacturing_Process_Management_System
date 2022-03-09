package tue.shop4cf.integration.ngsild.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.Date;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DateTimeProperty extends BaseProperty {
    @JsonProperty("@value")
    private Date value = new Date();


}
