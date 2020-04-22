package me.henu.stdspringmvcmid;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Class 레벨에 @RequestMapping을 지정하면 공통적인 요청 처리를 담당하게 됨
 * - 해당 컨트롤러는 특정 URL로 시작되는 요청을 처리하거나,
 * - 특정 HTTP Method 요청을 처리함
 */

@Controller
@RequestMapping("/hello") // 공통 적으로 처리할 URL 매핑
public class SampleController {

    @RequestMapping("/**")
    @ResponseBody
    public String hello() {
        return "hello";
    }

}
