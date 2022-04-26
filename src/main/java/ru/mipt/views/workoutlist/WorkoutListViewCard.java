package ru.mipt.views.workoutlist;

import ru.mipt.data.dto.TagsDTO;
import ru.mipt.data.dto.WorkoutDTO;
import ru.mipt.data.repository.WorkoutRepository;
import ru.mipt.data.service.WorkoutService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;

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
        header.addClassNames("text-xl", "font-semibold");
        header.setText(StringUtils.abbreviate(workoutDTO.getTitle(), 27));

        Span rating = new Span();
        rating.addClassNames("text-l", "text-primary-contrast");
        rating.setText(String.valueOf(workoutDTO.getRating()));

        Span subtitle = new Span();
        subtitle.addClassNames("text-s", "text-secondary");
        StringBuilder tagsString = new StringBuilder();
        for (TagsDTO tagsDTO : workoutDTO.getWorkoutTags()) {
            tagsString.append(tagsDTO.getMessage()).append(", ");
        }
        tagsString.deleteCharAt(tagsString.length() - 1);
        tagsString.deleteCharAt(tagsString.length() - 1);
        String tags = tagsString.toString();
        subtitle.setText(StringUtils.abbreviate(tags, 55));

        String shortDescription = StringUtils.abbreviate(workoutDTO.getDescription(), 150);
        StringBuilder longDescription = new StringBuilder(shortDescription);
        if (shortDescription.length() < 150) {
            longDescription.append(" ".repeat(Math.max(0, 150 - shortDescription.length() - shortDescription.length())));
        }

        Paragraph description = new Paragraph(longDescription.toString());
        description.addClassName("my-m");

        Span badge = new Span();
        badge.getElement().setAttribute("theme", "badge");
        badge.setText("Label");

        add(header, rating, subtitle, description);
        addClickListener(e -> UI.getCurrent().navigate("workout/" + id));
    }
}
