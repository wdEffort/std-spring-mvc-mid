package me.henu.stdspringmvcmid;

import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.File;

@Controller
public class FileController {

    // 파일 리소스를 읽어오기 위한 의존성 주입
    @Autowired
    ResourceLoader resourceLoader;

    /**
     * File 업로드 양식 화면 요청
     *
     * @param model RedirectAttributes를 통해 자동으로 Model에 데이터가 담김.
     * @return
     */
    @GetMapping("/file")
    public String fileUploadForm(Model model) {
        return "file/form-upload";
    }

    /**
     * 파일 업로드 처리를 할 때 사용할 수 있는 MultipartFile 아규먼트
     * <p>
     * [특징]
     * 1.사용하기 위해서는 MultipartResolver Bean이 DispatcherServlet에 설정이 되어 있어야 함.
     * - DispatcherServlet는 초기화 과정 중에 자신이 사용할 여러 Bean들을 ApplicationContext에서 가져와서 설정을 함.
     * - ApplicationContext에 사용할 Bean이 없는 경우에는 아예 사용하지 않거나, 기본 전략(DispatcherServlet.properties)을 사용하도록 설정이 되어 있음.
     * - Spring Boot에서는 MultipartAutoConfiguration을 통해 자동으로 설정되어 있음.
     * - Spring Boot가 아닌 프로젝트에서는 MultipartResolver Bean을 직접 등록해 줘야 함.
     * <p>
     * 2. POST multipart/form-data 요청에 들어 있는 파일을 참조할 수 있음.
     * <p>
     * 3. List<MultipartFile> 아규먼트를 사용해 여러 파일을 참조할 수도 있음.
     *
     * @param multipartFile
     * @return
     */
    @PostMapping("/file")
    public String fileUload(
            @RequestParam("file") MultipartFile multipartFile,
            RedirectAttributes redirectAttributes) {
        // save(실제로는 File을 저장하는 로직이 구현되어야 함.)
        System.out.println("File name : " + multipartFile.getName());
        System.out.println("File original name : " + multipartFile.getOriginalFilename());

        String mesasge = multipartFile.getOriginalFilename() + " is uploaded";

        redirectAttributes.addFlashAttribute("message", mesasge);

        return "redirect:/file";

    }

    /**
     * 파일 다운로드
     * 1. 파일 리소스를 읽어오는 방법
     * - Spring에서 제공하는 ResourceLoader를 주입받아 사용하면 됨.
     * <p>
     * ResponseEntity<T>
     * 1. 응답 상태코드, 응답 헤더, 응답 본문을 설정할 수 있음.
     * - 응답 본문의 타입을 제네릭 타입으로 정의 할 수 있음.
     *
     * @param filename
     * @return
     * @throws Exception
     */
    @GetMapping("/file/{filename}")
    // 리턴 타입이 ResponseEntity인 경우에는 응답 본문을 작성한 것이기 때문에 @ResponseBody를 사용하지 않아도 됨.
    // @ResponseBody
    public ResponseEntity<Resource> downloadFile(@PathVariable String filename) throws Exception {
        Resource resource = resourceLoader.getResource("classpath:" + filename);
        File file = resource.getFile();

        // 미디어 타입을 알아내기 위해 Tika 사용
        Tika tika = new Tika();
        String mediaType = tika.detect(file);

        // 파일 다운로드 응답 헤더(문자열 타입)에 설정할 내용을 지정함.
        return ResponseEntity.ok() // 응답 상태 코드 설정
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"") // 사용자가 해당 파일을 받을 때 사용할 파일 이름 설정
                .header(HttpHeaders.CONTENT_TYPE, mediaType) // 어떤 타입의 파일인지 설정
                .header(HttpHeaders.CONTENT_LENGTH, file.length() + "") // 파일 크기 설정(Header는 모두 문자열 타입이어야 하므로 형변환이 필요함.)
                .body(resource); // 응답 본문에 리소스를 넣어줌으로써 다운로드가 가능해짐
    }
}
