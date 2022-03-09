package tue.shop4cf.integration.ngsild.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tue.shop4cf.integration.ngsild.repository.impl.NgsildAlertRepositoryImpl;
import tue.shop4cf.integration.ngsild.service.NgsildAlertService;
@Service
public class NgsildAlertServiceImpl implements NgsildAlertService {

    @Autowired
    private NgsildAlertRepositoryImpl ngsildAlertRepositoryImpl;

    @Override
    public void delete(String id) {
        ngsildAlertRepositoryImpl.delete(id);
    }
}
