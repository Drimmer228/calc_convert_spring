package com.example.calc_convert.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HomeController {
    @GetMapping("/home")
    String getHome(){
        return "home";
    }

    @PostMapping("/calc")
    public String calculate(@RequestParam double num1,
                            @RequestParam double num2,
                            @RequestParam String operator, Model model) {
        double result = 0;
        switch (operator) {
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0) {
                    result = num1 / num2;
                } else {
                    model.addAttribute("error", "Деление на ноль невозможно");
                    return "calc";
                }
                break;
        }
        model.addAttribute("result", result);
        return "result";
    }

    @GetMapping("/calc")
    String getCalc(){
        return "calc";
    }

    @GetMapping("/converter")
    String getConverter(){
        return "converter";
    }

    @PostMapping("/convert")
    public String convertCurrency(@RequestParam String fromCurrency,
                                  @RequestParam String toCurrency,
                                  @RequestParam double amount,
                                  Model model) {
        double usdToRubRate = 75.0;
        double eurToRubRate = 90.0;

        double convertedAmount = 0;

        if (fromCurrency.equals("USD") && toCurrency.equals("RUB")) {
            convertedAmount = amount * usdToRubRate;
        } else if (fromCurrency.equals("EUR") && toCurrency.equals("RUB")) {
            convertedAmount = amount * eurToRubRate;
        } else if (fromCurrency.equals("RUB") && toCurrency.equals("USD")) {
            convertedAmount = amount / usdToRubRate;
        } else if (fromCurrency.equals("RUB") && toCurrency.equals("EUR")) {
            convertedAmount = amount / eurToRubRate;
        }

        model.addAttribute("result", convertedAmount);
        return "result";
    }
}
