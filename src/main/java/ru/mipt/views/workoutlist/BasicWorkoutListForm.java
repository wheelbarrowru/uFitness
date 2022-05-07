package ru.mipt.views.workoutlist;

import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.OrderedList;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.server.VaadinSession;
import com.vaadin.flow.server.WebBrowser;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mipt.data.dto.WorkoutDTO;
import ru.mipt.data.service.WorkoutService;

import java.util.List;
import java.util.regex.Pattern;

/**
 * This class is responsible for customer favorite workouts
 */
public class BasicWorkoutListForm extends Div implements HasComponents, HasStyle {
    @Getter
    private final H2 header;

    /**
     * Main constructor
     *
     * @param workoutService basic service
     */
    public BasicWorkoutListForm(@Autowired WorkoutService workoutService, List<WorkoutDTO> workoutDTOList) {
        addClassNames("workout-list-view", "max-w-screen-2xl", "mx-xl", "pb-l", "px-m");
        if (isMobileDevice()) {
            removeClassNames("mx-xl", "pb-l", "px-m");
        }

        header = new H2();
        header.addClassName("px-l");
        if (!isMobileDevice()) {
            header.addClassName("text-3xl");
        }

        OrderedList workoutContainer = new OrderedList();
        workoutContainer.addClassNames("gap-m", "grid", "list-none", "m-0", "px-l");
        for (WorkoutDTO workoutDTO : workoutDTOList) {
            workoutContainer.add(new WorkoutListViewCard(workoutDTO.getId(), workoutService));
        }
        Div hint = new Div();
        hint.setText("Nothing found");
        hint.setWidthFull();
        hint.getStyle().set("padding", "var(--lumo-size-l)")
                .set("text-align", "center")
                .set("color", "var(--lumo-contrast-70pct)");
        if (workoutDTOList.isEmpty()) {
            workoutContainer.add(new ListItem(hint));
        }

        TextField search = new TextField();
        search.setPlaceholder("Workout name");
        search.setWidth("60%");
        search.setClearButtonVisible(true);

        Button searchButton = new Button("Search");
        searchButton.setIcon(VaadinIcon.SEARCH.create());
        searchButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        HorizontalLayout searchLayout = new HorizontalLayout(search, searchButton);
        searchLayout.addClassNames("p-l");

        search.addValueChangeListener(event -> {
                    workoutContainer.removeAll();
                    Pattern pattern = Pattern.compile(String.format(".*%s.*", search.getValue()));
                    for (WorkoutDTO workoutDTO :
                            workoutDTOList) {
                        if (pattern.matcher(workoutDTO.getTitle()).matches()) {
                            workoutContainer.add(new WorkoutListViewCard(workoutDTO.getId(), workoutService));
                        }
                    }
                    if (workoutDTOList.isEmpty()) {
                        workoutContainer.add(new ListItem(hint));
                    }
                }
        );

        add(header, searchLayout, workoutContainer);
    }

    private boolean isMobileDevice() {
        WebBrowser webBrowser = VaadinSession.getCurrent().getBrowser();
        return webBrowser.isAndroid() || webBrowser.isIPhone() || webBrowser.isWindowsPhone() || webBrowser.isSafari();
    }
}
