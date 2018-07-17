package com.podskoczy.money.expense;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Michal on 2018-02-11.
 */
@AllArgsConstructor
@Controller
@RestController
public class PlannedExpenseController {

    private PlannedExpenseService plannedExpenseService;

    @RequestMapping(method = RequestMethod.GET, path = "/overdrawn")
    public boolean checkForOverdrawn(@RequestParam(value = "category") Category category) {
        return Math.random() > 0.5;
    }

}
