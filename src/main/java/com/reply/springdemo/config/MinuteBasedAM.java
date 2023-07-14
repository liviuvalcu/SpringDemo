package com.reply.springdemo.config;

import org.springframework.security.authorization.AuthenticatedAuthorizationManager;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.access.intercept.RequestAuthorizationContext;

import java.time.LocalDateTime;
import java.util.function.Supplier;

public class MinuteBasedAM implements AuthorizationManager<RequestAuthorizationContext> {

    @Override
    public void verify(Supplier<Authentication> authentication, RequestAuthorizationContext object) {
        AuthorizationManager.super.verify(authentication, object);
    }

    @Override
    public AuthorizationDecision check(Supplier<Authentication> authentication, RequestAuthorizationContext object) {

       AuthenticatedAuthorizationManager authorizationManager = AuthenticatedAuthorizationManager.authenticated();

       boolean isAuthenticated = authorizationManager.check(authentication, object).isGranted();

       if(!isAuthenticated){
           return new AuthorizationDecision(false);
       }

        boolean granted = authentication.get().getAuthorities().stream().map(GrantedAuthority::getAuthority)
               .filter(r -> "ROLE_USER".equals(r) && LocalDateTime.now().getMinute() % 2 != 0)
               .findAny()
               .map(s -> false)
               .orElse(true);
        return  new AuthorizationDecision(granted);
    }
}
