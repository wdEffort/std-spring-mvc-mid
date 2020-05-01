package me.henu.stdspringmvcmid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("event")
public class EventController {

    /**
     * 예외 처리 핸들러
     * 특정 예외 발생시 에러 페이지를 보여주는 핸들러 정의
     *
     * @param exception
     * @param model
     * @return
     * @ExceptionHandler 어노테이션 사용
     * 1. 특정 예외가 발생한 요청을 직접 처리하는 핸들러를 정의할 수 있음.
     * - 요청을 처리하는 핸들러 메소드와 비슷하지만 추가적으로 사용할 수 있는 메소드 아규먼트가 조금 다름.
     * - 지원하는 메소드 아규먼트 : 처리하고 싶은 예외 객체, 핸들러 객체(=> Spring.io의 Spring Web MVC 레퍼런스 참고...)
     * 2. REST API의 경우 응답 본문에 에러 정보를 담아주고, 상태 코드를 설정하려면 ResponseEntity를 주로 사용함.
     * 3. 가장 구체적인 에러가 매핑이 됨.
     * - 가령 EventException, RuntimeException 두 예외 핸들러가 있다고 하더라도 구체적인 에러 핸들러에 매핑이 됨.
     * 4. 여러 에러를 같이 처리하고 싶은 경우 @ExceptionHandler에 배열로 설정할 수 있음.
     * - 여러 에러를 같이 처리할 경우 두 에러 타입의 "상위 타입"으로 예외 아규먼트를 설정해야 함.
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

    /**
     * 이벤트를 이름을 입력하는 페이지로 이동
     *
     * @return
     */
    @GetMapping("/events/form/name")
    public String eventsFormName(Model model) {
        throw new EventException(); // 강제로 EventException 발생
        //model.addAttribute("event", new Event());
        //return "events/form-name";
    }

    /**
     * 이벤트 이름을 처리
     *
     * @param event
     * @param bindingResult
     * @param sessionStatus
     * @return
     */
    @PostMapping("/events/form/name")
    public String eventFormNameSubmit(
            @Validated @ModelAttribute Event event,
            BindingResult bindingResult,
            SessionStatus sessionStatus) {
        if (bindingResult.hasErrors()) {
            return "events/form-name";
        }

        return "redirect:/events/form/limit";
    }

    /**
     * 이벤트 참여자수를 입력하는 페이지로 이동
     *
     * @param event
     * @param model
     * @return
     */
    @GetMapping("/events/form/limit")
    public String eventsFormLimit(@ModelAttribute Event event, Model model) {
        model.addAttribute("event", event);
        return "events/form-limit";
    }

    /**
     * 이벤트 참여자수를 처리
     *
     * @param event
     * @param bindingResult
     * @param sessionStatus
     * @param redirectAttributes
     * @return
     */
    @PostMapping("/events/form/limit")
    public String eventFormLimitSubmit(
            @Validated @ModelAttribute Event event,
            BindingResult bindingResult,
            SessionStatus sessionStatus,
            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "events/form-limit";
        }

        sessionStatus.setComplete();

        // 리다이렉트 할 때 HTTP Session에 "newEvent"라는 이름으로 객체를 담아서 보냄.
        redirectAttributes.addFlashAttribute("newEvent", event);

        return "redirect:/events/list";
    }

    /**
     * 이벤트 목록 조회
     *
     * @param model
     * @param localDateTime
     * @return
     */
    @GetMapping("/events/list")
    public String getEvents(
            Model model,
            @SessionAttribute("visitTime") LocalDateTime localDateTime) {
        System.out.println(localDateTime);

        // DB를 사용하지 않기 때문에 덤프 이벤트 객체 생성
        Event event = new Event();
        event.setName("spring mvc");
        event.setLimit(20);

        // HTTP Session에서 "newEvent"라는 이름을 가진 객체를 Model을 이용하여 꺼냄
        Event newEvent = (Event) model.asMap().get("newEvent");

        List<Event> events = new ArrayList<>();
        events.add(event);
        events.add(newEvent);

        model.addAttribute("eventList", events);

        return "events/list";
    }

}
