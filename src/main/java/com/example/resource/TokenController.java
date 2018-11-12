package com.example.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("tokens")
public class TokenController {

    @Autowired
    private TokenStore tokenStore;
    @Autowired
    private ConsumerTokenServices tokenServices;

    @GetMapping
    public List<OAuth2AccessToken> tokens() {
        return new ArrayList<>(tokenStore.findTokensByClientId("clientId"));
    }

    @PostMapping("{token}")
    public ResponseEntity revoke(@PathVariable("token") String token) {
        tokenServices.revokeToken(token);
        return ResponseEntity.ok().build();
    }
}
