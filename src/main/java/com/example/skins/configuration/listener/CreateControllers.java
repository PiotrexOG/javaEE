package com.example.skins.configuration.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import com.example.skins.skin.controller.simple.SkinSimpleController;
import com.example.skins.skin.controller.simple.CaseSimpleController;
import com.example.skins.skin.service.SkinService;
import com.example.skins.skin.service.CaseService;
import com.example.skins.component.DtoFunctionFactory;

/**
 * Listener started automatically on servlet context initialized. Creates an instance of controllers and puts them in
 * the application (servlet) context.
 */
@WebListener//using annotation does not allow configuring order
public class CreateControllers implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        SkinService SkinService = (SkinService) event.getServletContext().getAttribute("SkinService");
        CaseService CaseService = (CaseService) event.getServletContext().getAttribute("CaseService");

        event.getServletContext().setAttribute("SkinController", new SkinSimpleController(
                SkinService,
                new DtoFunctionFactory()
        ));

        event.getServletContext().setAttribute("CaseController", new CaseSimpleController(
                CaseService,
                new DtoFunctionFactory()
        ));
    }
}
