package com.client.repository;
// JPA Repositoyy for Client

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.client.entity.Client;

@Repository
public interface IClientRepository extends JpaRepository<Client, String>{
}
    

