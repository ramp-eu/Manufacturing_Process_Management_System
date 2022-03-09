package tue.shop4cf.integration.ngsild.controller;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.runtime.MessageCorrelationResult;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.camunda.spin.Spin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tue.shop4cf.integration.ngsild.dto.TaskDTO;
import tue.shop4cf.integration.ngsild.dto.TaskNotificationDTO;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
public class TaskLdController {
    @Autowired
    RuntimeService runtimeService;

    static Map<String,Boolean> flags=new HashMap<>();

    @RequestMapping(value = "/taskld", method = RequestMethod.POST)
    public TaskDTO getTask(@RequestBody TaskNotificationDTO taskNotificationDTO) throws Exception {
        log.info("Task notification received by Orion");
        log.info("Notification received: "+ taskNotificationDTO);
        TaskDTO taskDTO = taskNotificationDTO.getData()[0];

        ProcessInstance pi = runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(taskDTO.getId()).singleResult();
        if (pi==null) {
            if(flags.containsKey(taskDTO.getId())){
            }
            else {
                flags.put(taskDTO.getId(),true);
                MessageCorrelationResult result = runtimeService.createMessageCorrelation("IncomingTask")
                        .setVariable("IncomingTask", Spin.JSON(taskDTO))
                        .setVariable("TASK_ID", taskDTO.getId())
                        .processInstanceBusinessKey(taskDTO.getId())
                        .correlateWithResult();
            }
        } else {
            runtimeService.setVariable(pi.getProcessInstanceId(), "IncomingTask", Spin.JSON(taskDTO));
            flags.remove(taskDTO.getId());
        }

        return taskDTO;
    }


}
