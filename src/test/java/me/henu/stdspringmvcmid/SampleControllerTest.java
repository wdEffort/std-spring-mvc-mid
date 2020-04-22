package me.henu.stdspringmvcmid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
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
     * Content-Type 매핑 테스트
     *
     * @throws Exception
     */
    @Test
    public void helloTest() throws Exception {
        // GET 요청을 할 때 Content-Type, Accept Header를 지정해 줌
        this.mockMvc.perform(get("/hello")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .accept(MediaType.TEXT_PLAIN))
                .andDo(print())
                .andExpect(status().isOk());
    }

}