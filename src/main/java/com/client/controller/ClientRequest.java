package com.client.controller;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientRequest {

    @NotNull
    @Pattern(regexp = "^[PC]{1}$", message = "Client code must be 1 character(P or C)")
    private String documentType;
    @NotNull
    @Pattern(regexp = "^[0-9]+$", message = "Document number must be numeric")
    private String documentNumber;
}
