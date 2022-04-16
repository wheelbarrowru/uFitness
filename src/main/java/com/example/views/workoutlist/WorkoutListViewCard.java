package com.example.views.workoutlist;

import com.example.data.dto.TagsDTO;
import com.example.data.dto.WorkoutDTO;
import com.example.data.service.WorkoutService;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;
import org.apache.commons.lang3.StringUtils;

public class WorkoutListViewCard extends ListItem {

    public WorkoutListViewCard(int id, WorkoutService workoutService) {
        addClassNames("bg-contrast-5", "flex", "flex-col", "items-start", "p-m", "rounded-l");

        WorkoutDTO workoutDTO = workoutService.getDTO(id);

        Span header = new Span();
        header.addClassNames("text-xl", "font-semibold");
        header.setText(StringUtils.abbreviate(workoutDTO.getTitle(), 27));

        Span rating = new Span();
        rating.addClassNames("text-l","text-primary-contrast");
        rating.setText(String.valueOf(workoutDTO.getRating()));

        Span subtitle = new Span();
        subtitle.addClassNames("text-s", "text-secondary");
        StringBuffer tagsString = new StringBuffer();
        for (TagsDTO tagsDTO : workoutDTO.getWorkoutTags()) {
            tagsString.append(tagsDTO.getMessage()).append(", ");
        }
        tagsString.deleteCharAt(tagsString.length() - 1);
        tagsString.deleteCharAt(tagsString.length() - 1);
        String tags = tagsString.toString();
        subtitle.setText(StringUtils.abbreviate(tags, 55));

        String shortDescription = StringUtils.abbreviate(workoutDTO.getDescription(), 150);
        StringBuffer longDescription = new StringBuffer(shortDescription);
        if (shortDescription.length() < 150) {
            longDescription.append(" ".repeat(Math.max(0, 150 - shortDescription.length() - shortDescription.length())));
        }

        Paragraph description = new Paragraph(longDescription.toString());
        description.addClassName("my-m");

        Span badge = new Span();
        badge.getElement().setAttribute("theme", "badge");
        badge.setText("Label");

        add(header, rating, subtitle, description);
        addClickListener(e -> UI.getCurrent().navigate("workout/"+id));
    }
}
