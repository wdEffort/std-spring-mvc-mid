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
     * 요청 매개변수 처리 테스트
     *
     * @throws Exception
     * @RequestParam 요청 매개변수 값을 처리할 수 있음
     */
    @Test
    public void helloTest() throws Exception {
        this.mockMvc.perform(post("/events?name=spring")
                .param("id", "1")) // 요청 Parameter 설정
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

}