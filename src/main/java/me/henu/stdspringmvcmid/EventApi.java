package me.henu.stdspringmvcmid;

import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/events")
public class EventApi {

    /**
     * @ResponseBody와 비슷하지만, 요청 헤더 정보까지 가져오기 위해서는 HttpEntity<T>를 사용해야 함.
     * - HttpMessageConverter를 이용하여 요청 본문 데이터를 객체로 변환할 수 있음.
     * - 요청 본문 데이터를 변환한 객체의 타입을 제네릭 타입으로 설정.
     *
     * @param request
     * @return
     */
    @PostMapping
    public Event createEvent(HttpEntity<Event> request) {
        // DB가 있다면 이벤트를 저장할 수 있음.

        // 요청 헤더 정보에 접근해서 Content-Type 추출.
        MediaType contentType = request.getHeaders().getContentType();
        System.out.println(contentType);

        return request.getBody(); // 제네릭 타입에 해당하는 본문을 반환함.
    }


}
