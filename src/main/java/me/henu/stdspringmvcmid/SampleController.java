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
     *
     * @param name
     * @param limit
     * @param model
     * @param localDateTime
     * @return
     */
    @GetMapping("/events/list")
    public String getEvents(
            @RequestParam String name,
            @RequestParam Integer limit,
            Model model,
            @SessionAttribute("visitTime") LocalDateTime localDateTime) {
        System.out.println(localDateTime);

        // 리다이렉트로 받아온 URI Query Parameter를 사용하여 이벤트 객체 생성
        Event newEvent = new Event();
        newEvent.setName(name);
        newEvent.setLimit(limit);

        // DB를 사용하지 않기 때문에 덤프 이벤트 객체 생성
        Event event = new Event();
        event.setName("spring mvc");
        event.setLimit(20);

        List<Event> events = new ArrayList<>();
        events.add(newEvent);
        events.add(event);

        model.addAttribute("eventList", events);

        return "events/list";
    }

}
