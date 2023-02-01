package com.zlennon.chatgpt.model;


import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "req_res_items", schema = "chatgpt", catalog = "")
public class ReqResItemsEntity {
    private Integer id;
    private String reqInfo;
    private String resInfo;
    private String api;
    private Integer type;

    @Basic
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "req_info")
    public String getReqInfo() {
        return reqInfo;
    }

    public void setReqInfo(String reqInfo) {
        this.reqInfo = reqInfo;
    }

    @Basic
    @Column(name = "res_info")
    public String getResInfo() {
        return resInfo;
    }

    public void setResInfo(String resInfo) {
        this.resInfo = resInfo;
    }

    @Basic
    @Column(name = "api")
    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    @Basic
    @Column(name = "type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReqResItemsEntity that = (ReqResItemsEntity) o;
        return Objects.equals(id, that.id) && Objects.equals(reqInfo, that.reqInfo) && Objects.equals(resInfo, that.resInfo) && Objects.equals(api, that.api) && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, reqInfo, resInfo, api, type);
    }
}
