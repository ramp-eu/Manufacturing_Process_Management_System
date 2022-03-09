package tue.shop4cf.process.startup_system.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tue.shop4cf.process.common.constants.CommonProcessVariables;
import tue.shop4cf.process.common.delegate.SetCommonVariablesDelegate;
import tue.shop4cf.process.startup_system.constants.ProcessVariables;

import org.camunda.bpm.engine.runtime.ProcessInstance;
import tue.shop4cf.integration.rest.CamundaRESTCalls;

import javax.inject.Inject;
import javax.inject.Named;
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


@Slf4j
@Component
public class DeleteOldInstancesDelegate implements JavaDelegate {

    @Autowired
    RuntimeService runtimeService;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        System.out.println("Host IP:" + InetAddress.getLocalHost().toString());
        System.out.println("Host IP:" + InetAddress.getLocalHost().getHostAddress());

        String localhost_ip = InetAddress.getLocalHost().getHostAddress();

        log.info("Deleting old instances.." );
        CamundaRESTCalls restCalls = new CamundaRESTCalls();
        List<ProcessInstance> pi = new ArrayList<ProcessInstance>();

        pi = runtimeService.createProcessInstanceQuery()
                .processDefinitionKey("Cups_trays_feeding_process")
                .list();
        if (pi != null && !pi.isEmpty()){
            restCalls.DeleteInstances(localhost_ip,"20000", "Cups_trays_feeding_process" );
        }

//        restCalls.DeleteInstances(resetSystemDataAccessor.getEngineHostAddress().toString(),resetSystemDataAccessor.getEngineHostPort().toString(), "Event_Handling_Process" );
//        restCalls.DeleteInstances(resetSystemDataAccessor.getEngineHostAddress().toString(),resetSystemDataAccessor.getEngineHostPort().toString(), "Order_to_Delivery_Printing_Process" );
//        restCalls.DeleteInstances(resetSystemDataAccessor.getEngineHostAddress().toString(),resetSystemDataAccessor.getEngineHostPort().toString(), "Order_to_Delivery_Printing_Process_Demo_A" );
//        restCalls.DeleteInstances(resetSystemDataAccessor.getEngineHostAddress().toString(),resetSystemDataAccessor.getEngineHostPort().toString(), "Materials_Management" );
//        restCalls.DeleteInstances(resetSystemDataAccessor.getEngineHostAddress().toString(),resetSystemDataAccessor.getEngineHostPort().toString(), "AGV_Management" );
//        restCalls.DeleteInstances(resetSystemDataAccessor.getEngineHostAddress().toString(),resetSystemDataAccessor.getEngineHostPort().toString(), "Recipe_Controller" );
//        restCalls.DeleteInstances(resetSystemDataAccessor.getEngineHostAddress().toString(),resetSystemDataAccessor.getEngineHostPort().toString(), "Test_Process_PPMS_LINC" );
//        restCalls.DeleteInstances(resetSystemDataAccessor.getEngineHostAddress().toString(),resetSystemDataAccessor.getEngineHostPort().toString(), "Database_Query_Process" );

        restCalls = null;

    }

}
