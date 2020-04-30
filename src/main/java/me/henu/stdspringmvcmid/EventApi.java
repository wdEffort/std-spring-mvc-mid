package me.henu.stdspringmvcmid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/events")
public class EventApi {

    /**
     * 데이터를 응답 본문 메시지로 보내기
     * @ResponseBody를 사용하지 않고, 메소드 리턴 타입으로 ResponseEntity<T>를 사용.
     * - 응답 본문에 전달할 객체의 타입을 제네릭 타입으로 설정함.
     *
     * @param event
     * @return
     */
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody @Valid Event event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // 에러 코드를 반환할 때 응답 본문을 추가로 설정이 가능함.
            return ResponseEntity.badRequest().build();
        }

        // 상태코드와 함께 응답 본문을 전달
        // return ResponseEntity.ok(event);
        return ResponseEntity.ok().body(event);
    }

}
