package tue.shop4cf.integration.ngsild.repository.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Repository;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;
import tue.shop4cf.config.FiwareProperties;
import tue.shop4cf.integration.ngsild.dto.TaskDTO;
import tue.shop4cf.integration.ngsild.repository.NgsildEntityRepository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Repository
public class NgsildTaskRepositoryImpl implements NgsildEntityRepository<TaskDTO> {

    @Autowired
    private FiwareProperties fiwareProperties;


    @Override
    public void save(TaskDTO taskDTO) {
        try {
            final String uri = fiwareProperties.getOrionldUrl() + "/ngsi-ld/v1/entities/";
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            headers.set("Content-Type", "application/ld+json");
            HttpEntity<?> httpEntity = new HttpEntity<>(taskDTO,headers);
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
    public Optional<TaskDTO> findById(String id) {
        try {
            final String uri = fiwareProperties.getOrionldUrl() + "/ngsi-ld/v1/entities/"+ id;
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            String link = String.valueOf(new StringBuilder()
                    .append("<")
                    .append("https://raw.githubusercontent.com/shop4cf/data-models/master/docs/shop4cfcontext.jsonld")
                    .append(">;")
                    .append("rel=http://www.w3.org/ns/json-ld#context;")
                    .append("type=application/ld+json"));
            headers.set("Link", link);
            HttpEntity httpEntity = new HttpEntity(headers);
            ResponseEntity<?> response = restTemplate.exchange(
                    URI.create(uri),
                    HttpMethod.GET,
                    httpEntity,
                    new ParameterizedTypeReference<Optional<TaskDTO>>() {
                    }
            );
            return (Optional<TaskDTO>)response.getBody();

        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @Override
    public List<TaskDTO> findAllByType() {
        String uri = fiwareProperties.getOrionldUrl() + "/ngsi-ld/v1/entities?type=Task";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        String link = String.valueOf(new StringBuilder()
                .append("<")
                .append("https://raw.githubusercontent.com/shop4cf/data-models/master/docs/shop4cfcontext.jsonld")
                .append(">;")
                .append("rel=http://www.w3.org/ns/json-ld#context;")
                .append("type=application/ld+json"));
        headers.set("Link",link);
        HttpEntity httpEntity = new HttpEntity(headers);

        ResponseEntity<List> response = restTemplate.exchange(
                URI.create(uri),
                HttpMethod.GET,
                httpEntity,
                new ParameterizedTypeReference<List>() {
                }
        );
        List<TaskDTO> entities = response.getBody();
        return entities;
    }

    @Override
    public void delete(String id) {
        try {
            final String uri = fiwareProperties.getOrionldUrl() + "/ngsi-ld/v1/entities/"+ id;
            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();
            HttpEntity httpEntity = new HttpEntity(headers);
            ResponseEntity<String> response = restTemplate.exchange(
                    URI.create(uri),
                    HttpMethod.DELETE,
                    httpEntity,
                    new ParameterizedTypeReference<String>() {
                    }
            );
        } catch (Exception ex) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
        }
    }

    @Override
    public void update(String id, TaskDTO taskDTO) {
        try {
            final String uri = fiwareProperties.getOrionldUrl() + "/ngsi-ld/v1/entities/"+id+"/attrs/";
            String link = String.valueOf(new StringBuilder()
                    .append("<")
                    .append("https://raw.githubusercontent.com/shop4cf/data-models/master/docs/shop4cfcontext.jsonld")
                    .append(">;")
                    .append("rel=http://www.w3.org/ns/json-ld#context;")
                    .append("type=application/ld+json"));

            RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
            HttpHeaders headers = new HttpHeaders();
            headers.set("Link", link);
            headers.set("Content-Type","application/json");
            HttpEntity<?> httpEntity = new HttpEntity<>(taskDTO,headers);
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
