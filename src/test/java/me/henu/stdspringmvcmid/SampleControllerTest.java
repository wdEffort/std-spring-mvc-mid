package me.henu.stdspringmvcmid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    /**
     * 정규 표현식을 이용한 요청 매핑 테스트
     *
     * @throws Exception
     */
    @Test
    public void regExpTest() throws Exception {
        this.mockMvc.perform(get("/hello/henu/info"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("henu info"))
                .andExpect(handler().handlerType(SampleController.class)) // 핸들러 타입이 SampleController인지 확인
                .andExpect(handler().methodName("regExp")); // 핸들러 메소드 이름이 regExp인지 확인
    }

}