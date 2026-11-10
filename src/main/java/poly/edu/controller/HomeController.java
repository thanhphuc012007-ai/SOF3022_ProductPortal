package poly.edu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private void addCommonData(Model model) {
        model.addAttribute("appName", "Product Portal");
        model.addAttribute("subject", "SOF3022 - Spring Boot MVC");
        model.addAttribute("semester", "Summer 2026");
        model.addAttribute("message", "Chào mừng bạn đến với hệ thống quản lý sản phẩm");
    }

    @GetMapping({"", "/", "/index"})
    public String index(Model model) {
        addCommonData(model);
        return "home/index";
    }

    @GetMapping("/about")
    public String about(Model model) {
        addCommonData(model);
        return "home/about";
    }

    @GetMapping("/help")
    public String help(Model model) {
        addCommonData(model);
        return "home/help";
    }
}
