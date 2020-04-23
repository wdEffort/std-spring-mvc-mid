package me.henu.stdspringmvcmid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpHeaders;
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
     * 특정한 Header가 들어 있는 요청 처리 테스트
     *
     * @throws Exception
     */
    @Test
    public void headerRequestTest() throws Exception {
        this.mockMvc.perform(get("/header1")
                .header(HttpHeaders.FROM, "localhost"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * 설정한 Header가 없는 요청 처리 테스트
     *
     * @throws Exception
     */
    @Test
    public void headerNotRequestTest() throws Exception {
        this.mockMvc.perform(get("/header2")
                .header(HttpHeaders.ACCEPT_LANGUAGE, "en"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * 특정한 Header Key&Value가 있는 요청 처리 테스트
     *
     * @throws Exception
     */
    @Test
    public void headerKeyValueRequestTest() throws Exception {
        this.mockMvc.perform(get("/header3")
                .header(HttpHeaders.AUTHORIZATION, "123"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}