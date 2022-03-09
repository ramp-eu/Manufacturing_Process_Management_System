package tue.shop4cf.process.common.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import tue.shop4cf.integration.ngsild.dto.AlertDTO;

@Slf4j
@Component
public class TestDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        log.info("\n\n  ... LoggerDelegate invoked by "
                + "processDefinitionId=" + delegateExecution.getProcessDefinitionId()
                + ", activityId=" + delegateExecution.getCurrentActivityId()
                + ", activityName='" + delegateExecution.getCurrentActivityName().replaceAll("\n", " ") + "'"
                + ", processInstanceId=" + delegateExecution.getProcessInstanceId()
                + ", businessKey=" + delegateExecution.getProcessBusinessKey()
                + ", executionId=" + delegateExecution.getId()
                + ", modelName=" + delegateExecution.getBpmnModelInstance().getModel().getModelName()
                + ", elementId" + delegateExecution.getBpmnModelElementInstance().getId()
                + " \n\n");

        AlertDTO alert = (AlertDTO) delegateExecution.getVariable("FeedingTriggerDatavalue");
        log.info("The alert is : " + alert.getDescription());


        log.info("The queue alert is : " + delegateExecution.getVariable("FeedingTriggersQueue").toString());

    }
}