package me.henu.stdspringmvcmid;

import javax.validation.constraints.Min;

public class Event {

    private Integer id;

    private String name;

    // JSR-303이 지원하는 Validation 어노테이션 사용
    @Min(0)
    private Integer limit;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
