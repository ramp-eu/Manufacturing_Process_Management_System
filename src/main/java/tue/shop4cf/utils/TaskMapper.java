package tue.shop4cf.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.camunda.spin.json.SpinJsonNode;
import tue.shop4cf.integration.ngsild.dto.AlertDTO;
import tue.shop4cf.integration.ngsild.dto.RelationshipProperty;
import tue.shop4cf.integration.ngsild.dto.TaskDTO;

import static org.camunda.spin.Spin.JSON;

@Slf4j
public class TaskMapper {
    public static TaskDTO cast(String taskJSON){
        SpinJsonNode taskJson = JSON(taskJSON);

        log.info("JsonNode: " + taskJSON);
        TaskDTO taskDTO = TaskDTO.builder()
                .id(taskJson.prop("id").toString())
                .type(taskJson.prop("type").toString())
                .isDefinedBy(new RelationshipProperty())
//                .status(taskJson.prop("status"))
                .build();
        log.info("ParsedTask: " + taskDTO);
        return  taskDTO;
    }
}
