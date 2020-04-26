package me.henu.stdspringmvcmid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

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
     * 바인딩 이후 검증 작업 처리하기(Validation)
     * JSR-303이 지원하는 @Valid 또는 @Validated(그룹 Validation 지원) 어노테이션 이용
     *
     * @param event
     * @param bindingResult
     * @return
     * @Valid 검증 작업을 처리하고, 에러가 발생한다면 BindingResult 타입의 변수에 담아줌
     */
    @PostMapping("/events")
    @ResponseBody
    public Event createEvent(@Valid @ModelAttribute Event event, BindingResult bindingResult) {
        // BindingResult 타입의 변수에 에러가 있으면 if문 동작
        if (bindingResult.hasErrors()) {
            System.out.println("Binding Error catched!");

            // 객체의 정보 출력
            bindingResult.getAllErrors().forEach(c -> {
                System.out.println(c);
            });
        }

        return event;
    }

}
