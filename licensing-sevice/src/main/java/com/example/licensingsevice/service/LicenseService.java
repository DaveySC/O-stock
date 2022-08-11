package com.example.licensingsevice.service;

import com.example.licensingsevice.config.ServiceConfig;
import com.example.licensingsevice.model.License;
import com.example.licensingsevice.repository.LicenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Random;
import java.util.UUID;

@Service
public class LicenseService {

    MessageSource messages;

    private LicenseRepository licenseRepository;

    ServiceConfig config;

    @Autowired
    public void setMessages(MessageSource messages) {
        this.messages = messages;
    }
    @Autowired
    public void setLicenseRepository(LicenseRepository licenseRepository) {
        this.licenseRepository = licenseRepository;
    }
    @Autowired
    public void setConfig(ServiceConfig config) {
        this.config = config;
    }

    public License getLicense(String licenseId, String organizationId){
        License license = licenseRepository.findByOrganizationIdAndLicenseId(organizationId, licenseId);
        if (license == null) {
            throw new IllegalArgumentException(String.format(messages.getMessage("license.search.error.message", null, null),
                    licenseId, organizationId));
        }
        return license.withComment(config.getProperty());
    }

    public License createLicense(License license){
        license.setLicenseId(UUID.randomUUID().toString());
        licenseRepository.save(license);
        return license.withComment(config.getProperty());
    }

    public License updateLicense(License license){
        licenseRepository.save(license);
        return license.withComment(config.getProperty());
    }

    public String deleteLicense(String licenseId){
        licenseRepository.deleteById(licenseId);
        return  String.format(messages.getMessage("license.delete.message", null, null),licenseId);
    }
}