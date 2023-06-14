package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j
@RestController
public class LogTestController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

//        log.trace("trace log="+ name); 문자열의 연산이 있으면서 메모리 낭비가 발생됨, 이렇게 사용하지 말 것

        log.trace("trace log={}", name);
        log.debug("debug log={}", name);
        log.info(" info log = {}", name);
        log.warn(" warn log = {}", name);
        log.error(" error log = {}", name);

        return "ok";
    }
}
