package com.lea.api.entity;

public class Resource {

    private Integer id;

    private String url;

    private String description;

    private String requirePermission;

    private Integer delete;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequirePermission() {
        return requirePermission;
    }

    public void setRequirePermission(String requirePermission) {
        this.requirePermission = requirePermission;
    }

    public Integer getDelete() {
        return delete;
    }

    public void setDelete(Integer delete) {
        this.delete = delete;
    }
}
