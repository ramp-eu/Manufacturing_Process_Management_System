package tue.shop4cf.integration.ngsild.controller;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.TaskService;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.task.Task;
import org.camunda.spin.Spin;
;
import java.util.List;

import org.camunda.spin.json.SpinJsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import tue.shop4cf.integration.ngsild.dto.TaskNotificationDTO;

import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Iterator;


@Slf4j
@RestController
public class TaskController {

    private final static Logger LOGGER = Logger.getLogger(TaskController.class.getName());

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    protected TaskService taskService;

//    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
//    public void getTasks(@RequestBody String taskNotificationDTO) {
//        log.info(taskNotificationDTO);
//    };


//        @RequestMapping(value = "/tasks/status", method = RequestMethod.POST)
//    public void getTasks(@RequestBody String taskNotificationDTO) {
//            log.info("Here it comes:");
//            log.info(taskNotificationDTO);
//    };


    @RequestMapping(value = "/tasks/status", method = RequestMethod.POST)
    public TaskNotificationDTO getTaskUpdate(@RequestBody TaskNotificationDTO taskNotificationDTO) {
            log.info("There was a status update on a task entity:");
            log.info(taskNotificationDTO.toString());
            SpinJsonNode task_json = Spin.JSON(taskNotificationDTO.getData()[0]);

            String[] urn_id = taskNotificationDTO.getData()[0].getId().split(":");
            String task_instance_id = urn_id[urn_id.length - 1];
            String task_status = taskNotificationDTO.getData()[0].getStatus().getValue();
            log.info("Task_instance_id: " + task_instance_id);
            log.info("Task status: " + task_status);

            List<Task> taskList = taskService.createTaskQuery().taskId(task_instance_id).list();
            if (taskList != null && !taskList.isEmpty()){
                Task task = taskList.get(0);
                String process_instance_id = task.getProcessInstanceId();
                log.info("Process_instance_id: " + process_instance_id);

                if (taskNotificationDTO.getData()[0].getOutputParameters() !=null)
                {
                    JsonNode outputparameters_json = taskNotificationDTO.getData()[0].getOutputParameters().getValue();
                    Iterator<String> fieldNames = outputparameters_json.fieldNames();

                    Iterator<Map.Entry<String, JsonNode>> it = outputparameters_json.fields();
                    while (it.hasNext()) {
                        Map.Entry<String,JsonNode> outputparam = it.next();
                        String name = outputparam.getKey();
                        Object value = outputparam.getValue();
                        runtimeService.setVariable(process_instance_id, name, value);
                    }
                }
//            String process_instance_id = task.getProcessInstanceId();
//            LOGGER.info("Process_instance_id: " + process_instance_id);
                if (task_status.equals("completed")) {
                    taskService.complete(task_instance_id);
//              CamundaRESTCalls restCalls = new CamundaRESTCalls();
//              restCalls.TaskComplete("localhost", "20000", process_instance_id, task_instance_id, task_json.prop("workParameters").toString());
//              restCalls = null;
                }
                else if (task_status.equals("failed"))
                {
//                    throw new BpmnError("agv_unable");

                    taskService.handleBpmnError(
                            task.getId(),
                            "agv_unable_code" // errorCode
                            );

                }
            }
            else
            {
                LOGGER.log(Level.WARNING,"There is no existing task with task-instance-id: " + task_instance_id);
            }

            log.info("---------End of task status notification-------");
            return taskNotificationDTO;

    };


    @RequestMapping(value = "/tasks", method = RequestMethod.POST)
    public TaskNotificationDTO getTasks(@RequestBody TaskNotificationDTO taskNotificationDTO) {
        log.info("Task notification received by MPMS");
        log.info(taskNotificationDTO.toString());
        SpinJsonNode task_json = Spin.JSON(taskNotificationDTO.getData()[0]);

        String[] urn_id = taskNotificationDTO.getData()[0].getId().split(":");
        String task_instance_id = urn_id[urn_id.length - 1];
        String task_status = taskNotificationDTO.getData()[0].getStatus().getValue();
        log.info("Task_instance_id: " + task_instance_id);
        log.info("Task status: " + task_status);

        List<Task> taskList = taskService.createTaskQuery().taskId(task_instance_id).list();
        if (taskList != null && !taskList.isEmpty()){
            Task task = taskList.get(0);
            String process_instance_id = task.getProcessInstanceId();
            log.info("Process_instance_id: " + process_instance_id);

            if (taskNotificationDTO.getData()[0].getOutputParameters() !=null)
            {
                JsonNode outputparameters_json = taskNotificationDTO.getData()[0].getOutputParameters().getValue();
                Iterator<String> fieldNames = outputparameters_json.fieldNames();

                Iterator<Map.Entry<String, JsonNode>> it = outputparameters_json.fields();
                while (it.hasNext()) {
                    Map.Entry<String,JsonNode> outputparam = it.next();
                    String name = outputparam.getKey();
                    Object value = outputparam.getValue();
                    runtimeService.setVariable(process_instance_id, name, value);
                }
            }

//            String process_instance_id = task.getProcessInstanceId();
//            LOGGER.info("Process_instance_id: " + process_instance_id);
            if (task_status.equals("completed")) {
                taskService.complete(task_instance_id);
//              CamundaRESTCalls restCalls = new CamundaRESTCalls();
//              restCalls.TaskComplete("localhost", "20000", process_instance_id, task_instance_id, task_json.prop("workParameters").toString());
//              restCalls = null;
            }
            else
                {

                }
        }
        else
        {
            LOGGER.log(Level.WARNING,"There is no existing task with task-instance-id: " + task_instance_id);
        }

        log.info("---------End of task notification-------");
        return taskNotificationDTO;
    }
}
