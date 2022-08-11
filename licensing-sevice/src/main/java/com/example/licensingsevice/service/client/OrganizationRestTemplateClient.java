package com.example.licensingsevice.service.client;

import com.example.licensingsevice.model.Organization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import static org.bouncycastle.asn1.x509.X509ObjectIdentifiers.organization;

@Component
public class OrganizationRestTemplateClient {

    RestTemplate restTemplate;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Organization getOrganization(String organizationId){
        ResponseEntity<Organization> restExchange =
                restTemplate.exchange(
                        "http://organization-service/v1/organization/{organizationId}",
                        HttpMethod.GET, null,
                        Organization.class, organizationId);
        return restExchange.getBody();
    }

}
