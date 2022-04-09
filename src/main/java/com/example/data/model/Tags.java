package com.example.data.model;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.UUID;

public class Tags extends AbstractEntity{
    private String tag;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
