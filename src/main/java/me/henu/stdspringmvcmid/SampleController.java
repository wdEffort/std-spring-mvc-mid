package me.henu.stdspringmvcmid;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    /**
     * Content-Type 매핑(=> Content-Type Header로 필터링을 진행함.)
     *
     * @return
     * @RequestMapping "consumes" 속성을 이용하면 특정 타입의 데이터를 담고 있는 요청만 처리하게 됨(요청과 매치되지 않는 경우 415).
     * "produces" 속성을 이용하면 특정 타입의 응답을 만들게 됨(요청과 매치되지 않는 경우 406).
     * 단, "produces"는 Accept Header가 없는 경우에도 매핑이 됨.
     * <p>
     * 각 속성값을 지정할 때 "application/json"과 같은 문자열을 입력해도 되지만,
     * org.springframework.http가 제공하는 MediaType을 사용하면 상수로 표기되어 있기 때문에 편리함.
     */
    @RequestMapping(
            value = "/hello",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, // JSON 데이터를 담고 있는 요청만 처리
            produces = MediaType.TEXT_PLAIN_VALUE //
    )
    @ResponseBody
    public String hello() {
        return "hello";
    }

}
