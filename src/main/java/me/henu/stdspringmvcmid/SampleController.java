package me.henu.stdspringmvcmid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
     * @Validated 검증 작업을 처리하고, 에러가 발생한다면 BindingResult 타입의 변수에 담아줌
     * [특징]
     * 1. @Valid 어노테이션과 달리 검증 작업을 처리할 때 그룹을 지정할 수 있음
     * - @Validated(Event.ValidateName.class)과 같이 그룹을 지정하면 다른 Validation 설정은 검증 처리를 하지 않음
     * (
     * ex. Event 클래스의 limit 멤버 변수에 @Min이 설정되어 있어도 검증 처리를 하지 않음.
     * => limit 멤버 변수에 값이 잘못 바인딩이 되어도 에러가 BindingResult 타입의 변수에 담기지 않음
     * )
     */
    @PostMapping("/events")
    @ResponseBody
    public Event createEvent(@Validated(Event.ValidateName.class) @ModelAttribute Event event, BindingResult bindingResult) {
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
