package com.example.licensingsevice.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.hateoas.RepresentationModel;

@Getter
@Setter
@ToString
public class Organization extends RepresentationModel<Organization> {
    Integer id;
    String name;
    String contactName;
    String contactEmail;
    String contactPhone;
}