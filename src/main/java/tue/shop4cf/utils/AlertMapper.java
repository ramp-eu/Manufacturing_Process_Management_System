package tue.shop4cf.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.spin.json.SpinJsonNode;
import tue.shop4cf.integration.ngsild.dto.AlertDTO;
import lombok.extern.slf4j.Slf4j;

import static org.camunda.spin.Spin.JSON;

@Slf4j
public class AlertMapper {

    public static AlertDTO cast(String alertJSON){
        SpinJsonNode alertJson = JSON(alertJSON);
        log.info("JsonNode: " + alertJSON);
        AlertDTO alertDTO = AlertDTO.builder()
                .id(alertJson.prop("id").toString())
                .type(alertJson.prop("type").toString())
                /*.alertSource(alertJson.prop("alertSource").toString())
                .category(alertJson.prop("category").toString())
                .se

                verity(alertJson.prop("severity").toString())
                .source(alertJson.prop("source").toString())
                .subCategory(alertJson.prop("subCategory").toString())
                .humanVerified(Boolean.valueOf(alertJson.prop("humanVerified").toString()))
                .description(alertJson.prop("description").toString())*/
                .build();
        log.info("ParsedAlert: " + alertDTO);
        return  alertDTO;
    }
}
