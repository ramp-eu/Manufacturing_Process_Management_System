package tue.shop4cf.integration.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import tue.shop4cf.integration.ngsild.dto.AssetDTO;
import tue.shop4cf.integration.ngsild.dto.TaskDTO;

import java.net.URI;

@Service
public class CBRESTTemplate {

    @Value("${fiware.orionld-url}")
    private String fiwareServerUrl;

    public Object getVersion() {
        try {
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            HttpEntity httpEntity = new HttpEntity(headers);
            ResponseEntity<Object> responce = restTemplate.exchange(
                    URI.create(fiwareServerUrl + "/version"),
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<Object>() {
                    }
            );

            return responce.getBody();
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }


    public void postAsset(AssetDTO asset) {
        try {
            final String uri = fiwareServerUrl + "/ngsi-ld/v1/entities";
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
//            headers.add("Content-Type", "application/ld+json");
            headers.set("Content-Type", "application/ld+json");
//            headers.set("Link","<https://smartdatamodels.org/context.jsonld;>; rel=\"http://www.w3.org/ns/json-ld#context\"; type=\"application/ld+json\"");
//            headers.set("Link","<https://smartdatamodels.org/context.jsonld>; rel=\"http://www.w3.org/ns/json-ld#context\"; type=\"application/ld+json\",<https://raw.githubusercontent.com/shop4cf/data-models/master/docs/shop4cfcontext.jsonld>; rel=\"http://www.w3.org/ns/json-ld#context\"; type=\"application/ld+json\"");
//            headers.set("Link","{\n" +
//                    "   \"@context\": [\n" +
//                    "      \"https://smartdatamodels.org/context.jsonld\",\n" +
//                    "      \"https://raw.githubusercontent.com/shop4cf/data-models/master/docs/shop4cfcontext.jsonld\"\n" +
//                    "   ]\n" +
//                    "}; rel=\"http://www.w3.org/ns/json-ld#context\"; type=\"application/ld+json\"");

            HttpEntity<AssetDTO> httpEntity = new HttpEntity<AssetDTO>(asset, headers);

            restTemplate.postForObject(uri,
                    httpEntity,
                    Void.class
            );

//           restTemplate.exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<TaskDTO>() {});


        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }


    public void postTask(TaskDTO task) {
        try {
            final String uri = fiwareServerUrl + "/ngsi-ld/v1/entities";
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
//            headers.add("Content-Type", "application/ld+json");
            headers.set("Content-Type", "application/ld+json");
//            headers.set("Link","<https://smartdatamodels.org/context.jsonld;>; rel=\"http://www.w3.org/ns/json-ld#context\"; type=\"application/ld+json\"");
//            headers.set("Link","<https://smartdatamodels.org/context.jsonld>; rel=\"http://www.w3.org/ns/json-ld#context\"; type=\"application/ld+json\",<https://raw.githubusercontent.com/shop4cf/data-models/master/docs/shop4cfcontext.jsonld>; rel=\"http://www.w3.org/ns/json-ld#context\"; type=\"application/ld+json\"");
//            headers.set("Link","{\n" +
//                    "   \"@context\": [\n" +
//                    "      \"https://smartdatamodels.org/context.jsonld\",\n" +
//                    "      \"https://raw.githubusercontent.com/shop4cf/data-models/master/docs/shop4cfcontext.jsonld\"\n" +
//                    "   ]\n" +
//                    "}; rel=\"http://www.w3.org/ns/json-ld#context\"; type=\"application/ld+json\"");


            HttpEntity<TaskDTO> httpEntity = new HttpEntity<TaskDTO>(task, headers);

            restTemplate.postForObject(uri,
                    httpEntity,
                    Void.class
            );

//           restTemplate.exchange(uri, HttpMethod.POST, httpEntity, new ParameterizedTypeReference<TaskDTO>() {});


        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }







}
