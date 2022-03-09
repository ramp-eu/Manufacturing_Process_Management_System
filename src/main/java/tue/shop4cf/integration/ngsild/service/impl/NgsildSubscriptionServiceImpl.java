package tue.shop4cf.integration.ngsild.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tue.shop4cf.integration.ngsild.dto.SubscriptionDTO;
import tue.shop4cf.integration.ngsild.repository.NgsildSubscriptionRepository;
import tue.shop4cf.integration.ngsild.service.NgsildSubscriptionService;

import java.util.List;
@Service
public class NgsildSubscriptionServiceImpl  implements NgsildSubscriptionService {

    @Autowired
    NgsildSubscriptionRepository ngsildSubscriptionRepository;
    @Override
    public void subscribeEntity() {

        ngsildSubscriptionRepository.subscribeEntity();
    }

    @Override
    public List<SubscriptionDTO> fetchSubscription() {
        List<SubscriptionDTO> list= ngsildSubscriptionRepository.fetchSubscription();
        return list;
    }
}
