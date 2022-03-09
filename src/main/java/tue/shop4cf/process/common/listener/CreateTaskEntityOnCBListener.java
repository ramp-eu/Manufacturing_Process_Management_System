package tue.shop4cf.process.common.listener;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateTask;
import org.camunda.bpm.engine.delegate.TaskListener;
import org.springframework.stereotype.Component;
import tue.shop4cf.integration.ngsild.dto.*;
import tue.shop4cf.integration.ngsild.service.CBOperationsService;
import tue.shop4cf.process.test_process.constants.ProcessVariables;

import java.util.*;
import java.util.logging.Logger;

@Slf4j
@Component
public class CreateTaskEntityOnCBListener  implements TaskListener {

    private final static Logger LOGGER = Logger.getLogger(CreateTaskEntityOnCBListener.class.getName());

    private final CBOperationsService orionLDCBOperationsService;
    public CreateTaskEntityOnCBListener(CBOperationsService orionLDCBOperationsService) {
        this.orionLDCBOperationsService = orionLDCBOperationsService;
    }

    @Override
    public void notify(DelegateTask delegateTask) {

//        ---Get the relevant info---
        Long taskId = 0L;
        String taskInstanceId = delegateTask.getId();
        String taskModelerId = delegateTask.getExecution().getCurrentActivityId();
        String taskName = delegateTask.getName();
        String companyName = delegateTask.getExecution().getVariable(ProcessVariables.COMPANY_NAME).toString();

        LOGGER.info("Task Name: " + taskName);
        LOGGER.info("Task (Modeler) Id): " + taskModelerId);
        LOGGER.info("Task Instance Id: " + taskInstanceId);


//        ---Build Task object---
//        TaskDTO task = new TaskDTO();
//        task.setId("urn:ngsi-ld:Task:" + companyName + ":" + taskInstanceId);
//        task.setType("Task");
////        task.setIsDefinedBy("urn:ngsi-ld:TaskDef:"+ companyName +":"+ taskModelerId);
//        task.setStatus("pending");
//

        //        ---isDefinedBy---
        RelationshipProperty isDefinedByProperty = new RelationshipProperty();
        isDefinedByProperty.setObject("urn:ngsi-ld:TaskDef:"+ companyName + ":" + taskModelerId);

        //        ---involves---
        List<RelationshipProperty> involvesResources_lst = new ArrayList<RelationshipProperty>();

        //        ---happensAt---
        List<HappensAtPointProperty> happensAtPoints_lst = new ArrayList<HappensAtPointProperty>();

        //        ---workParameters---
        ObjectMapper objectMapper = new ObjectMapper();
//        JsonNode node = objectMapper.createObjectNode();
        ObjectNode workParameters_objectNode = objectMapper.createObjectNode();

        if (taskModelerId.equals("RTTC_1") || taskModelerId.equals("FC_1")) {

            //Glove resource
            RelationshipProperty glove_resource = new RelationshipProperty();
            glove_resource.setObject("urn:ngsi-ld:Device:"+ companyName + ":"+ "glove-1");
            involvesResources_lst.add(glove_resource);

            HappensAtPointProperty happensAtPoint = new HappensAtPointProperty();
            happensAtPoint.setObject("urn:ngsi-ld:Location:"+ companyName + ":" + "storage");
            CommonProperty locationFunctionProperty = new CommonProperty();
            locationFunctionProperty.setValue("source");
            happensAtPoint.setLocationFunction(locationFunctionProperty);
            happensAtPoints_lst.add(happensAtPoint);

            workParameters_objectNode.put("twp_1", "ValueForParameter1");
            workParameters_objectNode.put("twp_2", "ValueForParameter2");

        }
        else if (taskModelerId.equals("TRC_1") || taskModelerId.equals("SABP") || taskModelerId.equals("SAM") || taskModelerId.equals("SALB") || taskModelerId.equals("SALB") || taskModelerId.equals("CWFAT") || taskModelerId.equals("CWF"))
        {
            //Person resource
            RelationshipProperty person_resource = new RelationshipProperty();
            person_resource.setObject("urn:ngsi-ld:Person:"+ companyName + ":"+ "operator_demonstrator");
            involvesResources_lst.add(person_resource);

            workParameters_objectNode.put("twp_1", "ValueForParameter1");
            workParameters_objectNode.put("twp_2", "ValueForParameter2");

        }
        else if (taskModelerId.equals("RBPC") || taskModelerId.equals("RMC") || taskModelerId.equals("RLBC"))
        {
            //Robot resource
            RelationshipProperty person_resource = new RelationshipProperty();
            person_resource.setObject("urn:ngsi-ld:Device:"+ companyName + ":"+ "Robot-1");
            involvesResources_lst.add(person_resource);

            workParameters_objectNode.put("twp_1", "ValueForParameter1");
            workParameters_objectNode.put("twp_2", "ValueForParameter2");

        }
        else if (taskModelerId.equals("MTWWTOT")) {
            //AGV resource
            RelationshipProperty agv_resource = new RelationshipProperty();
            agv_resource.setObject("urn:ngsi-ld:Device:"+ companyName + ":"+ "agv_1");
            involvesResources_lst.add(agv_resource);

            HappensAtPointProperty happensAtPoint_target = new HappensAtPointProperty();
            if (delegateTask.getExecution().getVariable("tray_type").equals("A"))
            {
                happensAtPoint_target.setObject("urn:ngsi-ld:Location:"+ companyName + ":" + "W1");
            }
            else if (delegateTask.getExecution().getVariable("tray_type").equals("B"))
            {
                happensAtPoint_target.setObject("urn:ngsi-ld:Location:"+ companyName + ":" + "W2");
            }
            else if (delegateTask.getExecution().getVariable("tray_type").equals("C"))
            {
                happensAtPoint_target.setObject("urn:ngsi-ld:Location:"+ companyName + ":" + "W3");
            }
            CommonProperty locationFunctionProperty = new CommonProperty();
            locationFunctionProperty.setValue("target");
            happensAtPoint_target.setLocationFunction(locationFunctionProperty);
            happensAtPoints_lst.add(happensAtPoint_target);

            workParameters_objectNode.put("tray_type", delegateTask.getExecution().getVariable("tray_type").toString());

        }
        else if (taskModelerId.equals("P-UT") || taskModelerId.equals("DTOLSG")) {
            //Robot resource
            RelationshipProperty agv_resource = new RelationshipProperty();
            agv_resource.setObject("urn:ngsi-ld:Device:"+ companyName + ":"+ "robot_1");
            involvesResources_lst.add(agv_resource);

        }
        else if (taskModelerId.equals("MTLS")) {
            //AGV resource
            RelationshipProperty agv_resource = new RelationshipProperty();
            agv_resource.setObject("urn:ngsi-ld:Device:"+ companyName + ":"+ "agv_1");
            involvesResources_lst.add(agv_resource);

            HappensAtPointProperty happensAtPoint_target = new HappensAtPointProperty();
            if (delegateTask.getExecution().getVariable("LS").equals("LS2"))
            {
                happensAtPoint_target.setObject("urn:ngsi-ld:Location:"+ companyName + ":" + "LS2");
            }
            else if (delegateTask.getExecution().getVariable("LS").equals("LS5"))
            {
                happensAtPoint_target.setObject("urn:ngsi-ld:Location:"+ companyName + ":" + "LS5");
            }
            CommonProperty locationFunctionProperty = new CommonProperty();
            locationFunctionProperty.setValue("target");
            happensAtPoint_target.setLocationFunction(locationFunctionProperty);
            happensAtPoints_lst.add(happensAtPoint_target);

        }
        else if (taskModelerId.equals("MTCS")) {
            //AGV resource
            RelationshipProperty agv_resource = new RelationshipProperty();
            agv_resource.setObject("urn:ngsi-ld:Device:"+ companyName + ":"+ "agv_1");
            involvesResources_lst.add(agv_resource);

            HappensAtPointProperty happensAtPoint_target = new HappensAtPointProperty();
            happensAtPoint_target.setObject("urn:ngsi-ld:Location:"+ companyName + ":" + "ACS1");
            CommonProperty locationFunctionProperty = new CommonProperty();
            locationFunctionProperty.setValue("target");
            happensAtPoint_target.setLocationFunction(locationFunctionProperty);
            happensAtPoints_lst.add(happensAtPoint_target);

        }

        InvolvesProperty involvesProperty = new InvolvesProperty();
        involvesProperty.setValue(involvesResources_lst);

        HappensAtProperty happensAtProperty = new HappensAtProperty();
        happensAtProperty.setValue(happensAtPoints_lst);

        WorkParametersProperty workParametersProperty = new WorkParametersProperty();
//        workParametersProperty.setValue(workParameters_objectNode);

        //        ---status---
        TaskStatusProperty taskStatusProperty = new TaskStatusProperty();
        taskStatusProperty.setValue("pending");
//        taskStatusProperty.setObservedAt(Instant.now());

        //        ---outputParameters---
        ObjectNode outputParameters_objectNode = objectMapper.createObjectNode();
        OutputParametersProperty outputParametersProperty = new OutputParametersProperty();
        outputParametersProperty.setValue(outputParameters_objectNode);

        //context
//        String[] context = {"https://smartdatamodels.org/context.jsonld"};
        String[] context = {"https://smartdatamodels.org/context.jsonld","https://raw.githubusercontent.com/shop4cf/data-models/master/docs/shop4cfcontext.jsonld" };

        TaskDTO task = TaskDTO.builder().id("urn:ngsi-ld:Task:" + companyName + ":" + taskInstanceId)
                .type("Task")
                .isDefinedBy(isDefinedByProperty)
                .involves(involvesProperty)
                .happensAt(happensAtProperty)
                .workParameters(workParametersProperty)
                .status(taskStatusProperty)
                .outputParameters(outputParametersProperty)
                .context(context)
                .build();


//        ---Post Task---
        orionLDCBOperationsService.postTask(task);


    }
}
