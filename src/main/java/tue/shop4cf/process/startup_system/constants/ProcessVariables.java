package tue.shop4cf.process.startup_system.constants;


import org.camunda.bpm.engine.RuntimeService;
import org.springframework.beans.factory.annotation.Autowired;

public final class ProcessVariables {

    public static final String RESET_SYSTEM = "reset";

    public static final String ORIONLD_URL_SUBSCRIPTIONS= "orionLD_url_subscriptions";

//  public static final String ENTITY_TYPE="entity_type";

    //  -----Alert entities-----
    public static final String ALERT_SUBSCRIPTION_IDENTIFIER="alert_subscription_identifier";
    public static final String ALERT_SUBSCRIPTION_DESCRIPTION="alert_subscription_description";
    public static final String ALERT_ENDPOINT_URI="alert_endpoint_uri";

    //  -----Task entities-----
    public static final String TASK_SUBSCRIPTION_IDENTIFIER="task_subscription_identifier";
    public static final String TASK_SUBSCRIPTION_DESCRIPTION="task_subscription_description";
    public static final String TASK_ENDPOINT_URI="task_endpoint_uri";

    //  -----Task status changes-----
    public static final String TASK_STATUS_SUBSCRIPTION_IDENTIFIER="task_status_subscription_identifier";
    public static final String TASK_STATUS_SUBSCRIPTION_DESCRIPTION="task_status_subscription_description";
    public static final String TASK_STATUS_ENDPOINT_URI="task_status_endpoint_uri";

}
