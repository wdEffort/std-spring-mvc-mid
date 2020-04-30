package me.henu.stdspringmvcmid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@SessionAttributes("event")
public class EventController {

    /**
     * @param webDataBinder 데이터 바인딩 또는 검증 설정을 커스터마이징 할 수 있는 클래스
     * @InitBinder 특정 컨트롤러에서 데이터 바인딩 또는 검증 설정을 변경하고 싶을 때 사용.
     * 1. 메소드 리턴 타입은 반드시 void로 설정해야 함.
     * 2. 모든 요청 전에 반드시 호출하게 됨.
     * 3. 특정 Model 객체에만 데이터 바인딩 또는 검증 설정을 적용하고 싶은 경우 이름을 지정해야 함.
     */
    @InitBinder("event") // "event"라는 이름을 가진 Model 객체에만 데이터 바인딩, 검증 적용
    public void initEventBinder(WebDataBinder webDataBinder) {
        // URI Path, URI Query Parameter, HTTP Form Data를 통해
        // 값을 저장시키고 싶지 않은 필드를 걸러냄.
        webDataBinder.setDisallowedFields("id");
        // webDataBinder.setAllowedFields(); // 값을 저장시킬 필드들을 설정.

        // webDataBinder.addCustomFormatter(); // Formatter 설정.

        webDataBinder.addValidators(new EventValidator()); // 커스텀한 Validator 설정.
    }


    /**
     * 이벤트 카테고리 모델 생성
     *
     * @param model
     * @return
     */
    @ModelAttribute("categories")
    public List<String> categories(Model model) {
        return List.of("study", "seminar", "hobby", "social");
    }

    /**
     * 이벤트를 이름을 입력하는 페이지로 이동
     *
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
