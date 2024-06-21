package com.picpaysimplificado.picpaysimplificado.dtos;

public record AuthorizationDTO(
        String authorization
) {

    public boolean isAuthorized(){
        return authorization.equals("true");
    }
}
