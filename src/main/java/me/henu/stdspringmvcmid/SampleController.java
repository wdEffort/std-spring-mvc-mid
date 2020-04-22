package me.henu.stdspringmvcmid;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
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

    /**
     * [URI 확장자 매핑 지원]
     * Spring MVC는 아래와 같이 "/henu"로 매핑을 하면, 암묵적으로 "/henu.*"과 같은 매핑을 해줌
     * (henu.json, henu.html, henu.xml, ... )
     * 하지만 Spring Boot와 최신 버전의 Spring에서는 이 기능을 기본적으로 사용하지 않도록 설정이 되어있음(=> RFD Attack 보안 이슈 관련)
     * <p>
     * [URI 확장자 매핑을 사용할 때 단점]
     * URI 변수, Path 매개변수, URI 인코딩을 사용할 때 불명확 함.
     * 예전에는 특정한 응답 타입을 지정하기 위해 "/henu.json", "/henu.xml"과 같이 사용했다면
     * 최근에는 요청 헤더(Accept Header)에 응답 타입을 설정하는 방식으로 사용을 함.(Header에 들어 있는 내용을 가지고 판단)
     *
     * @return
     */
    @RequestMapping("/henu")
    // @RequestMapping({"/henu", "/henu.*"})
    @ResponseBody
    public String helloHenu() {
        return "hello henu";
    }

}
