package org.example.BookSpring.bookStorage.Magazines;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MagazineController {
    private final MagazineService<Magazine> magazineService;

    @Autowired
    public MagazineController(MagazineService<Magazine> magazineService) {
        this.magazineService = magazineService;
    }

    @GetMapping("/magazines")
    public List<Magazine> getAllMagazines() {
        return magazineService.getAll();
    }

    @GetMapping("/magazines/{magazineId}")
    public Magazine getMagazine(@PathVariable Integer magazineId) {
        return magazineService.get(magazineId).orElse(null);
    }

    @PostMapping("/magazines")
    public Boolean addMagazine(@RequestBody Magazine magazine) {
        return magazineService.add(magazine);
    }

    @DeleteMapping("/magazines/{magazineId}")
    public Boolean deleteMagazine(@PathVariable Integer magazineId) {
        return magazineService.delete(magazineId);
    }

    @PutMapping("/magazines/{magazineId}")
    public Magazine updateMagazine(@PathVariable Integer magazineId, @RequestBody Magazine nMaga) {
        return magazineService.update(magazineId, nMaga)
                .map(magazine -> {
                    magazine.setId(nMaga.getId());
                    magazine.setTitle(nMaga.getTitle());
                    magazine.setAuthor(nMaga.getAuthor());
                    magazine.setDateWrite(nMaga.getDateWrite());
                    magazine.setPages(nMaga.getPages());

                    return magazine;
                })
                .orElse(null);
    }
}
