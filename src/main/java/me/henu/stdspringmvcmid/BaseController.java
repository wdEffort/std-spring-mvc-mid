package me.henu.stdspringmvcmid;

import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

/**
 * 전역 컨트롤러(@ControllerAdvice, @RestControllerAdvice)
 * 1. Model 객체, 예외 처리, 데이터 바인딩 및 검증 설정을 모든 컨트롤러에 적용하고 싶은 경우 사용.
 * 2. Spring 4.0부터 적용할 범위를 설정 할 수 있음.(레퍼런스 참고...)
 * - 특정 어노테이션을 가지고 있는 컨트롤러에만 적용
 * - 특정 패키지 이하의 컨트롤러에만 적용
 * - 특정 클래스 타입에만 적용
 */

// EventController.class, EventApi.class에만 적용시키기
@ControllerAdvice(assignableTypes = {EventController.class, EventApi.class})
public class BaseController {

    /**
     * 예외 처리 핸들러
     * 특정 예외 발생시 에러 페이지를 보여주는 핸들러 정의
     *
     * @param exception
     * @param model
     * @return
     */
    @ExceptionHandler({EventException.class, RuntimeException.class})
    public String eventErrorHandler(RuntimeException exception, Model model) {
        model.addAttribute("message", "Runtime error");
        return "error";
    }

    /**
     * 컨트롤러 안에서 데이터 바인딩 또는 검증 설정을 변경
     *
     * @param webDataBinder
     */
    @InitBinder("event")
    public void initEventBinder(WebDataBinder webDataBinder) {
        webDataBinder.setDisallowedFields("id");
        webDataBinder.addValidators(new EventValidator());
    }

    /**
     * 이벤트 카테고리 모델 생성
     *
     * @param model
     * @return
     */
    @ModelAttribute("categories")
    public List<String> categories(Model model) {
        return List.of("study", "seminar", "hobby", "social");
    }

}
