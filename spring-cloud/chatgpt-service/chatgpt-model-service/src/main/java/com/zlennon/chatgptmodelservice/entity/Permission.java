package com.zlennon.chatgptmodelservice.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Table(name = "model_permission")
@Entity
@Data
public class Permission implements Serializable {

    @Id
    public String id;

    @ManyToOne
    @JoinColumn(name = "model_id")
    public Model model;

    public String object;
    public long created;
    public boolean allowCreateEngine;
    public boolean allowSampling;
    public boolean allowLogProbs;
    public boolean allowSearchIndices;
    public boolean allowView;
    public boolean allowFineTuning;
    public String organization;
    @Column(name="group_info")
    public String group;

    public boolean isBlocking;


    public Permission() {
    }
}
