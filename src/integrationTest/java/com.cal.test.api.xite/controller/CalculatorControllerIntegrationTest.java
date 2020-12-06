package com.cal.test.api.xite.controller;

import com.cal.test.api.xite.service.CalculatorService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

@RunWith(SpringRunner.class)
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CalculatorControllerIntegrationTest {

    @LocalServerPort
    int port;

    HttpHeaders headers = new HttpHeaders();

    @Autowired
    CalculatorService calculatorService;



    @Test
    public void testCalculateSuccess() throws Exception{
        RestTemplate restTemplate=new RestTemplate();
        HttpEntity<String> entity = new HttpEntity<String>(null, headers);
        String url="http://localhost:"+port+"/calculate?expression=3*3";
        URI uri=new URI(url);
            ResponseEntity<String> responseEntity = restTemplate.exchange(uri,HttpMethod.GET,entity,String.class);
            Assertions.assertEquals(200, responseEntity.getStatusCodeValue());

    }

    @Test
    public void testCalculateFailure() throws Exception{
        RestTemplate restTemplate=new RestTemplate();
        HttpEntity<String> entity=new HttpEntity<>(null,headers);
        String url="http://localhost:"+port+"/calculate?expression=3/";
        URI uri=new URI(url);
        try {
            restTemplate.exchange(uri, HttpMethod.GET, entity, String.class);
            Assert.fail();
        }
        catch (HttpClientErrorException e){
            Assertions.assertEquals(400, e.getRawStatusCode());

        }
    }

}
