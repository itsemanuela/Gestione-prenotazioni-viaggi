package emanuela.carrubba.viaggi.services;


import emanuela.carrubba.viaggi.StatoViaggio;
import emanuela.carrubba.viaggi.dto.ViaggiDto;
import emanuela.carrubba.viaggi.entities.Viaggi;
import emanuela.carrubba.viaggi.repositories.ViaggiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViaggiService {

    @Autowired
    private ViaggiRepository viaggiRepository;

    public Viaggi creaNuovoViaggio(ViaggiDto dati) {
        Viaggi nuovoViaggio = new Viaggi();

        nuovoViaggio.setDestinazione(dati.destinazione());
        nuovoViaggio.setData(dati.data());
        nuovoViaggio.setStato(StatoViaggio.valueOf(dati.stato().toUpperCase()));

        return viaggiRepository.save(nuovoViaggio);
    }
}