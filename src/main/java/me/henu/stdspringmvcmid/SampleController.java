package me.henu.stdspringmvcmid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
     * 단순 타입 데이터를 복합 타입 객체로 바인딩 시킬때 예외 상황 처리하기
     * ModelAttribute 어노테이션을 사용한 메소드 아규먼트 바로 오른쪽에 BindingResult 타입의 변수를 선언하면
     * Binding과 관련된 에러(BindingException)가 바로 던져지는게 아니라 담기게 되며 요청은 정상적으로 처리됨.
     * BindingResult 타입의 변수를 사용해서 부가적인 처리가 가능함.(ex. 바인딩이 잘못 되었을 때 다시 View를 보여주며 사용자한테 메시지를 띄워주는 처리)
     *
     * @param event
     * @return
     */
    @PostMapping("/events")
    @ResponseBody
    public Event createEvent(@ModelAttribute Event event, BindingResult bindingResult) {
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
