package me.henu.stdspringmvcmid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@Controller
public class SampleController {

    /**
     * 요청 매개변수(URL Query Parameter, HTTP Form 데이터)를 @RequestParam 어노테이션을 사용하여 처리하기
     *
     * @param params
     * @return
     * @RequestParam 요청 매개변수에 있는 단순 타입 데이터를 메소드 아규먼트로 받아올 수 있음
     * [특징]
     * 1. 이 어노테이션을 생략해서 사용할 수 있음
     * 2. 요청 매개변수 이름을 value 속성을 이용해서 지정할 수 있음
     * 3. 값이 반드시 있어야 하며, required 속성 또는 Java 8 이후 사용 가능한 Optional을 사용해서 부가적인 값으로 설정할 수도 있음
     * 4. defaultValue 속성을 이용해서 기본값을 지정할 수 있음
     * 5. String 타입이 아닌 값들은 타입 변환을 지원함
     * 6. Map<String, String>, MultiValueMap<String, String>을 사용해서 모든 요청 매개변수를 한번에 받아올 수도 있음
     */
    @PostMapping("/events")
    @ResponseBody
    public Event getEvent(@RequestParam Map<String, String> params) {
        Event event = new Event();
        event.setId(Integer.parseInt(params.get("id")));
        event.setName(params.get("name"));

        return event;
    }

}
