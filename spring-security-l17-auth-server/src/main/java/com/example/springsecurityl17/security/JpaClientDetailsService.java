package com.example.springsecurityl17.security;

import com.example.springsecurityl17.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

public class JpaClientDetailsService implements ClientDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientDetails loadClientByClientId(String clientId) throws ClientRegistrationException {
        return new SecurityClient(clientRepository.findClientByClientId(clientId).
                orElseThrow(() -> new ClientRegistrationException("Client not found")));
    }
}
