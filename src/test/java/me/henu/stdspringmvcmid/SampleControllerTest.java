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
     * 이벤트 페이지 요청 테스트
     *
     * @throws Exception
     */
    @Test
    public void eventFormTest() throws Exception {
        this.mockMvc.perform(get("/events/form"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("events/form")) // View 이름이 "/events/form" 인지 확인 확인
                .andExpect(model().attributeExists("event")); // Model에 "event"라는 이름을 가진 객체가 있는지 확인 ;
    }

    /**
     * 새로운 이벤트 생성 테스트
     *
     * @throws Exception
     */
    @Test
    public void createEvent() throws Exception {
        this.mockMvc.perform(post("/events?name=spring")
                .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

}