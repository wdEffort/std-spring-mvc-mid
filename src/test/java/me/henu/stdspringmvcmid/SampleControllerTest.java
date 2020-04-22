package me.henu.stdspringmvcmid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    /**
     * 한글자 요청 매핑 테스트
     *
     * @throws Exception
     */
    @Test
    public void helloPatternTest1() throws Exception {
        this.mockMvc.perform(get("/hello/1"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * 여러 글자 요청 매핑 테스트
     *
     * @throws Exception
     */
    @Test
    public void helloPatternTest2() throws Exception {
        this.mockMvc.perform(get("/hello/henu"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * 여러 패쓰 요청 매핑 테스트
     *
     * @throws Exception
     */
    @Test
    public void helloPatternTest3() throws Exception {
        this.mockMvc.perform(get("/hello/henu/info"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}