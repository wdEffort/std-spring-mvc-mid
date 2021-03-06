package me.henu.stdspringmvcmid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.util.Map;

import static org.hamcrest.core.IsNull.notNullValue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringRunner.class)
@WebMvcTest
public class EventControllerTest {

    @Autowired
    MockMvc mockMvc;

    /**
     * 이벤트 페이지 요청 테스트
     *
     * @throws Exception
     */
    @Test
    public void eventFormTest() throws Exception {
        MockHttpServletRequest request = this.mockMvc.perform(get("/events/form"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("events/form")) // View 이름이 "/events/form" 인지 확인 확인
                .andExpect(model().attributeExists("event")) // Model에 "event"라는 이름을 가진 객체가 있는지 확인
                .andExpect(request().sessionAttribute("event", notNullValue()))
                .andReturn().getRequest();// Session에 "event"라는 이름을 가진 객체가 있는지 확인
        Object event = request.getSession().getAttribute("event");
        System.out.println(event);
    }

    /**
     * 새로운 이벤트 생성 테스트
     *
     * @throws Exception
     */
    @Test
    public void createEvent() throws Exception {
        ResultActions resultActions = this.mockMvc.perform(post("/events")
                .param("id", "1")
                .param("name", "spring")
                .param("limit", "-10")) // Validation 처리를 위한 Parameter 전달
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().hasErrors());// Binding 에러 발생시 Model에 에러정보가 담겨있는지 확인

        ModelAndView mav = resultActions.andReturn().getModelAndView();// ModelAndView 객체를 꺼냄
        Map<String, Object> model = mav.getModel(); // ModelAndView 객체에서 Model 객체를 꺼냄

        System.out.println(model.size());
    }

    /**
     * Flash Attributes 사용, @ModelAttribute 테스트
     *
     * @throws Exception
     */
    @Test
    public void getEvents() throws Exception {
        Event newEvent = new Event();
        newEvent.setId(1);
        newEvent.setName("spring jpa");
        newEvent.setLimit(10);

        this.mockMvc.perform(get("/events/list")
                .sessionAttr("visitTime", LocalDateTime.now()) // HTTP Session에 "visitTime" 저장
                .flashAttr("newEvent", newEvent)) // Flash Attributes에 "newEvent" 저장
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("categories")) // Model에 "categories"라는 이름의 객체가 있는지 확인
                .andExpect(xpath("//li").nodeCount(2)); // <li> Element가 2개 있는지 확인
    }

}