package me.henu.stdspringmvcmid;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

import java.util.List;

// Web과 관련된 설정 파일

@Configuration
// @EnableWebMvc 어노테이션을 사용하면 DelegatingWebMvcConfiguration이 Import 되면서
// WebMvcConfigurationSupport가 Bean으로 등록되기 때문에
// 스프링 부트가 지원하는 WebMvcAutoConfiguration의 조건에 위배 되므로 자동 설정을 사용하지 않게 됨
@EnableWebMvc
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

    /**
     * HandlerInterceptor 추가
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new VisitTimeInterceptor()); // 모든 요청 처리전 해당 인터셉터가 호출됨.
    }

    /**
     * MessageConverter 등록
     * MessageConverter를 추가하여 사용하게 됨.
     *
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

    }

    /**
     * MessageConverter 등록
     * 단, 기본 MessageConverter는 상요하지 않게 됨.
     *
     * @param converters
     */
    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

    }
}
