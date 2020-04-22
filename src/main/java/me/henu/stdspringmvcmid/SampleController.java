package me.henu.stdspringmvcmid;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Class 레벨에 @RequestMapping을 지정하면 공통적인 요청 처리를 담당하게 됨
 * - 해당 컨트롤러는 특정 URL로 시작되는 요청을 처리하거나,
 * - 특정 HTTP Method 요청을 처리함
 */

@Controller
public class SampleController {

    /*
     [요청 식별자(패턴)로 매핑하기]
     ? : 한글자("/hello/???" => "/hello/lee")
     * : 여러 글자("/hello/*" => "/hello/henunim")
     ** : 여러 패스 ("/hello/**" => "/hello/henu/info")
     */

    @GetMapping("/hello/?")
    @ResponseBody
    public String helloPattern1() {
        return "hello";
    }

    @GetMapping("/hello/*")
    @ResponseBody
    public String helloPattern2() {
        return "hello";
    }

    @GetMapping("/hello/**")
    @ResponseBody
    public String helloPattern3() {
        return "hello";
    }

}
