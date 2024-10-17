package com.example.skins.configuration.listener;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import com.example.skins.skin.repository.api.SkinRepository;
import com.example.skins.skin.repository.api.CaseRepository;
import com.example.skins.skin.repository.memory.SkinInMemoryRepository;
import com.example.skins.skin.repository.memory.CaseInMemoryRepository;
import com.example.skins.skin.service.SkinService;
import com.example.skins.skin.service.CaseService;
import com.example.skins.crypto.component.Pbkdf2PasswordHash;
import com.example.skins.datastore.component.DataStore;
import com.example.skins.user.repository.api.UserRepository;
import com.example.skins.user.repository.memory.UserInMemoryRepository;
import com.example.skins.user.service.UserService;

/**
 * Listener started automatically on servlet context initialized. Creates an instance of services (business layer) and
 * puts them in the application (servlet) context.
 */
@WebListener//using annotation does not allow configuring order
public class CreateServices implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        DataStore dataSource = (DataStore) event.getServletContext().getAttribute("datasource");

        UserRepository userRepository = new UserInMemoryRepository(dataSource);
        CaseRepository CaseRepository = new CaseInMemoryRepository(dataSource);
        SkinRepository SkinRepository = new SkinInMemoryRepository(dataSource);

        event.getServletContext().setAttribute("userService", new UserService(userRepository, new Pbkdf2PasswordHash()));
        event.getServletContext().setAttribute("SkinService", new SkinService(SkinRepository, CaseRepository, userRepository));
        event.getServletContext().setAttribute("CaseService", new CaseService(CaseRepository));
    }

}
