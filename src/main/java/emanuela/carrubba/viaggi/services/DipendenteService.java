package emanuela.carrubba.viaggi.services;

import emanuela.carrubba.viaggi.dto.DipendenteDto;
import emanuela.carrubba.viaggi.entities.Dipendente;
import emanuela.carrubba.viaggi.repositories.DipendenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DipendenteService {

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public Dipendente registraNuovoDipendente(DipendenteDto dati) {
        Dipendente nuovoDipendente = new Dipendente();

        nuovoDipendente.setUsername(dati.username());
        nuovoDipendente.setNome(dati.nome());
        nuovoDipendente.setCognome(dati.cognome());
        nuovoDipendente.setEmail(dati.email());

        return dipendenteRepository.save(nuovoDipendente);
    }
}