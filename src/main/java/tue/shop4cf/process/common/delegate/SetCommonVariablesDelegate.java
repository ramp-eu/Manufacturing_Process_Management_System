package tue.shop4cf.process.common.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import tue.shop4cf.process.common.constants.CommonProcessVariables;

import javax.inject.Inject;
import javax.inject.Named;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Logger;


@Named
public class SetCommonVariablesDelegate {
    private final Logger LOGGER = Logger.getLogger(SetCommonVariablesDelegate.class.getName());

    public void execute(DelegateExecution delegateExecution) throws Exception {

//        delegateExecution.setVariable(CommonProcessVariables.ORIONLD_URL, "http://192.168.1.216:1026");

        //SHOP4CF collab-cloud setup
//        delegateExecution.setVariable(CommonProcessVariables.ORIONLD_URL, "http://shop4cf.collab-cloud.eu:1026");

        //SAG setup
//        delegateExecution.setVariable(CommonProcessVariables.ORIONLD_URL, "http://192.168.0.43:1026");

        //BOS setup
//        delegateExecution.setVariable(CommonProcessVariables.ORIONLD_URL, "http://192.168.0.11:1026");
        delegateExecution.setVariable(CommonProcessVariables.ORIONLD_URL, "http://orion:1026");


        }
}

