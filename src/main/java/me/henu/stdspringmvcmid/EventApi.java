package me.henu.stdspringmvcmid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/events")
public class EventApi {


    /**
     * HttpEntity<T>, @RequestBody 모두 @Valid 또는 @Validated를 사용해서 값을 검증 할 수 있음.
     *
     * @param event
     * @return
     */
    @PostMapping
    public Event createEvent(@RequestBody @Valid Event event, BindingResult bindingResult) {
        // DB가 있다면 이벤트를 저장할 수 있음.

        // BindingResult에 에러가 담긴 경우
        // 에러 본문에 원하는 응답을 넣거나, 상태값을 좀 더 구체적으로 바꿔주거나 등
        // 원하는 처리를 할 수 있음.
        if (bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(error -> {
                System.out.println(error);
            });
        }

        return event;
    }


}
