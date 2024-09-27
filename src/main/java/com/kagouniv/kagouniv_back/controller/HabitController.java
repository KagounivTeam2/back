package com.kagouniv.kagouniv_back.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/habit")
@RequiredArgsConstructor
@Tag(name="Habit Controller", description = "Habit API")
public class HabitController { }
