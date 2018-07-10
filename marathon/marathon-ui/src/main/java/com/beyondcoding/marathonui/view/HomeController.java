package com.beyondcoding.marathonui.view;

import com.beyondcoding.marathonui.domain.Runner;
import com.beyondcoding.marathonui.logic.Marathon;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/")
public class HomeController {

    private final Marathon marathon;

    public HomeController(Marathon marathon) {
        this.marathon = marathon;
    }

    @GetMapping
    String page() {
        return "home";
    }

    @ModelAttribute("winner")
    Runner winner() {
        return marathon.getWinner();
    }

    @ModelAttribute("runners")
    List<Runner> runners() {
        return marathon.getRunners();
    }

    @PostMapping
    String add(Runner runner) {
        marathon.add(runner);
        return "redirect:/";
    }

    @ModelAttribute("runner")
    Runner runner() {
        return new Runner();
    }

}
