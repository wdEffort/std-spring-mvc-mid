package me.henu.stdspringmvcmid;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

// Web과 관련된 설정 파일

@Configuration
public class WebConfig implements WebMvcConfigurer {

    /**
     * MatrixVariable 어노테이션 사용을 활성화 시키기 위한 설정 메소드
     *
     * @param configurer
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false); // URI 패턴에 ';'를 없에지 않도록 설정
        configurer.setUrlPathHelper(urlPathHelper);
    }
}
