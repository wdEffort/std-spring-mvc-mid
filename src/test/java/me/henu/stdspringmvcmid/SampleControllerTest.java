package me.henu.stdspringmvcmid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest
public class SampleControllerTest {

    @Autowired
    MockMvc mockMvc;

    /**
     * GET 요청 테스트
     *
     * @throws Exception
     */
    @Test
    public void helloGetRequestTest() throws Exception {
        this.mockMvc.perform(get("/hello"))
                .andDo(print())
                .andExpect(status().isOk()); // 요청이 잘 처리 되었는지 확인
    }

    /**
     * POST 요청 테스트
     *
     * @throws Exception
     */
    @Test
    public void helloPostRequestTest() throws Exception {
        this.mockMvc.perform(post("/hello"))
                .andDo(print())
                .andExpect(status().isOk()); // 요청이 잘못되었는지 확인
    }

    /**
     * DELETE 요청 테스트
     *
     * @throws Exception
     */
    @Test
    public void helloDeleteRequestTest() throws Exception {
        this.mockMvc.perform(delete("/hello"))
                .andDo(print())
                .andExpect(status().isMethodNotAllowed()); // 요청이 잘못되었는지 확인
    }
}