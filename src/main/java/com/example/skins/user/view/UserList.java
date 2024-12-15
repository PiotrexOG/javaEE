package com.example.skins.user.view;


import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.NoArgsConstructor;
import com.example.skins.component.ModelFunctionFactory;
import com.example.skins.user.model.UsersModel;
import com.example.skins.user.service.UserService;

@RequestScoped
@Named
@NoArgsConstructor(force = true)
public class UserList {
    private final UserService service;

    private UsersModel users;

    private final ModelFunctionFactory factory;

    @Inject
    public UserList(UserService service, ModelFunctionFactory factory) {
        this.service = service;
        this.factory = factory;
    }
    public UsersModel getUsers() {
        if (users == null) {
            users = factory.usersToModel().apply(service.findAll());
        }
        return users;
    }
    public String deleteAction(UsersModel.User user) {
        service.delete(user.getId());
        users = null;
        return "user_list?faces-redirect=true";
    }
}