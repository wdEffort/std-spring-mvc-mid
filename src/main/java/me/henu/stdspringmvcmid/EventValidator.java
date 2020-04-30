package me.henu.stdspringmvcmid;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

// Spring이 제공하는 Validator을 구현하여 커스텀한 Validator를 생성할 수 있음.

// @Component // 또는 Validator를 구현하지 않고 직접 Bean으로 등록(@Component 어노테이션 이용)해서 원하는 시점에 검증처리를 할수도 있음.
// public class EventValidator {
public class EventValidator implements Validator {

    /**
     * 어떤 도메인 클래스에 대한 Validation을 지원하는지 설정.
     *
     * @param clazz
     * @return
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return Event.class.isAssignableFrom(clazz);
    }

    /**
     * 검증 처리
     *
     * @param target 실제로 검증을 해야하는 객체
     * @param errors
     */
    @Override
    public void validate(Object target, Errors errors) {
        Event event = (Event) target;

        // Event 객체의 name 값에 "aaa"가 들어온다면 에러를 발생시킴.
        if (event.getName().equalsIgnoreCase("aaa")) {
            errors.rejectValue("name", "wrongValue", "The value is not allowed.");
        }
    }
}
