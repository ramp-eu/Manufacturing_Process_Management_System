package tue.shop4cf.process.startup_system.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import java.net.InetAddress;

import tue.shop4cf.process.common.constants.CommonProcessVariables;
import tue.shop4cf.process.common.delegate.SetCommonVariablesDelegate;
import tue.shop4cf.process.startup_system.constants.ProcessVariables;


@Slf4j
@Component
public class SetStartUpVariablesDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

//        --- Set common variables---
        SetCommonVariablesDelegate setCommonVariablesDelegate = new SetCommonVariablesDelegate();
        setCommonVariablesDelegate.execute(delegateExecution);
        setCommonVariablesDelegate = null;


//        --- Set process specific variables---
        delegateExecution.setVariable(ProcessVariables.ORIONLD_URL_SUBSCRIPTIONS, delegateExecution.getVariable(CommonProcessVariables.ORIONLD_URL).toString() + "/ngsi-ld/v1/subscriptions".toString());

        System.out.println("Host IP:" + InetAddress.getLocalHost().toString());
        System.out.println("Host IP:" + InetAddress.getLocalHost().getHostAddress());

        String localhost_ip = InetAddress.getLocalHost().getHostAddress();
//        String localhost_ip = "192.168.0.12";

//      -----Alert entities-----
        delegateExecution.setVariable(ProcessVariables.ALERT_SUBSCRIPTION_IDENTIFIER, "mpms_alert_subscription".toString());
        delegateExecution.setVariable(ProcessVariables.ALERT_SUBSCRIPTION_DESCRIPTION, "Notify MPMS of created Alert".toString());
//        delegateExecution.setVariable(ProcessVariables.ALERT_ENDPOINT_URI, "http://" + InetAddress.getLocalHost().getHostAddress()  + ":20000/alerts".toString());
        delegateExecution.setVariable(ProcessVariables.ALERT_ENDPOINT_URI, "http://" + localhost_ip  + ":20000/alerts".toString());


//      -----Task entities-----
        delegateExecution.setVariable(ProcessVariables.TASK_SUBSCRIPTION_IDENTIFIER, "mpms_task_subscription".toString());
        delegateExecution.setVariable(ProcessVariables.TASK_SUBSCRIPTION_DESCRIPTION, "Notify MPMS of created Task".toString());
//        delegateExecution.setVariable(ProcessVariables.TASK_ENDPOINT_URI, "http://" + InetAddress.getLocalHost().getHostAddress()  + ":20000/tasks".toString());
        delegateExecution.setVariable(ProcessVariables.TASK_ENDPOINT_URI, "http://" + localhost_ip  + ":20000/tasks".toString());

//      -----Task status-----
        delegateExecution.setVariable(ProcessVariables.TASK_STATUS_SUBSCRIPTION_IDENTIFIER, "mpms_task_status_subscription".toString());
        delegateExecution.setVariable(ProcessVariables.TASK_STATUS_SUBSCRIPTION_DESCRIPTION, "Notify MPMS of Task status changes".toString());
//        delegateExecution.setVariable(ProcessVariables.TASK_STATUS_ENDPOINT_URI, "http://" + InetAddress.getLocalHost().getHostAddress()  + ":20000/tasks/status".toString());
        delegateExecution.setVariable(ProcessVariables.TASK_STATUS_ENDPOINT_URI, "http://" + localhost_ip  + ":20000/tasks/status".toString());

    }
}