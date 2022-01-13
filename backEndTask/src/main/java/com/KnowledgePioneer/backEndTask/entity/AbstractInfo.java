package com.KnowledgePioneer.backEndTask.entity;

import javax.persistence.Embeddable;

@Embeddable
public class AbstractInfo {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAbstract() {
        return Abstract;
    }

    public void setAbstract(String anAbstract) {
        Abstract = anAbstract;
    }

    private String Abstract;
}
