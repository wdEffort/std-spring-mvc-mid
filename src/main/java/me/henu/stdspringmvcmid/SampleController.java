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
     * 단순 타입 데이터를 복합 타입 객체로 받기
     *
     * @param event
     * @return
     * @ModelAttribute 여러 곳(URI Path, 요청 매개변수, 세션 등)에 있는 단순 타입 데이터를 복합 타입 객체로 받아오거나, 해당 객체를 새로 만들 때 사용
     * [특징]
     * 1. 어노테이션을 생략하여 사용할 수 있음
     */
    @PostMapping("/events")
    @ResponseBody
    public Event createEvent(@ModelAttribute Event event) {
        return event;
    }

}
