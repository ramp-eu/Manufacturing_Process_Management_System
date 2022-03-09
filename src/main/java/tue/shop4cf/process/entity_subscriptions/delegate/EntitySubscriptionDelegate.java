package tue.shop4cf.process.entity_subscriptions.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tue.shop4cf.integration.ngsild.service.NgsildSubscriptionService;

@Slf4j
@Component
public class EntitySubscriptionDelegate implements JavaDelegate {


    @Autowired
    private NgsildSubscriptionService ngsildSubscriptionService;


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        ngsildSubscriptionService.subscribeEntity();
    }

}
