package tue.shop4cf.process.common.delegate;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;
import tue.shop4cf.integration.ngsild.dto.*;
import tue.shop4cf.integration.ngsild.service.CBOperationsService;
import tue.shop4cf.process.test_process.constants.ProcessVariables;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Slf4j
@Component
public class CreateEntitiesOnCBDelegate implements JavaDelegate {

    private final static Logger LOGGER = Logger.getLogger(CreateEntitiesOnCBDelegate.class.getName());

    private final CBOperationsService orionLDCBOperationsService;
    public CreateEntitiesOnCBDelegate(CBOperationsService orionLDCBOperationsService) {
        this.orionLDCBOperationsService = orionLDCBOperationsService;
    }


    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String companyName = delegateExecution.getVariable(ProcessVariables.COMPANY_NAME).toString();

        //context
        //String[] context = {"https://smartdatamodels.org/context.jsonld"};
        String[] context = {"https://smartdatamodels.org/context.jsonld","https://raw.githubusercontent.com/shop4cf/data-models/master/docs/shop4cfcontext.jsonld" };

//        ---Asset entities---
        //        ---LS2---
        //        ---isSpecifiedBy---
        List<RelationshipProperty> isSpecifiedBy_lst_LS2 = new ArrayList<RelationshipProperty>();

        RelationshipProperty LS2_asset_specification = new RelationshipProperty();
        LS2_asset_specification.setObject("urn:ngsi-ld:ResourceSpecification:"+ companyName + ":"+ "LS2");
        isSpecifiedBy_lst_LS2.add(LS2_asset_specification);

        IsSpecifiedByProperty isSpecifiedByProperty_LS2 = new IsSpecifiedByProperty();
        isSpecifiedByProperty_LS2.setValue(isSpecifiedBy_lst_LS2);

        //        ---description---
        CommonProperty description_property_LS2 = new CommonProperty();
        description_property_LS2.setValue("Loading Station no. 2 for feeding cups trays");

        AssetDTO LS2_asset = AssetDTO.builder().id("urn:ngsi-ld:Asset:" + companyName + ":" + "LS2")
                .type("Asset")
                .description(description_property_LS2)
                .state("available")
                .isSpecifiedBy(isSpecifiedByProperty_LS2)
                .context(context)
                .build();


//        ---Post Asset---
        orionLDCBOperationsService.postAsset(LS2_asset);


        //        ---LS5---
        //        ---isSpecifiedBy---
        List<RelationshipProperty> isSpecifiedBy_lst_LS5 = new ArrayList<RelationshipProperty>();

        RelationshipProperty LS5_asset_specification = new RelationshipProperty();
        LS5_asset_specification.setObject("urn:ngsi-ld:ResourceSpecification:"+ companyName + ":"+ "LS5");
        isSpecifiedBy_lst_LS5.add(LS5_asset_specification);

        IsSpecifiedByProperty isSpecifiedByProperty_LS5 = new IsSpecifiedByProperty();
        isSpecifiedByProperty_LS5.setValue(isSpecifiedBy_lst_LS5);

        //        ---description---
        CommonProperty description_property_LS5 = new CommonProperty();
        description_property_LS5.setValue("Loading Station no. 5 for feeding cups trays");

        AssetDTO LS5_asset = AssetDTO.builder().id("urn:ngsi-ld:Asset:" + companyName + ":" + "LS5")
                .type("Asset")
                .description(description_property_LS5)
                .state("available")
                .isSpecifiedBy(isSpecifiedByProperty_LS5)
                .context(context)
                .build();


//        ---Post Asset---
        orionLDCBOperationsService.postAsset(LS5_asset);
    }
}