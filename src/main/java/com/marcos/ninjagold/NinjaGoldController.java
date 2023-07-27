package com.marcos.ninjagold;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Controller
public class NinjaGoldController {
    private int ninjaGold = 0;
    private final int deudaMax = -100;

    private List<String> activities = new ArrayList<>();

    @RequestMapping("/")
    public String index(Model model) {
        model.addAttribute("ninjaGold", ninjaGold);
        model.addAttribute("activities", activities);
        return "index";
    }

    @PostMapping("/farm")
    public String farm() {
        int goldEarned = new Random().nextInt(11) + 10;
        ninjaGold += goldEarned;
        activities.add("Ganaste " + goldEarned + " de oro de la granja. (" + LocalDateTime.now() + ")");
        checkDeuda();
        return "redirect:/";
    }

    @PostMapping("/cave")
    public String cave() {
        int goldEarned = new Random().nextInt(6) + 5;
        ninjaGold += goldEarned;
        activities.add("Ganaste " + goldEarned + " de oro de la cueva. (" + LocalDateTime.now() + ")");
        checkDeuda();

        return "redirect:/";
    }

    @PostMapping("/house")
    public String house() {
        int goldEarned = new Random().nextInt(4) + 2;
        ninjaGold += goldEarned;
        activities.add("Ganaste " + goldEarned + " de oro de la casa. (" + LocalDateTime.now() + ")");
        checkDeuda();

        return "redirect:/";
    }

    @PostMapping("/casino")
    public String casino() {
        int goldChange = new Random().nextInt(101) - 50;
        ninjaGold += goldChange;
        if (goldChange > 0) {
            activities.add("Ganaste " + goldChange + " de oro del casino. (" + LocalDateTime.now() + ")");
        } else {
            activities.add("Perdiste" + Math.abs(goldChange) + " de oro del casino. (" + LocalDateTime.now() + ")");
        }
        checkDeuda();

        return "redirect:/";
    }

    @PostMapping("/spa")
    public String spa() {
        int goldLost = new Random().nextInt(16) + 5;
        ninjaGold -= goldLost;
        activities.add("Perdiste " + goldLost + " de oro en el Spa. (" + LocalDateTime.now() + ")");
        checkDeuda();

        return "redirect:/";
    }

    @PostMapping("/reset")
    public String reset() {
        ninjaGold = 0;
        activities.clear();
        return "redirect:/";
    }

    private void checkDeuda() {
        if (ninjaGold <= deudaMax) {
            ninjaGold = 0;
            activities.clear();
            activities.add("¡Has sido enviado a la cárcel por acumular demasiadas deudas! (" + LocalDateTime.now() + ")");

        }
    }
}
