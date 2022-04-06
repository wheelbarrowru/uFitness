package com.example.views.workoutlist;

import com.vaadin.flow.component.html.ListItem;
import com.vaadin.flow.component.html.Paragraph;
import com.vaadin.flow.component.html.Span;

public class WorkoutListViewCard extends ListItem {

    public WorkoutListViewCard(String text) {
        addClassNames("bg-contrast-5", "flex", "flex-col", "items-start", "p-m", "rounded-l");


        Span header = new Span();
        header.addClassNames("text-xl", "font-semibold");
        header.setText("Title");

        Span subtitle = new Span();
        subtitle.addClassNames("text-s", "text-secondary");
        subtitle.setText("Tags");

        Paragraph description = new Paragraph(text);
        description.addClassName("my-m");

        Span badge = new Span();
        badge.getElement().setAttribute("theme", "badge");
        badge.setText("Label");

        add(header, subtitle, description);
    }
}
