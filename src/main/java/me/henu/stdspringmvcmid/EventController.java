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
public class EventController {

    /**
     * @ModelAttribute의 또다른 사용법
     * 1. @RequestMapping을 사용한 핸들러 메소드의 아규먼트에 사용.
     * 2. @Controller 또는 @ControllerAdvice 어노테이션을 사용한 Class에서 모델 정보를 초기화 할 때 사용.
     * - Controller 안에서 모두 공통적으로 참고해야하는 모델 정보가 있어야 하는 경우 사용.
     * 3. @RequestMapping 어노테이선과 함께 사용하면 해당 메소드에서 리턴하는 객체를 모델에 넣어 줌.(잘 사용하지 않음...)
     * - View 이름은 RequestToViewNameTranslator 인터페이스가 요청 URI과 정확히 일치하는 View 이름으로 리턴해 줌으로써 설정됨.
     */

    /**
     * 이벤트 카테고리 모델 생성
     * Contorller 안에서 모두 공통적으로 참고 할 수 있음.
     * 위 메소드와 달리 메소드 리턴 타입을 지정한 경우 @ModelAttribute에 모델 이름을 지정하여 사용이 가능함.
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
     * 해당 메소드와 같이 @ReuqestMapping + @ModelAttribute 조합인 경우
     * 리턴하는 객체를 자동으로 Model에 담아주게 됨.(이러한 경우 @ModelAttribute는 생략 가능.)
     * View 이름 처리는 RequestToViewNameTranslator 인터페이스를 이용하여 처리됨.
     *
     * @return
     */
    @GetMapping("/events/form-name")
    @ModelAttribute
    public Event eventsFormName() {
        return new Event();
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
