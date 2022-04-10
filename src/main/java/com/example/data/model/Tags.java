package com.example.data.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.data.annotation.Id;

import javax.persistence.*;
import java.util.Objects;
import java.util.UUID;

@Entity(name = "Tags")
@Table(name = "tags")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tags extends AbstractEntity{
    @Column(name = "message",insertable = false,updatable = false)
    private String message;

    @JsonProperty(value = "id")
    @Id
    @SequenceGenerator(
            name = "id",
            sequenceName = "id",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "id"
    )
    @Column(name = "id",insertable = false,updatable = false)
    private int Id;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Tags tags = (Tags) o;
        return Objects.equals(getId(), tags.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
