package me.henu.stdspringmvcmid;

import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Class 레벨에 @RequestMapping을 지정하면 공통적인 요청 처리를 담당하게 됨
 * - 해당 컨트롤러는 특정 URL로 시작되는 요청을 처리하거나,
 * - 특정 HTTP Method 요청을 처리함
 */

@Controller
@RequestMapping(method = RequestMethod.GET) // 해당 컨트롤러는 GET 요청만 받게 됨
public class SampleController {

    /**
     * HTTP 요청 맵핑
     * [GET]
     * 클라이언트가 서브의 리소스를 요청할 때 사용
     * 캐싱 가능
     * 민감한 데이터를 보낼 때 사용하지 않아야 함
     * 동일한 GET 요청은 항상 동일한 응답을 반환해야함(Idemponent)
     * <p>
     * [POST]
     * 클라이언트가 서버의 리소스를 수정하거나, 새로 만들 때 사용
     * 서버에 보내는 데이터를 POST 요청 본문에 담으며, 데이터 길이 제한이 없음
     * <p>
     * [PUT]
     * URI에 해당하는 데이터를 새로 만들거나, 수정할 때 사용
     * POST의 URI는 보내는 데이터를 처리할 리소스를 지칭하며,
     * PUT의 URI는 보내는 데이터에 해당하는 리소스를 지칭함
     * Idemponent
     * <p>
     * [DELETE]
     * URI에 해당하는 리소스를 삭제할 때 사용
     * Idemponent
     * <p>
     * [PATCH]
     * PUT과 비슷하지만, 기존 엔티티와 새 데이터의 차이점만 보냄
     * (리소스가 가지고 있는 일부의 데이터만 수정하고 싶은 경우 사용)
     * Idemponent
     *
     * @return
     * @GetMapping GET 요청 처리를 하는 어노테이션
     */
    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "hello";
    }

}
