package me.henu.stdspringmvcmid;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
// 애플리케이션 전반에 걸친 Test를 작성하고자 할 떄 사용하는 어노테이션
@SpringBootTest
// @SpringBootTest 어노테이션은 모든 Bean이 등록이 되지만, MockMvc를 자동으로 만들어 주지 않음.
// 따라서 MockMvc를 사용하기 위해 @AutoConfigureMockMvc 어노테이션을 사용함.
@AutoConfigureMockMvc
public class FileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    /**
     * File 업로드 테스트
     *
     * @throws Exception
     */
    @Test
    public void fileUploadTest() throws Exception {
        // input[type="file"]의 name 속성값, 파일명, Content-Type, 파일에 들어갈 본문
        MockMultipartFile file = new MockMultipartFile("file", "test.txt", "text/plain", "Hello".getBytes());

        // Multipart 요청(POST, multipart/form-data 설정과 동일)을 통해 File 전달
        this.mockMvc.perform(multipart("/file").file(file))
                .andDo(print())
                .andExpect(status().is3xxRedirection());
    }
}