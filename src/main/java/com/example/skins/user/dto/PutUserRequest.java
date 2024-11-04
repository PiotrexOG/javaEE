package com.example.skins.user.dto;

import com.example.skins.skin.entity.Skin;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

/**
 * PUT user request. Contains only fields that can be set during user creation. User is defined in
 * {@link com.example.skins.user.entity.User}.
 */
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class PutUserRequest {

    /**
     * User's login.
     */
    private String login;

    /**
     * User's name.
     */
    private String name;

    /**
     * User's surname.
     */
    private String surname;

    /**
     * User's birthdate.
     */
    private LocalDate birthDate;

    /**
     * User's password.
     */
    private String password;

    /**
     * User's email.
     */
    private String email;

    @Singular
    private List<Skin> skins;

}
