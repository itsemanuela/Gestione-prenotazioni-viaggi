package emanuela.carrubba.viaggi.services;

import emanuela.carrubba.viaggi.dto.PrenotazioneDto;
import emanuela.carrubba.viaggi.entities.Dipendente;
import emanuela.carrubba.viaggi.entities.Prenotazione;
import emanuela.carrubba.viaggi.entities.Viaggi;
import emanuela.carrubba.viaggi.exceptions.NotFoundException;
import emanuela.carrubba.viaggi.exceptions.PrenotazioneEsistente;
import emanuela.carrubba.viaggi.repositories.DipendenteRepository;
import emanuela.carrubba.viaggi.repositories.PrenotazioneRepository;
import emanuela.carrubba.viaggi.repositories.ViaggiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    @Autowired
    private ViaggiRepository viaggiRepository;

    public Prenotazione effettuaPrenotazioneViaggio(PrenotazioneDto richiesta) {

        // verifico se il dipendente ha già una prenotazione in quella data
        boolean esisteGia = prenotazioneRepository.existsByDipendenteIdAndDataRichiesta(
                richiesta.dipendenteId(),
                richiesta.dataRichiesta()
        );

        if (esisteGia) {
            throw new PrenotazioneEsistente("Prenotazione esistente!");
        }


        Dipendente dipendente = dipendenteRepository.findById(richiesta.dipendenteId())
                .orElseThrow(() -> new NotFoundException("Dipendente non trovato con ID: " + richiesta.dipendenteId()));

        Viaggi viaggio = viaggiRepository.findById(richiesta.viaggioId())
                .orElseThrow(() -> new NotFoundException("Viaggio non trovato con ID: " + richiesta.viaggioId()));

        // creo prenotazione
        Prenotazione nuovaPrenotazione = new Prenotazione();
        nuovaPrenotazione.setDipendente(dipendente);
        nuovaPrenotazione.setViaggi(viaggio);
        nuovaPrenotazione.setDataRichiesta(richiesta.dataRichiesta());
        nuovaPrenotazione.setNote(richiesta.note());

        // salvo
        return prenotazioneRepository.save(nuovaPrenotazione);
    }
}