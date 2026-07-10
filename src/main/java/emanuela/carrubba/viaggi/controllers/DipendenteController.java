package emanuela.carrubba.viaggi.controllers;

import emanuela.carrubba.viaggi.dto.DipendenteDto;
import emanuela.carrubba.viaggi.entities.Dipendente;
import emanuela.carrubba.viaggi.services.DipendenteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dipendenti")
public class DipendenteController {

    @Autowired
    private DipendenteService dipendenteService;

    // POST: creo
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Dipendente crea(@RequestBody @Validated DipendenteDto body) {
        return dipendenteService.salvaDipendente(body);
    }

    // GET: Lista completa
    @GetMapping
    public List<Dipendente> findAll() {
        return dipendenteService.findAll();
    }

    // GET: Singolo per ID
    @GetMapping("/{id}")
    public Dipendente findById(@PathVariable Long id) {
        return dipendenteService.findById(id);
    }

    // PUT: Aggiornamento completo
    @PutMapping("/{id}")
    public Dipendente update(@PathVariable Long id, @RequestBody @Validated DipendenteDto body) {
        return dipendenteService.findByIdAndUpdate(id, body);
    }

    // PATCH: Aggiornamento parziale
    @PatchMapping("/{dipendenteId}/avatar")
    public Dipendente updateAvatar(@PathVariable Long dipendenteId, @RequestParam("avatar") MultipartFile picture) {
        return dipendenteService.uploadAvatar(dipendenteId, picture);
    }

    // DELETE: Eliminazione
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        dipendenteService.findByIdAndDelete(id);
    }
}