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
     * 정규 표현식을 사용한 요청 매핑
     *
     * @return
     * @PathVariable 어노테이션을 사용하면 URL Path(경로)에 들어있는 값을 꺼내서 쓸 수 있음
     */
    @RequestMapping("/{name:[a-z]+}/{value:[a-z]+}") // 알파벳 a ~ z까지 해당하는 요청을 매핑
    @ResponseBody
    public String regExp(@PathVariable String name, @PathVariable String value) {
        return name + " " + value;
    }

    /**
     * 요청 패턴이 중복되는 경우 구체적으로 매핑되는 핸들러가 선택됨.
     * <p>
     * "/hello/henu/info"로 요청을 보내게 되면 hi() 메소드가 아닌,
     * 좀 더 구체적으로 매핑이 된 regExp() 메소드가 동작하게 된다.
     *
     * @return
     */
    @RequestMapping("/**")
    @ResponseBody
    public String hi() {
        return "hello";
    }

}
