package me.henu.stdspringmvcmid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

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
     *
     * @param event
     * @param bindingResult
     * @param sessionStatus
     * @param model
     * @return
     */
    @PostMapping("/events/form/limit")
    public String eventFormLimitSubmit(
            @Validated @ModelAttribute Event event,
            BindingResult bindingResult,
            SessionStatus sessionStatus,
            Model model) {
        if (bindingResult.hasErrors()) {
            return "events/form-limit";
        }

        sessionStatus.setComplete();

        // RedirectAttributes를 사용하지 않는 상황에서
        // 리다이렉트할 때 Model에 들어있는 데이저 중에 Pirimitive type(기본 타입)은 URI Query Parameter로 추가가 됨.
        // 단, Spring Boot에서는 이 기능이 기본적으로 비활성화 되어있음.
        // 활성화 시키기 위해서는 Ignore-default-model-on-redirect 프로퍼티를 사용해서 활성화시킬 수 있다.
        // 리다이렉트가 되는 쪽에서 @PathVariable 또는 @ModelAttribute를 이용하여 URI Query Parameter를 사용할 수 있음.
        model.addAttribute("name", event.getName());
        model.addAttribute("limit", event.getLimit());

        return "redirect:/events/list"; // "redirect:/events/list?name=spring&limit=50" URI를 요청하는 것과 같음.
    }

    @GetMapping("/events/list")
    public String getEvents(Model model, @SessionAttribute("visitTime") LocalDateTime localDateTime) {
        System.out.println(localDateTime);

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
