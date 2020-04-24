package me.henu.stdspringmvcmid;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.lang.annotation.*;

/**
 * 커스텀 어노테이션 사용
 * [어노테이션 종류]
 * 1. 메타 어노테이션
 * - 어노테이션에 사용할 수 있는 어노테이션
 * 1) @Retention - 해당 어노테이션을 언제까지 유지할 것인가를 설정
 * (Class[기본]: 컴파일 한 .class 파일에도 유지, 클래스 로딩시(메모리로 읽어오면) 사라짐 / Source : 소스 코드 까지만 유지 / Runtime : 클래스 로딩 후에도 유지)
 * 2) @Target - 해당 어노테이션을 어디에 사용할 수 있는지 설정
 * 3) @Documented - 해당 어노테이션을 사용한 코드의 JAVA DOC에 어노테이션에 대한 정보를 표기할지 설정
 * - Spring이 제공하는 대부분의 어노테이션은 메타 어노테이션으로 사용할 수 있음
 * <p>
 * 2. 조합(Composed) 어노테이션
 * - 한개 혹은 여러 메타 어노테이션을 조합해서 만든 어노테이션
 * - 코드를 간결하게 줄일 수 있음
 * - 보다 구체적인 의미를 부여할 수 있음
 */

@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@RequestMapping(value = "/hello", method = RequestMethod.GET)
public @interface GetHelloMapping {
}
