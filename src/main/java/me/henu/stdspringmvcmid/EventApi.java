package me.henu.stdspringmvcmid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/events")
public class EventApi {


    /**
     * 데이터를 응답 본문 메시지로 보내기
     *
     * @param event
     * @return
     * @ResponseBody 메소드 리턴값을 HttpMessageConverter를 사용하여 HTTP 응답 본문(Body)에 담아줌.
     * 요청 헤더 중 "Accept" 값을 참고하여 적절한 HttpMessageConverter 타입을 선택하여 사용하게 됨.
     * 단, @RestController 어노테이션을 사용하는 Class라면 메소드마다 @ResponseBody를 설정하지 않아도 자동으로 모든 핸들러 메소드에 적용이 됨.
     */
    @PostMapping
    //@ResponseBody
    public Event createEvent(@RequestBody @Valid Event event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error);
            });
        }

        return event;
    }

}
