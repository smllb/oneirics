package com.lucid.oneiric.dto;

import org.springframework.security.core.userdetails.User;

public class UserPublicViewDTO {

    private String login;
    private Integer lucidDreamCount;
    private Integer regularDreamCount;

    public UserPublicViewDTO() {

    }

    public UserPublicViewDTO(String login, Integer lucidDreamCount, Integer regularDreamCount) {
        this.login = login;
        this.lucidDreamCount = lucidDreamCount;
        this.regularDreamCount = regularDreamCount;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Integer getLucidDreamCount() {
        return lucidDreamCount;
    }

    public void setLucidDreamCount(Integer lucidDreamCount) {
        this.lucidDreamCount = lucidDreamCount;
    }

    public Integer getRegularDreamCount() {
        return regularDreamCount;
    }

    public void setRegularDreamCount(Integer regularDreamCount) {
        this.regularDreamCount = regularDreamCount;
    }
}
