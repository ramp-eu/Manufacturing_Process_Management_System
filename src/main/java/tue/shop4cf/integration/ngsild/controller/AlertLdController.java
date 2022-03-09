package tue.shop4cf.integration.ngsild.controller;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.camunda.spin.Spin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tue.shop4cf.integration.ngsild.dto.AlertDTO;
import tue.shop4cf.integration.ngsild.dto.AlertNotificationDTO;

@Slf4j
@RestController
public class AlertLdController {

    @Autowired
    RuntimeService runtimeService;

    @RequestMapping(value = "/alertld", method = RequestMethod.POST)
    public AlertDTO getAlert(@RequestBody AlertNotificationDTO alertNotificationDTO) throws Exception {
        log.info("Alert notification received by Orion");
        log.info("Notification received: "+ alertNotificationDTO);
        AlertDTO alertDTO = alertNotificationDTO.getData()[0];
        MessageCorrelationResult result = runtimeService.createMessageCorrelation("IncomingAlert")
                .setVariable("IncomingAlert", Spin.JSON(alertDTO))
                .setVariable("ALERT_ID", alertDTO.getId())
                .processInstanceBusinessKey(alertDTO.getId())
                .correlateWithResult();
        return alertDTO;
    }
}