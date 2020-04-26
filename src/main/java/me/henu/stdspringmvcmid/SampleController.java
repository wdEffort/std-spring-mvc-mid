package me.henu.stdspringmvcmid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class SampleController {

    /**
     * 이벤트를 입력하는 페이지로 이동
     *
     * @param model
     * @return
     */
    @GetMapping("/events/form")
    public String eventsForm(Model model) {
        Event newEvent = new Event();
        newEvent.setLimit(50);

        model.addAttribute("event", newEvent); // Form backing object

        return "events/form";
    }

    /**
     * 새로운 이벤트 생성
     *
     * @param event
     * @param bindingResult
     * @return
     */
    @PostMapping("/events")
    public String createEvent(
            @Validated @ModelAttribute Event event,
            BindingResult bindingResult,
            Model model) {
        // BindingResult 타입의 변수에 에러가 있으면 if문 동작
        if (bindingResult.hasErrors()) {
            return "events/form"; // View에서 바인딩 에러를 보여줄 수 있음(Thymeleaf, JSP 마다 사용법이 있으니 참고...)
        }

        List<Event> events = new ArrayList<>();
        events.add(event);

        model.addAttribute("eventList", events);

        // POST > Redirect > GET 패턴 적용
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
