package com.example.organizationservice.service;

import com.example.organizationservice.model.Organization;
import com.example.organizationservice.repository.OrganizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class OrganizationService {
    private OrganizationRepository repository;

    @Autowired
    public void setRepository(OrganizationRepository repository) {
        this.repository = repository;
    }

    public Organization findById(String organizationId) {
        Optional<Organization> opt = repository.findById(organizationId);
        return (opt.isPresent()) ? opt.get() : null;
    }

    public Organization create(Organization organization){
        organization = repository.save(organization);
        return organization;
    }

    public void update(Organization organization){
        repository.save(organization);
    }

    public void delete(Organization organization){
        repository.deleteById(organization.getId());
    }
}