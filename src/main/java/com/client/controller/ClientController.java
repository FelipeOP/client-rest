package com.client.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.client.entity.Client;
import com.client.sevice.IClientService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @PostMapping("/findbydocument")
    public ResponseEntity<ClientResponse> findClient(@RequestBody @Valid ClientRequest clientRequest) {
        log.info("new {}", clientRequest.toString());
        Client client = clientService.getClientByDocument(clientRequest);
        if (client == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new ClientResponse(client));
    }

}
