package com.rocketzki.persistedthymeleaf.controller;

import com.rocketzki.persistedthymeleaf.model.Score;
import com.rocketzki.persistedthymeleaf.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ScoreController {

    private ScoreService scoreService;

    @Autowired
    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;

    }

    @GetMapping("/hall")
    public ModelAndView showHallOfFame(ModelAndView mav) {
        mav.addObject("scores", scoreService.getAllScores());
        mav.setViewName("hall");
        return mav;
    }

    @GetMapping("/sendScore")
    @ResponseBody
    public Score sendScore(@RequestParam Integer score) {
        return scoreService.saveScore(score);
    }

}
