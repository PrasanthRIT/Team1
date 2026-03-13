package org.example.tigerboard.controller;

import org.example.tigerboard.service.TigerBoardService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TigerBoardController {

    private final TigerBoardService tigerBoardService;

    public TigerBoardController(TigerBoardService tigerBoardService) {
        this.tigerBoardService = tigerBoardService;
    }

    @GetMapping("")
}

