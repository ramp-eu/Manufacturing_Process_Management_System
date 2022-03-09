package tue.shop4cf.process.test_process.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import java.net.InetAddress;

import tue.shop4cf.process.common.constants.CommonProcessVariables;
import tue.shop4cf.process.common.delegate.SetCommonVariablesDelegate;


@Slf4j
@Component
public class SetTestProcessVariablesDelegate implements JavaDelegate {
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

//        --- Set common variables---
        SetCommonVariablesDelegate setCommonVariablesDelegate = new SetCommonVariablesDelegate();
        setCommonVariablesDelegate.execute(delegateExecution);
        setCommonVariablesDelegate = null;


//        --- Set process specific variables---
        delegateExecution.setVariable(CommonProcessVariables.COMPANY_NAME, "siemens");

        System.out.println("Host IP:" + InetAddress.getLocalHost().toString());
        System.out.println("Host IP:" + InetAddress.getLocalHost().getHostAddress());



    }
}