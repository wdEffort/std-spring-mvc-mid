package me.henu.stdspringmvcmid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("event")
public class SampleController {

    /**
     * 이벤트를 이름을 입력하는 페이지로 이동
     *
     * @param model
     * @return
     */
    @GetMapping("/events/form/name")
    public String eventsFormName(Model model) {
        model.addAttribute("event", new Event());
        return "events/form-name";
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
     * RedirectAttribute를 사용하여 Spring Boot의 Ignore-default-model-on-redirect 프로퍼티 설정이 기본값(true)이어도
     * 리다이렉트 할 때 URI Query Parameter을 전달할 수 있음.
     * <p>
     * [특징]
     * 1. RedirectAttributes의 addAttribute() 메소드를 이용하여 명시한 것들만 URI Query Parameter로 전달됨.
     * 2. 리다이렉트 되는 쪽에서 @RequestParam 또는 @ModelAttribute를 사용하여 받아서 사용가능함.
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

        // RedirectAttributes에 명시한 것들만 URI Query Parameter로 전달됨.
        redirectAttributes.addAttribute("name", event.getName());
        redirectAttributes.addAttribute("limit", event.getLimit());

        return "redirect:/events/list";
    }

    /**
     * 이벤트 목록 조회
     * RedirectAttributes를 사용하여 URI Query Parameter 데이터를 @ModelAttribute로 복합 객체 타입으로 받을 때
     * @SessionAttributes 어노테이션에 설정한 이름과 동일하면, 간혹 Session에 설정한 이름과 동일한 객체가 없다면 에러가 발생하므로
     * 이름을 다르게 설정하는 것이 좋음.
     *
     * @param event
     * @param model
     * @param localDateTime
     * @return
     */
    @GetMapping("/events/list")
    public String getEvents(
            @ModelAttribute("newEvent") Event event, // @SessionAttributes에 설정한 이름과 다르므로, URI Query Parameter 데이터를 복합 객체 타입으로 받아옴
            Model model,
            @SessionAttribute("visitTime") LocalDateTime localDateTime) {
        System.out.println(localDateTime);

        // DB를 사용하지 않기 때문에 덤프 이벤트 객체 생성
        Event newEvent = new Event();
        newEvent.setName("spring mvc");
        newEvent.setLimit(20);

        List<Event> events = new ArrayList<>();
        events.add(event);
        events.add(newEvent);

        model.addAttribute("eventList", events);

        return "events/list";
    }

}
