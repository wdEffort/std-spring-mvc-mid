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
     * URI 패턴의 일부를 값으로 처리하는 방법 테스트
     *
     * @throws Exception
     * @PathVariable URI Path를 값으로 받아서 처리
     * @MatrixVariable URI 패턴에서 Key/Value 쌍의 데이터를 받아서 처리
     */
    @Test
    public void helloTest() throws Exception {
        this.mockMvc.perform(get("/events/1;name=spring"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

}