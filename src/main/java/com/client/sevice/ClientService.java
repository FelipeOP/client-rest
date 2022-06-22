package com.client.sevice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.client.controller.ClientRequest;
import com.client.entity.Client;
import com.client.repository.IClientRepository;

@Service
public class ClientService implements IClientService {

    @Autowired
    private IClientRepository clientRepository;

    List<Client> clients = List.of(
            new Client(null,
                    "C",
                    "23445322",
                    "Luis",
                    "Felipe",
                    "Salamanca",
                    "Lopez",
                    "3005946322",
                    "Calle 10a # 4-83",
                    "Facatativa"));

    @Override
    public Client getClientByDocument(ClientRequest clientRequest) {
        return clients.stream().filter(client -> client.getDocumentNumber().equals(clientRequest.getDocumentNumber())
                &&
                client.getDocumentType().equals(clientRequest.getDocumentType()))
                .findFirst().orElse(null);
    }
}
