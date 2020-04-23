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
            value = "/param1",
            params = "name"
    )
    @ResponseBody
    public String paramKeyRequest() {
        return "hello";
    }

    /**
     * 특정한 요청 매개변수(Parameter) Key를 가지고 있는 않는 요청 처리
     *
     * @return
     */
    @GetMapping(
            value = "/param2",
            params = "!name"
    )
    @ResponseBody
    public String paramNotKeyRequest() {
        return "hello";
    }

    /**
     * 특정한 요청 매개변수(Parameter) Key&Value 가지고 있는 요청 처리
     *
     * @return
     */
    @GetMapping(
            value = "/param3",
            params = "job=developer"
    )
    @ResponseBody
    public String paramKeyValueRequest() {
        return "hello";
    }

}
