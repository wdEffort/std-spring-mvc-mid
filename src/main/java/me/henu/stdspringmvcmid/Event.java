package me.henu.stdspringmvcmid;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

public class Event {

    // Validation 그룹 생성
    interface ValidateLimit {
    }

    // Validation 그룹 생성
    interface ValidateName {
    }

    private Integer id;

    // JSR-303이 지원하는 Validation 어노테이션 사용
    // Validation 그룹 지정
    @NotBlank(groups = ValidateName.class)
    private String name;

    // JSR-303이 지원하는 Validation 어노테이션 사용
    // Validation 그룹 지정
    @Min(value = 0, groups = ValidateLimit.class)
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
