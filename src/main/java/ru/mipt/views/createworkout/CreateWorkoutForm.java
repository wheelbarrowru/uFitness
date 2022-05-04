package ru.mipt.views.createworkout;

import com.vaadin.flow.data.value.ValueChangeMode;
import ru.mipt.data.dto.TagsDTO;
import ru.mipt.data.repository.TagsRepository;
import ru.mipt.data.service.TagsService;
import ru.mipt.views.tagsgrid.TagsGrid;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Stream;

/**
 * Create a FormLayout with all our components. The FormLayout
 * doesn't have any logic (validation, etc.), but it allows us to
 * configure responsiveness from Java code and its defaults looks
 * nicer than just using a Div or a VerticalLayout.
 * <p>
 * This FormLayout itself is added to the MainView, where it is made
 * accessible to the user.
 */
public class CreateWorkoutForm extends FormLayout {
    private final TextField title;
    private final TextArea body;

    private final Button button;

    private final Span errorMessageField;

    private final TagsGrid tagsGrid;

    /**
     * Constructor - creating a new form for creating workout
     *
     * @param tagsService basic service
     * @see TagsService#TagsService(TagsRepository)
     */
    public CreateWorkoutForm(@Autowired TagsService tagsService) {
        Component header = new H2("Create your workout");
        title = new TextField("Add your workout's title");

        errorMessageField = new Span();

        body = new TextArea("Add your workout's description");
        body.setWidthFull();
        int charLimit = 1000;
        body.setMaxLength(charLimit);
        body.setValueChangeMode(ValueChangeMode.EAGER);
        body.addValueChangeListener(e -> e.getSource().setHelperText(e.getValue().length() + "/" + charLimit));

        button = new Button("Publish");
        button.setMaxWidth("120px");
        button.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        tagsGrid = new TagsGrid(tagsService);
        tagsGrid.setMaxWidth("320px");
        tagsGrid.setAllowCustomValue(true);

        setRequiredIndicatorVisible(title, body);

        VerticalLayout headerLayout = new VerticalLayout(header);
        headerLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, header);
        headerLayout.setWidthFull();

        VerticalLayout buttonLayout = new VerticalLayout(button);
        buttonLayout.setHorizontalComponentAlignment(FlexComponent.Alignment.CENTER, button);
        buttonLayout.setWidthFull();
        tagsGrid.addClassName("m-s");
        add(headerLayout, title, tagsGrid, body, errorMessageField, buttonLayout);

        setColspan(header, 2);
        setColspan(title, 2);
        setColspan(tagsGrid, 2);
        setColspan(body, 2);
        setColspan(errorMessageField, 2);
        setColspan(buttonLayout, 2);
        setColspan(headerLayout, 2);


        setMaxWidth("800px");
    }

    /**
     * @return workout's saving button
     */
    public Button getButton() {
        return button;
    }

    /**
     * @return workout's error field
     */
    public Span getErrorMessageField() {
        return errorMessageField;
    }

    /**
     * @return workout's title
     */
    public TextField getTitle() {
        return title;
    }

    /**
     * @return workout's description
     */
    public TextArea getBody() {
        return body;
    }

    /**
     * @return workout's tags
     */
    public Set<TagsDTO> getTagsSet() {
        return new HashSet<>(tagsGrid.getTags());
    }

    /**
     * Set indicators for validation
     *
     * @param components components
     */
    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }
}
