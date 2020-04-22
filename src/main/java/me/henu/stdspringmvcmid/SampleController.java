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

    /**
     * 여러 요청 매핑
     * 배열 형태로 URL을 지정해주면 됨
     *
     * @return
     */
    @GetMapping({"/hello", "/hi"})
    @ResponseBody
    public String hello() {
        return "hello";
    }


}
