package org.example.BookSpring.bookStorage.Letters;

import org.example.BookSpring.bookStorage.ItemOp;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Letter> getAllLetter() {
        return letterService.getAll();
    }

    @GetMapping("/letters/{letterId}")
    public ItemOp getMLetter(@PathVariable Integer letterId) {
        return letterService.get(letterId);
    }

    @PostMapping("/letters")
    public Boolean addLetter(@RequestBody Letter letter) {
        return letterService.save(letter);
    }

    @DeleteMapping("/letters/{letterId}")
    public Boolean deleteLetter(@PathVariable Integer letterId) {
        return letterService.delete(letterId);
    }

    @PutMapping("/letters/{letterId}")
    public Boolean updateLetter(@PathVariable Integer letterId, @RequestBody Letter nLetter) {
        return letterService.update(letterId, nLetter);
    }
}

