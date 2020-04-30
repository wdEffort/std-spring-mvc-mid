package me.henu.stdspringmvcmid;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class EventApiTest {

    // Spring Boot가 자동으로 제공하는 Jakson 라이브러리에서 등록되어 있는
    // ObjectMapper Bean 사용.(객체를 JSON 문자열로, JSON 문자열을 객체로 변환이 가능.)
    @Autowired
    ObjectMapper objectMapper;

    @Autowired
    MockMvc mockMvc;

    /**
     * 요청 본문 데이터를 객체로 변환할 때 검증처리 테스트
     *
     * @throws Exception
     */
    @Test
    public void createEvent() throws Exception {
        Event event = new Event();
        event.setId(1);
        event.setName("spring");
        event.setLimit(-50);

        // 객체를 JSON 문자열로 변환
        String json = objectMapper.writeValueAsString(event);

        this.mockMvc.perform(post("/api/events")
                .contentType(MediaType.APPLICATION_JSON_UTF8) // 요청 Header Content-Type 설정(사용할 HttpMesasgeConverter가 결정되는 요인임.)
                .content(json) // 요청 본문 설정.
                .accept(MediaType.APPLICATION_JSON)) // 요청이 어떠한 타입의 응답을 원하는지 설정
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("name").value("spring"))
                .andExpect(jsonPath("limit").value(-50));
    }

}