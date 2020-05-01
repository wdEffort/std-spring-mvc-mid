package me.henu.stdspringmvcmid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/api/events")
public class EventApi {

    /**
     * REST API의 경우 응답 본문에 에러 정보를 담아주고, 상태 코드를 설정하려면 ResponseEntity를 주로 사용함.
     * - REST API에서는 메시지 본문에 왜 에러가 발생했는지 에러 정보를 전달해줘야 하기 떄문
     *
     * @param exception
     * @param model
     * @return
     */
    @ExceptionHandler
    public ResponseEntity eventErrorHandler(RuntimeException exception, Model model) {
        return ResponseEntity.badRequest().body("Can't create event as ...");
    }

    /**
     * 데이터를 응답 본문 메시지로 보내기
     *
     * @param event
     * @return
     * @ResponseBody를 사용하지 않고, 메소드 리턴 타입으로 ResponseEntity<T>를 사용.
     * - 응답 본문에 전달할 메시지 타입을 제네릭 타입으로 설정함.
     * - 좀 더 세밀한 응답 처리를 해야하는 경우 사용하면 됨.
     */
    @PostMapping
    public ResponseEntity<Event> createEvent(@RequestBody @Valid Event event, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().build();
        }

        return new ResponseEntity<Event>(event, HttpStatus.CREATED);
    }

}
