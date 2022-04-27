package ru.mipt;

import com.vaadin.flow.component.dependency.NpmPackage;
import com.vaadin.flow.component.page.AppShellConfigurator;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.theme.Theme;
import com.vaadin.flow.theme.lumo.Lumo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import ru.mipt.data.model.Tags;
import ru.mipt.data.repository.TagsRepository;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.*;

/**
 * The entry point of the Spring Boot application.
 * <p>
 * Use the @PWA annotation make the application installable on phones, tablets
 * and some desktop browsers.
 */
@SpringBootApplication
@Theme(value = "ufitness", variant = Lumo.DARK)
@PWA(name = "uFitness", shortName = "uFitness")
@NpmPackage(value = "line-awesome", version = "1.3.0")
@EnableSwagger2
public class Application extends SpringBootServletInitializer implements AppShellConfigurator {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }

    /**
     * Creating init data to Tags table
     *
     * @param tagsRepository basic repository
     */
    @Autowired
    public void initTags(TagsRepository tagsRepository) {
        if (tagsRepository.count() == 0) {
            tagsRepository.saveAll(List.of(
                    new Tags("arms workout"),
                    new Tags("arms & chest"),
                    new Tags("back"),
                    new Tags("biceps"),
                    new Tags("buttocks"),
                    new Tags("calves"),
                    new Tags("cardio"),
                    new Tags("chest"),
                    new Tags("crossfit workout"),
                    new Tags("chest&back"),
                    new Tags("legs"),
                    new Tags("lightweight"),
                    new Tags("press"),
                    new Tags("press & legs"),
                    new Tags("quadriceps"),
                    new Tags("shoulders"),
                    new Tags("stretching"),
                    new Tags("triceps")
            ));
        }
    }

}
