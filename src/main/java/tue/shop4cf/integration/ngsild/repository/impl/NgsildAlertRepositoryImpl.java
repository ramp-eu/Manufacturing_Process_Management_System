package tue.shop4cf.integration.ngsild.repository.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import tue.shop4cf.config.FiwareProperties;
import tue.shop4cf.integration.ngsild.dto.AlertDTO;
import tue.shop4cf.integration.ngsild.repository.NgsildEntityRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Slf4j
@Repository

public class NgsildAlertRepositoryImpl implements NgsildEntityRepository<AlertDTO> {

    @Autowired
    private FiwareProperties fiwareProperties;


    @Override
    public void save(AlertDTO alertDTO) {
        try {
            log.info(String.valueOf(alertDTO));
            final String uri = fiwareProperties.getOrionldUrl() + "/ngsi-ld/v1/entities/";
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/ld+json");
            HttpEntity<?> httpEntity = new HttpEntity<>(alertDTO,headers);
            log.info(httpEntity.getBody().toString());
            ResponseEntity<String> response = restTemplate.exchange(
                    URI.create(uri),
                    HttpMethod.POST,
                    httpEntity,
                    new ParameterizedTypeReference<String>() {
                    }
            );
        } catch (Exception ex) {
             throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @Override
    public Optional<AlertDTO> findById(String id) {
        try {
            final String uri = fiwareProperties.getOrionldUrl() + "/ngsi-ld/v1/entities/"+ id;
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Link","<https://smartdatamodels.org/context.jsonld>; rel=\"http://www.w3.org/ns/json-ld#context\"; type=\"application/ld+json\"");
            HttpEntity httpEntity = new HttpEntity(headers);
            ResponseEntity<Optional<AlertDTO>> response = restTemplate.exchange(
                    URI.create(uri),
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<Optional<AlertDTO>>() {
                    }
            );
            return (Optional<AlertDTO>)response.getBody();

        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @Override
    public List<AlertDTO> findAllByType() {
        String uri = fiwareProperties.getOrionldUrl() + "/ngsi-ld/v1/entities?type=Alert";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Link","<https://smartdatamodels.org/context.jsonld>; rel=\"http://www.w3.org/ns/json-ld#context\"; type=\"application/ld+json\"");
        HttpEntity httpEntity = new HttpEntity(headers);

        ResponseEntity<List<AlertDTO>> response = restTemplate.exchange(
                URI.create(uri),
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List<AlertDTO>>() {
                }
        );
        List<AlertDTO> entities = response.getBody();
        return entities;
    }

    @Override
    public void delete(String id) {
        final String uri = fiwareProperties.getOrionldUrl() + "/ngsi-ld/v1/entities/"+ id;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set("Link","<https://smartdatamodels.org/context.jsonld>; rel=\"http://www.w3.org/ns/json-ld#context\"; type=\"application/ld+json\"");
        HttpEntity httpEntity = new HttpEntity(headers);
        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.DELETE, httpEntity, String.class);
        log.info(response.toString());
    }

    @Override
    public void update(String id, AlertDTO alertDTO) {
        try {
            final String uri = fiwareProperties.getOrionldUrl() + "/ngsi-ld/v1/entities/"+id+"/attrs/";


            RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
            HttpHeaders headers = new HttpHeaders();
            headers.set("Link","<https://smartdatamodels.org/context.jsonld>; rel=\"http://www.w3.org/ns/json-ld#context\"; type=\"application/ld+json\"");
            headers.set("Content-Type","application/json");
            HttpEntity<?> httpEntity = new HttpEntity<>(alertDTO,headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    URI.create(uri),
                    HttpMethod.PATCH,
                    httpEntity,
                    new ParameterizedTypeReference<String>() {
                    }
            );
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }
}
