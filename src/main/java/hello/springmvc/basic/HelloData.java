package hello.springmvc.basic;

import hello.springmvc.SpringmvcApplication;
import lombok.Data;

@Data //롬복 라이브러리 : @Getter, Setter, ToString ,,,자동 제공
public class HelloData {
    private String username;
    private int age;

}
