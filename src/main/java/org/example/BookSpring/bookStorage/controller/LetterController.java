package org.example.BookSpring.bookStorage.controller;

import org.example.BookSpring.bookStorage.dto.LetterDto;
import org.example.BookSpring.bookStorage.dto.TakenItemDto;
import org.example.BookSpring.bookStorage.service.LetterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LetterController {
    private final LetterService letterService;

    @Autowired
    public LetterController(LetterService letterService) {
        this.letterService = letterService;
    }

    @GetMapping("/letters")
    public List<LetterDto> getAllLetter() {
        return letterService.getAll();
    }

    @GetMapping("/letters/{letterDtoId}")
    public LetterDto getMLetter(@PathVariable Integer letterDtoId) {
        return letterService.get(letterDtoId);
    }

    @PostMapping("/letters")
    @ResponseStatus(HttpStatus.CREATED)
    public LetterDto addLetter(@RequestBody LetterDto letterDto) {
        return letterService.save(letterDto);
    }

    @DeleteMapping("/letters/{letterDtoId}")
    public Boolean deleteLetter(@PathVariable Integer letterDtoId) {
        return letterService.delete(letterDtoId);
    }

    @PutMapping("/letters/{letterDtoId}")
    public Boolean updateLetter(@PathVariable Integer letterDtoId, @RequestBody LetterDto letterDto) {
        return letterService.update(letterDtoId, letterDto);
    }

    @PutMapping("/letters/{letterDtoId}/takenBy")
    public Boolean updateLetterTakenByUser(@PathVariable Integer letterDtoId, @RequestBody TakenItemDto takenItemDto) {
        return letterService.updateLetterTakenBy(letterDtoId, takenItemDto);
    }
}

