package me.henu.stdspringmvcmid;

import org.springframework.http.HttpStatus;
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
     * - 응답 본문에 전달할 메시지 타입을 제네릭 타입으로 설정함.
     * - 좀 더 세밀한 응답 처리를 해야하는 경우 사용하면 됨.
     *
     * @param event
     * @return
     */
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody @Valid Event event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<Event>(event, HttpStatus.CREATED);
    }

}
