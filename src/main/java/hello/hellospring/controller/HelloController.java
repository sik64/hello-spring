package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController {
    @GetMapping("/hello")
    public String hello(Model model){
        model.addAttribute("data","은식님");
        return "hello"; // templates 의 hello.html을 찾는다 템플릿 뷰의 이름
    } // 컨트롤러가 리턴 문자열을 반환하면 +.html을 찾아 처리한다.
    // spring-boot-devtools로 재시작없이 적용가능

    // MVC 패턴 방식
    @GetMapping("/hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        model.addAttribute("name",name);
        return "hello-template";
    }
    // hello-mvc url로 들어온 model에서 name parameter를 뽑아 hello-template에 넣어서 전달

    //API 방식
    @GetMapping("hello-string")
    @ResponseBody // HTTP body부에 데이터를 직접 넣어주겠다.
    public String helloString(@RequestParam("name") String name){
        return "hello " + name; // "hello spring"
    }//http://localhost:8080/hello-string?name=spring 입력시 변수로 포함 문자열만 리턴

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; // 인스턴스 Object를 리턴한다면 JSON형식으로 화면에 출력
    }
    static class Hello{
        private String name;
        public String age;
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
