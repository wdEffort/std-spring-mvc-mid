package me.henu.stdspringmvcmid;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    /**
     * 특정한 Header가 들어 있는 요청 처리
     *
     * @return
     */
    @RequestMapping(
            value = "/header1",
            headers = HttpHeaders.FROM
    )
    @ResponseBody
    public String headerRequest() {
        return "header1";
    }

    /**
     * 설정한 Header가 없는 요청 처리
     *
     * @return
     */
    @RequestMapping(
            value = "/header2",
            headers = "!" + HttpHeaders.FROM
    )
    @ResponseBody
    public String headerNotRequest() {
        return "header2";
    }

    /**
     * 특정한 Header Key&Value가 있는 요청 처리
     *
     * @return
     */
    @RequestMapping(
            value = "/header3",
            headers = HttpHeaders.AUTHORIZATION + "=" + "123"
    )
    @ResponseBody
    public String headerKeyValueRequest() {
        return "header3";
    }

}
