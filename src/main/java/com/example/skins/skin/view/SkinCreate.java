package com.example.skins.skin.view;

import com.example.skins.c4se.model.CaseModel;
import jakarta.enterprise.context.Conversation;
import jakarta.enterprise.context.ConversationScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.extern.java.Log;
import com.example.skins.skin.model.SkinCreateModel;
import com.example.skins.skin.service.SkinService;
import com.example.skins.c4se.service.CaseService;
import com.example.skins.component.ModelFunctionFactory;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * View bean for rendering single Skin create form. Creating a Skin is divided into number of steps where each
 * step is separate JSF view. In order to use single bean, conversation scope is used.
 */
@ConversationScoped
@Named
@Log
@NoArgsConstructor(force = true)
public class SkinCreate implements Serializable {

    /**
     * Service for managing Skins.
     */
    private final SkinService SkinService;

    /**
     * Service for managing Cases.
     */
    private final CaseService CaseService;

    /**
     * Factory producing functions for conversion between models and entities.
     */
    private final ModelFunctionFactory factory;

    /**
     * Skin exposed to the view.
     */
    @Getter
    private SkinCreateModel Skin;

    /**
     * Available Cases.
     */
    @Getter
    private List<CaseModel> Cases;

    /**
     * Injected conversation.
     */
    private final Conversation conversation;

    /**
     * @param SkinService  service for managing Skins
     * @param CaseService service for managing Cases
     * @param factory           factory producing functions for conversion between models and entities
     * @param conversation      injected conversation
     */
    @Inject
    public SkinCreate(
            SkinService SkinService,
            CaseService CaseService,
            ModelFunctionFactory factory,
            Conversation conversation
    ) {
        this.SkinService = SkinService;
        this.factory = factory;
        this.CaseService = CaseService;
        this.conversation = conversation;
    }

    /**
     * In order to prevent calling service on different steps of JSF request lifecycle, model property is cached within
     * field and initialized during init of the view. @PostConstruct method is called after h:form header is already
     * rendered. Conversation should be started in f:metadata/f:event.
     */
    public void init() {
        if (conversation.isTransient()) {
            Cases = CaseService.findAll().stream()
                    .map(factory.CaseToModel())
                    .collect(Collectors.toList());
            Skin = SkinCreateModel.builder()
                    .id(UUID.randomUUID())
                    .build();
            conversation.begin();
        }
    }

    /**
     * @return Case navigation case
     */
    public String goToCaseAction() {
        return "/skin/skin_create__Case.xhtml?faces-redirect=true";
    }

    /**
     * @return skills navigation case
     */
    public String goToSkillsAction() {
        return "/skin/skin_create__skills.xhtml?faces-redirect=true";
    }

    /**
     * @return portrait navigation case
     */
    public String goToPortraitAction() {
        return "/skin/skin_create__portrait.xhtml?faces-redirect=true";
    }

    public String resetPortraitAction() {
        Skin.setPortrait(null);
        return goToPortraitAction();
    }

    /**
     * @return basic information navigation case
     */
    public Object goToBasicAction() {
        return "/skin/skin_create__basic.xhtml?faces-redirect=true";
    }

    /**
     * Cancels Skin creation process.
     *
     * @return Skins list navigation case
     */
    public String cancelAction() {
        conversation.end();
        return "/skin/skin_list.xhtml?faces-redirect=true";
    }

    /**
     * Sets default Skin properties (leve land health).
     *
     * @return confirmation navigation case
     */
    public String goToConfirmAction() {
        Skin.setLevel(1);
        Skin.setExperience(0);
        Skin.setHealth(Skin.getConstitution() == null ? 0 : Skin.getConstitution() * 2);
        return "/skin/skin_create__confirm.xhtml?faces-redirect=true";
    }

    /**
     * Stores new Skin and ends conversation.
     *
     * @return Skins list navigation case
     */
    public String saveAction() {
        SkinService.create(factory.modelToSkin().apply(Skin));
        conversation.end();
        return "/skin/skin_list.xhtml?faces-redirect=true";
    }

    /**
     * @return current conversation id
     */
    public String getConversationId() {
        return conversation.getId();
    }

    public String getSkinPortraitUrl() {
        return "/view/api/v1/Skins/new/portrait?cid=%s".formatted(getConversationId());
    }

}
