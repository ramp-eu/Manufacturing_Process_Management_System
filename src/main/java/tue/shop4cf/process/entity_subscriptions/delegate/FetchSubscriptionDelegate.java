package tue.shop4cf.process.entity_subscriptions.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tue.shop4cf.config.FiwareProperties;
import tue.shop4cf.integration.ngsild.dto.SubscriptionDTO;
import tue.shop4cf.integration.ngsild.service.impl.NgsildSubscriptionServiceImpl;

import java.util.List;

@Slf4j
@Component
public class FetchSubscriptionDelegate implements JavaDelegate {
    @Autowired
    FiwareProperties fiwareProperties;

    @Autowired
    private NgsildSubscriptionServiceImpl ngsildSubscriptionServiceImpl;

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        List<SubscriptionDTO> list = ngsildSubscriptionServiceImpl.fetchSubscription();
        if (list.toString().contains("mpms_task_subscription"))
        {
            log.info("Already subscribed to entities.");
            delegateExecution.setVariable("subscribed", true);
        }
        else {
            log.info("Subscription not found. Subscribing now...");
            delegateExecution.setVariable("subscribed", false);
        }

    }


}
