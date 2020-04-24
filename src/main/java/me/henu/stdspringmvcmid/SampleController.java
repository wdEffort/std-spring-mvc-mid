package me.henu.stdspringmvcmid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    /**
     * 특정한 요청 매개변수(Parameter) Key를 가지고 있는 요청 처리
     *
     * @return
     */
    @GetMapping(
            value = "/hello",
            params = "name"
    )
    @ResponseBody
    public String hello() {
        return "hello";
    }

}
