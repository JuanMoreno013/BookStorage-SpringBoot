package org.example.BookSpring.bookStorage.controller;

import org.example.BookSpring.bookStorage.dto.MagazineDto;
import org.example.BookSpring.bookStorage.dto.TakenItemDto;
import org.example.BookSpring.bookStorage.service.MagazineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    public List<MagazineDto> getAllMagazines() {
        return magazineService.getAll();
    }

    @GetMapping("/magazines/{magazineDtoId}")
    public MagazineDto getMagazine(@PathVariable Integer magazineDtoId) {
        return magazineService.get(magazineDtoId);
    }

    @PostMapping("/magazines")
    @ResponseStatus(HttpStatus.CREATED)
    public MagazineDto addMagazine(@RequestBody MagazineDto magazineDto) {
        return magazineService.save(magazineDto);
    }

    @DeleteMapping("/magazines/{magazineDtoId}")
    public Boolean deleteMagazine(@PathVariable Integer magazineDtoId) {
        return magazineService.delete(magazineDtoId);
    }

    @PutMapping("/magazines/{magazineDtoId}")
    public Boolean updateMagazine(@PathVariable Integer magazineDtoId, @RequestBody MagazineDto magazineDto) {
       return magazineService.update(magazineDtoId, magazineDto);
    }

    @PutMapping("/magazines/{magazineDtoId}/takenBy")
    public Boolean updateMagazineTakenByUser(@PathVariable Integer magazineDtoId,  @RequestBody TakenItemDto takenItemDto) {
        return magazineService.updateMagazineTakenBy(magazineDtoId, takenItemDto);
    }
}
