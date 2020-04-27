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
     * RedirectAttributes는 리다이렉트를 할 때 어떤 데이터를 URI Query Parameter로 전달하는 역할을 하는데
     * 이때 데이터는 URI Query Parameter에 붙을 수 있어야 하기 때문에 전부 "문자열"로 변환이 가능해야 함.
     * (즉, 복합 객체 타입의 경우 기본적으로 사용이 불가능)
     * <p>
     * 리다이렉트를 할 때 복합 객체 타입을 전달하기 위해서는
     * RedirectAttributes의 "Flash Attributes"를 사용해야 함.
     * <p>
     * [특징]
     * 1. 데이터가 URI에 노출되지 않음.
     * 2. 임의의 객체를 지정할 수 있음.
     * 3. 보통 HTTP Session을 많이 사용함.(=> 1회성)
     * - 리다이렉트 하기 전에 데이터를 HTTP Session에 저장하고, 리다이렉트 요청을 처리한 후 즉시 제거하는 방법으로 사용.
     * - Model 또는 @ModelAttribute를 사용하여 꺼내 쓸 수 있음.
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

        // 리다이렉트 할 때 HTTP Session에 "newEvent"라는 이름으로 객체를 담아서 보냄.
        redirectAttributes.addFlashAttribute("newEvent", event);

        return "redirect:/events/list";
    }

    /**
     * 이벤트 목록 조회
     * Flash Attributes를 사용하게 되면 HTTP Session에 데이터가 담긴 채로 리다이렉트 되므로
     * 요청 처리시 꺼내서(Model 또는 @ModelAttribute 이용) 사용할 수 있음.
     *
     * @param model
     * @param localDateTime
     * @return
     */
    @GetMapping("/events/list")
    public String getEvents(
            Model model,
            @SessionAttribute("visitTime") LocalDateTime localDateTime) {
        System.out.println(localDateTime);

        // DB를 사용하지 않기 때문에 덤프 이벤트 객체 생성
        Event event = new Event();
        event.setName("spring mvc");
        event.setLimit(20);

        // HTTP Session에서 "newEvent"라는 이름을 가진 객체를 Model을 이용하여 꺼냄
        Event newEvent = (Event) model.asMap().get("newEvent");

        List<Event> events = new ArrayList<>();
        events.add(event);
        events.add(newEvent);

        model.addAttribute("eventList", events);

        return "events/list";
    }

}
