package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParam1(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age = {}", username, age);

        response.getWriter().write("o k ~");

    }

    @ResponseBody //return 값을 문자열 그대로 응답 메시지로 보낼 수 있음 !! ✨
    @RequestMapping("/request-param-v2")
    public String requestParamV2(
            @RequestParam("username") String memberName,
            @RequestParam("age") int memberAge) {
        log.info("memberName = {}, memberAge = {}", memberName, memberAge);
        return "🎃  o k ";
    }


    @ResponseBody //return 값을 문자열 그대로 응답 메시지로 보낼 수 있음 !! ✨
    @RequestMapping("/request-param-v3")
    public String requestParamV3(
            @RequestParam String username,
            @RequestParam int age) { //요청 파라미터 변수명이랑 동일하면 요로코롬 간단해짐 (여기까지 작성하는게 더 직관적)
        log.info("username = {}, age = {}", username, age);
        return "🎃 o k 🎃";
    }

    @ResponseBody //return 값을 문자열 그대로 응답 메시지로 보낼 수 있음 !! ✨
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age) { //단순 타입이면 @RequestParam 도 생략 가능, 대신 요청 파람 변수명 동일
        log.info("username = {}, age = {}", username, age);
        return "o k 😎";
    }

    @ResponseBody //return 값을 문자열 그대로 응답 메시지로 보낼 수 있음 !! ✨
    @RequestMapping("/request-param-required")
    public String requestParamRequired (
            @RequestParam(required = true) String username,
            @RequestParam(required = false) Integer age) {
        log.info("username = {}, age = {}", username, age);
        return "o k 😎😎";
    }

    @ResponseBody //return 값을 문자열 그대로 응답 메시지로 보낼 수 있음 !! ✨
    @RequestMapping("/request-param-default")
    public String requestParamDefault (
            @RequestParam(required = true, defaultValue = "guest") String username,
            @RequestParam(required = false, defaultValue = "-1") int age) {
        log.info("username = {}, age = {}", username, age);
        return "o k 😎😎";
    }

    /**
     * @RequestParam Map, MultiValueMap
     * Map(key=value)
     * MultiValueMap(key=[value1, value2, ...]) ex) (key=userIds, value=[id1, id2])
     */
    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"),
                paramMap.get("age"));
        return "ok";
    }


    /**
    @ResponseBody
    @RequestMapping("model-attribute-v1")
    public String modelAttributeV1(@RequestParam String username, @RequestParam int age) {
        HelloData helloData = new HelloData();
        helloData.setUsername(username);
        helloData.setAge(age);

        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}", helloData); //username, age 자동으로 예쁘게 찍어줌
        return "o  k  😣";
    }
     */


    //@ModelAttribute 의 힘.. : HelloData 객체가 생성되고 요청파라미터 값도 모두 들어감 😮
    @ResponseBody
    @RequestMapping("model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData) {
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}", helloData); //username, age 자동으로 예쁘게 찍어줌
        return "o  k  😣";
    }
    @ResponseBody
    @RequestMapping("model-attribute-v2")
    public String modelAttributeV2(HelloData helloData) { //@ModelAttribute 생략 가능 but, @RequestParam도 생략 가능하니 혼란 발생 가능성 있음
        log.info("username={}, age={}", helloData.getUsername(), helloData.getAge());
        log.info("helloData = {}", helloData); //username, age 자동으로 예쁘게 찍어줌
        return "o  k  😣";
    }

}
