package ru.mipt.views.workoutlist;

import com.vaadin.flow.component.HasText;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Span;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import ru.mipt.data.dto.TagsDTO;
import ru.mipt.data.dto.WorkoutDTO;
import ru.mipt.data.repository.WorkoutRepository;
import ru.mipt.data.service.WorkoutService;

/**
 * Front of login class  with <b>ERROR_MESSAGE</b> and <b>id</b> and <b>workoutService</b> properties.
 */
public class WorkoutListViewCard extends ListItem {

    /**
     * Constructor of WorkoutListViewCard
     *
     * @param id             workout's id
     * @param workoutService basic service
     * @see WorkoutService#WorkoutService(WorkoutRepository)
     */
    public WorkoutListViewCard(int id, @Autowired WorkoutService workoutService) {
        addClassNames("bg-contrast-5", "flex", "flex-col", "items-start", "p-m", "rounded-l");

        WorkoutDTO workoutDTO = workoutService.getDTO(id);

        Span header = new Span();
        header.addClassNames("text-xl", "text-header");
        header.setText(StringUtils.abbreviate(workoutDTO.getTitle(), 20));

        Span rating = new Span();
        rating.addClassNames("text-l", "text-primary-contrast");
        rating.setText(String.valueOf(workoutDTO.getRating()));
        rating.getElement().setAttribute("theme", "badge");

        Span subtitle = new Span();
        subtitle.addClassNames("text-s", "text-body");
        StringBuilder tagsString = new StringBuilder();
        for (TagsDTO tagsDTO : workoutDTO.getWorkoutTags()) {
            tagsString.append(tagsDTO.getMessage()).append(", ");
        }
        tagsString.deleteCharAt(tagsString.length() - 1);
        tagsString.deleteCharAt(tagsString.length() - 1);
        String tags = tagsString.toString();
        subtitle.setText(StringUtils.abbreviate(tags, 55));

        String shortDescription = StringUtils.abbreviate(workoutDTO.getDescription(), 145);
        StringBuilder longDescription = new StringBuilder(shortDescription);
        int counter = 0;
        for (int i = 0; i < longDescription.length(); i++) {
            if (longDescription.charAt(i) != ' ') {
                counter++;
            } else {
                counter = 0;
            }
            if (counter > 30) {
                longDescription.insert(i, ' ');
                counter = 0;
            }
        }


        Span description = new Span();
        description.setText(longDescription.toString());
        description.addClassNames("text-m", "text-body");

        add(header, rating, subtitle, description);
        addClickListener(e -> UI.getCurrent().navigate("workout/" + id));
    }
}
