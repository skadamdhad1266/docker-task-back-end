package com.KnowledgePioneer.backEndTask.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;



@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class PatentInfo {


    @Id
    private String patentId;
    private String patentYear;


    private String patentTitle;

    @Column(length=100000)
    private String patentAbstract;

    @Column(length=10485760)
    private String patentText;

    public String getPatentId() {
        return patentId;
    }

    public void setPatentId(String patentId) {
        this.patentId = patentId;
    }

    public String getPatentAbstract() {
        return patentAbstract;
    }

    public void setPatentAbstract(String patentAbstract) {
        this.patentAbstract = patentAbstract;
    }



    public String getPatentYear() {
        return patentYear;
    }

    public void setPatentYear(String patentYear) {
        this.patentYear = patentYear;
    }

    public String getPatentTitle() {
        return patentTitle;
    }

    public void setPatentTitle(String patentTitle) {
        this.patentTitle = patentTitle;
    }



    public String getPatentText() {
        return patentText;
    }

    public void setPatentText(String patentText) {
        this.patentText = patentText;
    }


}
