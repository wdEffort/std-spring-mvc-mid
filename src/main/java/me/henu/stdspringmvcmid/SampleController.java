package me.henu.stdspringmvcmid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.MatrixVariable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Optional;

@Controller
public class SampleController {

    /**
     * URI 패턴의 일부를 핸들러 메소드 아규먼트로 받는 방법
     * Java 8 이후부터 제공하는 Optional을 사용하여 부가적인 처리를 할 수 있음
     *
     * @return
     * @PathVariable URI Path를 값으로 받아올 수 있음(매핑하는 값의 이름을 지정할 수 있음, 값이 반드시 있어야 함, 타입 변환과 Optional을 지원함)
     * @MatrixVariable URI 패턴에서 Key/Value 쌍의 데이터를 값으로 받아올 수 있음(특징은 @PathVariable과 동일하나, 어노테이션을 사용하려면 설정 파일을 이용하여 활성화 시켜줘야 함)
     * @ResponseBody 리턴 값을 HttpMessageConverter를 사용해서 JSON으로 변환하여 응답 본문에 전달하게 됨
     */
    @GetMapping("/events/{id}")
    @ResponseBody
    public Event getEvent(@PathVariable("id") Optional<Integer> idValue, @MatrixVariable String name) {
        Event event = new Event();

        if (!idValue.isEmpty()) {
            event.setId(idValue.get());
        }

        if (name != null) {
            event.setName(name);
        }

        return event;
    }

}
