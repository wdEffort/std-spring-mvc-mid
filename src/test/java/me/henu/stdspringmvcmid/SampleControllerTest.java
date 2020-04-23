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
     * 특정한 요청 매개변수(Parameter) Key를 가지고 있는 요청 처리 테스트
     *
     * @throws Exception
     */
    @Test
    public void paramKeyRequestTest() throws Exception {
        this.mockMvc.perform(get("/param1")
                .param("name", "henu"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * 특정한 요청 매개변수(Parameter) Key를 가지고 있는 않는 요청 처리 테스트
     *
     * @throws Exception
     */
    @Test
    public void paramNotKeyRequestTest() throws Exception {
        this.mockMvc.perform(get("/param2")
                .param("age", "29"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    /**
     * 특정한 요청 매개변수(Parameter) Key&Value 가지고 있는 요청 처리 테스트
     *
     * @throws Exception
     */
    @Test
    public void paramKeyValueRequestTest() throws Exception {
        this.mockMvc.perform(get("/param3")
                .param("job", "developer"))
                .andDo(print())
                .andExpect(status().isOk());
    }

}