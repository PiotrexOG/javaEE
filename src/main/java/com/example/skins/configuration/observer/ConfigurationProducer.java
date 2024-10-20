package com.example.skins.configuration.observer;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.servlet.ServletContext;

@ApplicationScoped
public class ConfigurationProducer {

    @Inject
    private ServletContext servletContext;

    @Produces
    public String produceAvatarPath() {
        // Pobieranie parametru z ServletContext
        return servletContext.getInitParameter("avatars-path");
    }
}