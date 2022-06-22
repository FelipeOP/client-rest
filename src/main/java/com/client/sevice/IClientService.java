package com.client.sevice;

import com.client.controller.ClientRequest;
import com.client.entity.Client;

public interface IClientService {

    public Client getClientByDocument(ClientRequest clientRequest);

}
