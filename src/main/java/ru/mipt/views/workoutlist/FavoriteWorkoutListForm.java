package ru.mipt.views.workoutlist;

import com.vaadin.flow.component.HasComponents;
import com.vaadin.flow.component.HasStyle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mipt.data.dto.WorkoutDTO;
import ru.mipt.data.service.UserService;
import ru.mipt.data.service.WorkoutService;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FavoriteWorkoutListForm extends Div implements HasComponents, HasStyle {

    public FavoriteWorkoutListForm(@Autowired UserService userService,
                                   @Autowired WorkoutService workoutService,
                                   Integer param) {
        List<WorkoutDTO> workoutDTOList = userService.getFavoriteWorkouts(param)
                .stream().sorted().collect(Collectors.toList());
        addClassNames("workout-list-view", "max-w-screen-2xl", "mx-xl", "pb-l", "px-m");

        H2 header = new H2("Your favorite workouts");
        header.addClassNames("px-l", "text-3xl");

        OrderedList workoutContainer = new OrderedList();
        workoutContainer.addClassNames("gap-m", "grid", "list-none", "m-0", "px-l");
        for (WorkoutDTO workoutDTO : workoutDTOList) {
            workoutContainer.add(new WorkoutListViewCard(workoutDTO.getId(), workoutService));
        }
        Div hint = new Div();
        hint.setText("There are no workouts with the given tags");
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
}
