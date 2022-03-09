package tue.shop4cf.integration.ngsild.controller;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.bpm.engine.variable.Variables;
import org.camunda.bpm.engine.variable.value.ObjectValue;
import org.camunda.spin.Spin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tue.shop4cf.integration.ngsild.dto.AlertDTO;
import tue.shop4cf.integration.ngsild.dto.AlertNotificationDTO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RestController
public class AlertController {

    @Autowired
    RuntimeService runtimeService;

    @RequestMapping(value = "/alerts", method = RequestMethod.POST)
    public AlertNotificationDTO getAlerts(@RequestBody AlertNotificationDTO alertNotificationDTO) throws Exception {
        log.info("Alert notification received by MPMS");

        AlertDTO alertDTO = alertNotificationDTO.getData()[0];

        List<ProcessInstance> pi = new ArrayList<ProcessInstance>();

        pi = runtimeService.createProcessInstanceQuery()
                .processDefinitionKey("Cups_trays_feeding_process")
                .list();

        Map<String, Object> variables = new HashMap<String, Object>();
        variables.put("company_name", "bos");

        if (pi != null && !pi.isEmpty()){
            log.info(pi.get(0).getProcessInstanceId());
            log.info(pi.get(0).getId());
            variables.put("FeedingTrigger_q", Spin.JSON(alertDTO));

            ObjectValue FeedingTrigger_q_DataValue = Variables.objectValue(alertDTO)
                    .serializationDataFormat(Variables.SerializationDataFormats.JSON)
                    .create();
            variables.put("FeedingTrigger_q_Datavalue",FeedingTrigger_q_DataValue);

            String[] alert_source = alertDTO.getAlertSource().getObject().split(":");
            String LS_q = alert_source[alert_source.length - 1];
            variables.put("LS_q", LS_q);
            String tray_type_q = alertDTO.getDescription().getValue();
            variables.put("tray_type_q", tray_type_q);
            String LS_assignee = LS_q + "Οperator";
            variables.put("LS_assignee", LS_assignee);

            MessageCorrelationResult result = runtimeService.createMessageCorrelation("feedingTrigger_msg")
                    .processInstanceId(pi.get(0).getProcessInstanceId())
                    .setVariablesLocal(variables)
                    .correlateWithResult();

//            commonDataAccessor.setEventHandlingProcessInstanceId(pi.get(0).getProcessInstanceId());

            if (result.getExecution() == null) {
                throw new Exception("Unable to correlate 'Feeding trigger' message");
            }
            else {
                log.info("Feeding trigger' " + result.getExecution().getProcessInstanceId() + ".");
            }

        }
        else{
            variables.put("FeedingTrigger", Spin.JSON(alertDTO));

            List <AlertDTO> FeedingTriggersQueue = new ArrayList<AlertDTO>();
            FeedingTriggersQueue.add(alertDTO);
//            variables.put("FeedingTriggersQueue", FeedingTriggersQueue);

            String tray_type = alertDTO.getDescription().getValue();
//            variables.put("tray_type", tray_type);

            ObjectValue FeedingTriggerDataValue = Variables.objectValue(alertDTO)
                    .serializationDataFormat(Variables.SerializationDataFormats.JSON)
                    .create();
            variables.put("FeedingTriggerDatavalue",FeedingTriggerDataValue);

            ObjectValue FeedingTriggersQueueDataValue = Variables.objectValue(FeedingTriggersQueue)
                    .serializationDataFormat(Variables.SerializationDataFormats.JSON)
                    .create();
            variables.put("FeedingTriggersQueue",FeedingTriggersQueueDataValue);

//            ObjectMapper mapper = new ObjectMapper();
//            String jsonStringFeedingTrigger = mapper.writeValueAsString(alertDTO);
//
//            SpinJsonNode FeedingTrigger_json = S(jsonStringFeedingTrigger, json());
//            variables.put("FeedingTrigger_json",FeedingTrigger_json);


            ProcessInstance instance = runtimeService.startProcessInstanceByKey("Cups_trays_feeding_process", variables);
//            commonDataAccessor.setEventHandlingProcessInstanceId(instance.getProcessInstanceId());
        }


        return alertNotificationDTO;
    }
}
