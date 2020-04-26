package me.henu.stdspringmvcmid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import java.util.ArrayList;
import java.util.List;

@Controller
/**
 * @SessionAttributes 어노테이션을 사용하면
 * 설정한 이름(여기서는 "event")에 해당하는 Model 객체를 자동으로 Session에 넣어줌.
 * 여러 화면(또는 요청)에서 사용해야 하는 객체를 공유할 때 사용.
 *
 * [특징]
 * 1. @ModelAttribute 어노테이션을 사용하여 세션에 있는 데이터를 바인딩 시킬 수 있음
 * 2. SessionStatus 객체를 이용하여 세션 처리를 완료시킬 수 있음(ex. Form 데이터 처리가 끝나고 세션을 비울 때)
 * - 보통 @SessionAttributes와 함께 쓰임
 */
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
        model.addAttribute("event", new Event()); // @SessionAttributes에서 설정한 이름과 동일한 Model 객체이므로 Session에 저장됨.
        return "events/form-name";
    }

    /**
     * 이벤트 이름을 처리
     * 데이터를 @ModelAttribute 어노테이션을 사용하여 복합 객체 타입으로 바인딩 할 때
     * Session에 담긴 해당 객체의 정보가 변경된 다음에 바인딩이 됨.
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
     * @return
     */
    @PostMapping("/events/form/limit")
    public String eventFormLimitSubmit(
            @Validated @ModelAttribute Event event,
            BindingResult bindingResult,
            SessionStatus sessionStatus) {
        if (bindingResult.hasErrors()) {
            return "events/form-limit";
        }

        sessionStatus.setComplete(); // @SessionAttributes로 지정한 객체를 Session에서 지움

        return "redirect:/events/list";
    }

    @GetMapping("/events/list")
    public String getEvents(Model model) {
        // DB를 사용하고 있지 않기 때문에 덤프 Event 객체 생성
        Event event = new Event();
        event.setId(1);
        event.setName("Spring");
        event.setLimit(50);

        List<Event> events = new ArrayList<>();
        events.add(event);

        model.addAttribute("eventList", events);

        return "events/list";
    }

}
