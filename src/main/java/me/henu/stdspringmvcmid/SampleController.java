package me.henu.stdspringmvcmid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

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
     * @param name
     * @param limit
     * @return
     */
    @PostMapping("/events")
    @ResponseBody
    public Event createEvent(
            @RequestParam String name,
            @RequestParam Integer limit) {
        Event event = new Event();
        event.setName(name);
        event.setLimit(limit);

        return event;
    }

}
