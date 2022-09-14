package org.example.BookSpring.bookStorage.Magazines;

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
    public List<Magazine> getAllMagazines() {
        return magazineService.getAll();
    }

    @GetMapping("/magazines/{magazineId}")
    public Magazine getMagazine(@PathVariable Integer magazineId) {
        return magazineService.get(magazineId);
    }

    @PostMapping("/magazines")
    public Boolean addMagazine(@RequestBody Magazine magazine) {
        return magazineService.save(magazine);
    }

    @DeleteMapping("/magazines/{magazineId}")
    public Boolean deleteMagazine(@PathVariable Integer magazineId) {
        return magazineService.delete(magazineId);
    }

    @PutMapping("/magazines/{magazineId}")
    public Boolean updateMagazine(@PathVariable Integer magazineId, @RequestBody Magazine magazine) {
       return magazineService.update(magazineId, magazine);
    }
}
