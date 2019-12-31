package com.security.demospringsecurity.message.request;

public class SearchHomeByNameForm {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SearchHomeByNameForm() {
    }

    public SearchHomeByNameForm(String name) {
        this.name = name;
    }
}
