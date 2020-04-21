package me.henu.stdspringmvcmid;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SampleController {

    /**
     * HTTP 요청 맵핑
     *
     * @return
     * @RequestMapping 어노테이션에 Method(GET, POST, PUT, DELETE, ... )를 지정하지 않으면 모든 요청을 받아 들임
     */
    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }

}
