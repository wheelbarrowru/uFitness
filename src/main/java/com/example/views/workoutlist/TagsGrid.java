package com.example.views.workoutlist;

import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.Route;

@Route("grid-dynamic-height")
public class TagsGrid extends Div {

    private static final List<Tags> invitedTags = new ArrayList<Tags>();

    private static Grid<Tags> grid;
    private static Div hint;

    public TagsGrid() {
        this.setupInvitationForm();
        this.setupGrid();
        this.refreshGrid();
    }

    private void setupInvitationForm() {
        //List<Tags> tags = DataService.getPeople();
        List<Tags>  tags = new ArrayList<>();
        tags.add(new Tags("legs"));
        tags.add(new Tags("eyes"));
        ComboBox<Tags> comboBox = new ComboBox<>();
        comboBox.setItems(tags);
        comboBox.setItemLabelGenerator(Tags::getName);

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

    private void setupGrid() {
        grid = new Grid<>(Tags.class, false);
        grid.setAllRowsVisible(true);
        grid.addColumn(Tags::getName).setHeader("Tags");
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

    private void sendInvitation(Tags tags) {
        if (tags == null || invitedTags.contains(tags))
            return;
        invitedTags.add(tags);
        this.refreshGrid();
    }

    private void removeInvitation(Tags tag) {
        if (tag == null)
            return;
        invitedTags.remove(tag);
        this.refreshGrid();
    }

}
