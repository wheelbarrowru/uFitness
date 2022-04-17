package com.example.views.tagsgrid;

import com.example.data.dto.TagsDTO;
import com.example.data.repository.TagsRepository;
import com.example.data.service.TagsService;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Class of window which we use for creating or searching workouts
 */
public class TagsGrid extends Div {

    private final List<TagsDTO> invitedTags = new ArrayList<>();

    private Grid<TagsDTO> grid;
    private Div hint;
    private final Set<TagsDTO> tags;

    /**
     * Constructor - creating a new tags view
     *
     * @param tagsService basic service
     * @see TagsService#TagsService(TagsRepository)
     */
    public TagsGrid(@Autowired TagsService tagsService) {
        this.tags = tagsService.getSetOfDTO();
        this.setupInvitationForm();
        this.setupGrid();
        this.refreshGrid();
    }

    /**
     * Method for creating a form for adding tags
     */
    private void setupInvitationForm() {
        ComboBox<TagsDTO> comboBox = new ComboBox<>();
        comboBox.setItems(tags);
        comboBox.setItemLabelGenerator(TagsDTO::getMessage);

        Button button = new Button("Add tag");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        button.addClickListener(e -> {
            sendInvitation(comboBox.getValue());
            comboBox.setValue(null);
        });

        HorizontalLayout layout = new HorizontalLayout(comboBox, button);
        layout.setFlexGrow(1, comboBox);

        add(layout);
    }

    /**
     * Method for setting up gird
     */
    private void setupGrid() {
        grid = new Grid<>(TagsDTO.class, false);
        grid.setAllRowsVisible(true);
        grid.addColumn(TagsDTO::getMessage).setHeader("Tags");
        grid.addColumn(
                new ComponentRenderer<>(Button::new, (button, tag) -> {
                    button.addThemeVariants(ButtonVariant.LUMO_ICON,
                            ButtonVariant.LUMO_ERROR,
                            ButtonVariant.LUMO_TERTIARY);
                    button.addClickListener(e -> this.removeInvitation(tag));
                    button.setIcon(new Icon(VaadinIcon.TRASH));
                })).setHeader("Manage");

        grid.setItems(invitedTags);

        hint = new Div();
        hint.setText("No tags has been added");
        hint.getStyle().set("padding", "var(--lumo-size-l)")
                .set("text-align", "center").set("font-style", "italic")
                .set("color", "var(--lumo-contrast-70pct)");

        add(hint, grid);
    }

    /**
     * Method for updating grid
     */
    private void refreshGrid() {
        if (invitedTags.size() > 0) {
            grid.setVisible(true);
            hint.setVisible(false);
            grid.getDataProvider().refreshAll();
        } else {
            grid.setVisible(false);
            hint.setVisible(true);
        }
    }

    /**
     * @param tagsDTO DTO of tag entity
     * @see TagsDTO#TagsDTO(int, String)
     */
    private void sendInvitation(TagsDTO tagsDTO) {
        if (tagsDTO == null || invitedTags.contains(tagsDTO))
            return;
        invitedTags.add(tagsDTO);
        this.refreshGrid();
    }

    /**
     * @param tagsDTO DTO of tag entity
     * @see TagsDTO#TagsDTO(int, String)
     */
    private void removeInvitation(TagsDTO tagsDTO) {
        if (tagsDTO == null)
            return;
        invitedTags.remove(tagsDTO);
        this.refreshGrid();
    }

    /**
     * @return invited tags
     * @see TagsDTO#TagsDTO(int, String)
     */
    public List<TagsDTO> getTags() {
        return invitedTags;
    }
}
