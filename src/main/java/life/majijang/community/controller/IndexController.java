package life.majijang.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by Administrator on 2019/12/20/0020.
 */
@Controller
public class IndexController {
  @GetMapping("/")
  public String hello() {
    return "index";
  }
}
