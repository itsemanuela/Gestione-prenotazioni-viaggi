package emanuela.carrubba.viaggi.controllers;



import emanuela.carrubba.viaggi.dto.PrenotazioneDto;
import emanuela.carrubba.viaggi.entities.Prenotazione;
import emanuela.carrubba.viaggi.services.PrenotazioneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService service;

    @PostMapping
    public ResponseEntity<Prenotazione> save(@RequestBody PrenotazioneDto body) {
        Prenotazione nuovaPrenotazione = service.effettuaPrenotazioneViaggio(body);
        return new ResponseEntity<>(nuovaPrenotazione, HttpStatus.CREATED);
    }
}