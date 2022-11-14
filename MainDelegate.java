package com.example.workflow;

//import java.util.logging.Logger;
import camundajar.impl.com.google.gson.Gson;
import ch.qos.logback.core.CoreConstants;
import ch.qos.logback.core.net.SyslogOutputStream;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@Component
public class MainDelegate implements JavaDelegate {

    //private final static Logger LOGGER = Logger.getLogger("LOAN-REQUESTS");
    @Autowired
    private RestTemplate restTemplate;
    public void execute(DelegateExecution execution) throws Exception {
        //LOGGER.info("Processing request by '" + execution.getVariable("customerId")
        // + "'...");
        //https://github.com/Java-Techie-jt/spring-boot-soap-ws-consumer/tree/master/src/main/java/com/javatechie/spring/soap/api
        
        ObjectMapper mapper = new ObjectMapper();
        final String baseUrl = "https://jsonplaceholder.typicode.com/todos/1";
        URI uri = new URI(baseUrl);
        ResponseEntity<Object> responseEntity = restTemplate.getForEntity(uri, Object.class);
        //Object res = responseEntity.getBody();
        String json = mapper.writeValueAsString(responseEntity.getBody());
        //Gson a=new Gson().fromJson(json);
        MediaType contentType = responseEntity.getHeaders().getContentType();
        HttpStatus statusCode = responseEntity.getStatusCode();
        System.out.println("############################3"+ json);

    }

}