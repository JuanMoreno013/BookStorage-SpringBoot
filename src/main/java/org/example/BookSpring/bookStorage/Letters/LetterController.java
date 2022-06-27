package org.example.BookSpring.bookStorage.Letters;

import org.example.BookSpring.bookStorage.ItemNotFoundException;
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
    public List<ItemOp> getAllLetter(){
        return letterService.getAll();
    }

    @GetMapping("/letters/{letterId}")
    public ItemOp getMLetter(@PathVariable Integer letterId){
        return letterService.get(letterId)
                .map(item -> (Letter) item)
                .orElseThrow(() -> new ItemNotFoundException(letterId));
    }

    @PostMapping("/letters")
    public Boolean addLetter(@RequestBody Letter letter){
        return letterService.add(letter);
    }

    @DeleteMapping("/letters/{letterId}")
    public Boolean deleteLetter(@PathVariable Integer letterId){
        return letterService.delete(letterId);
    }

    @PutMapping("/letters/{letterId}")
    public ItemOp updateLetter(@PathVariable Integer letterId, @RequestBody Letter nLetter)
    {
        return letterService.update(letterId, nLetter)
                .map(letter -> {
                    letter.setId(nLetter.getId());
                    letter.setTitle(nLetter.getTitle());
                    letter.setAuthor(nLetter.getAuthor());
                    letter.setDateWrite(nLetter.getDateWrite());
                    letter.setPages(nLetter.getPages());

                    return (Letter) letter;
                })
                .orElseThrow(() ->new IllegalArgumentException(""));
    }
}

