package tue.shop4cf.integration.ngsild.repository;


import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface NgsildSubscriptionRepository<T> {

    void subscribeEntity();
    List<T> fetchSubscription();
}
