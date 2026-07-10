package emanuela.carrubba.viaggi.controllers;

import emanuela.carrubba.viaggi.dto.PrenotazioneDto;
import emanuela.carrubba.viaggi.entities.Prenotazione;
import emanuela.carrubba.viaggi.services.PrenotazioneService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Prenotazione creaPrenotazione(@RequestBody @Valid PrenotazioneDto body) {
        return prenotazioneService.effettuaPrenotazioneViaggio(body);
    }
}
