package me.henu.stdspringmvcmid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
public class EventApi {

    /**
     * 요청 본문(Body)에 들어오는 데이터 @RequestBody 어노테이션을 사용하여
     * HandlerAdopter가 메소드 아큐먼트를 Resolving할 때
     * HandlerAdopter에 등록되어 있는 여러 HttpMessageConverter 중에
     * 현재 요청에 들어 있는 본문을 변환 할 수 있는 Converter를 선택을 해서 변환한 객체로 받아올 수 있음.
     * - 가령 요청 본문이 JSON 타입이고, 요청 Header의 Content-Type이 JSON인 경우 JSON을 변환할 수 있는 HttpMessageConverter를 사용하게 됨.
     * <p>
     * [단점]
     * 1. 요청 헤더 정보에는 접근할 수 없고, 오로지 요청 본문 정보에만 접근이 가능함.
     *
     * @param event
     * @return
     */
    @PostMapping
    public Event createEvent(@RequestBody Event event) {
        // DB가 있다면 이벤트를 저장할 수 있음.

        return event;
    }


}
