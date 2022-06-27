package org.example.BookSpring.bookStorage.Magazines;

import org.example.BookSpring.bookStorage.Books.Book;
import org.example.BookSpring.bookStorage.ItemNotFoundException;
import org.example.BookSpring.bookStorage.ItemOp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MagazineController {
    private final MagazineService magazineService;

    @Autowired
    public MagazineController(MagazineService magazineService) {
        this.magazineService = magazineService;
    }

    @GetMapping("/magazines")
    public List<ItemOp> getAllMagazines(){
        return magazineService.getAll();
    }

    @GetMapping("/magazines/{magazineId}")
    public ItemOp getMagazine(@PathVariable Integer magazineId){
        return magazineService.get(magazineId)
                .map(item -> (Magazine) item)
                .orElseThrow(() -> new ItemNotFoundException(magazineId));
    }

    @PostMapping("/magazines")
    public Boolean addMagazine(@RequestBody Book book){
        return magazineService.add(book);
    }

    @DeleteMapping("/magazines/{magazineId}")
    public Boolean deleteMagazine(@PathVariable Integer magazineId){
        return magazineService.delete(magazineId);
    }

    @PutMapping("/magazines/{magazineId}")
    public ItemOp updateMagazine(@PathVariable Integer magazineId, @RequestBody Magazine nMaga)
    {
        return magazineService.update(magazineId, nMaga)
                .map(magazine -> {
                    magazine.setId(nMaga.getId());
                    magazine.setTitle(nMaga.getTitle());
                    magazine.setAuthor(nMaga.getAuthor());
                    magazine.setDateWrite(nMaga.getDateWrite());
                    magazine.setPages(nMaga.getPages());

                    return (Magazine) magazine;
                })
                .orElseThrow(() ->new IllegalArgumentException(""));
    }
}
