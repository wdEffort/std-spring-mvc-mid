package me.henu.stdspringmvcmid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    /**
     * HTTP HEAD와 OPTION 요청 처리 테스트
     * <p>
     * Spring MVC에서 구현하지 않아도 자동으로 처리하는 HTTP Method들이 있음.
     * - HEAD : GET 요청과 동일하지만 응답 본문(Body)을 받아오지 않고 응답 Header만 받아옴.
     * - OPTIONS : 요청에 사용할 수 있는 HTTP Method 목록을 제공함.
     * 서버 또는 특정 리소스가 제공하는 기능을 확인할 수 있음(=> 서버는 "Allow" 응답 Header에 사용할 수 있는 HTTP Method 목록을 제공해야 함.)
     *
     * @throws Exception
     */
    @Test
    public void helloTest() throws Exception {
        this.mockMvc.perform(options("/hello")) // OPTIONS 요청
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(header().stringValues(
                        HttpHeaders.ALLOW,
                        hasItems(
                                containsString("GET"),
                                containsString("POST"),
                                containsString("HEAD"),
                                containsString("OPTIONS")
                        ))); // Header에 어떤 값이 있는지 확인(여기서는 사용 가능한 HTTP Method 목록을 순서 상관 없이 확인)
    }

}