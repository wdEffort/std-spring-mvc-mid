package me.henu.stdspringmvcmid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class FileController {

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

}
