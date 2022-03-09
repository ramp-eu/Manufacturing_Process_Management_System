package tue.shop4cf.integration.ngsild.service;


import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface NgsildSubscriptionService<T> {
    void subscribeEntity();
    List<T> fetchSubscription();
}
