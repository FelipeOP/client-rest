package com.client.controller;

import com.client.entity.Client;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientResponse {

    private String firstName;
    private String secondName;
    private String firstSurname;
    private String secondSurname;
    private String phone;
    private String address;
    private String residenceCity;

    // Map client to client response
    public ClientResponse(Client client) {
        this.firstName = client.getFirstName();
        this.secondName = client.getSecondName();
        this.firstSurname = client.getFirstSurname();
        this.secondSurname = client.getSecondSurname();
        this.phone = client.getPhone();
        this.address = client.getAddress();
        this.residenceCity = client.getResidenceCity();
    }

}
