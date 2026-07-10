package emanuela.carrubba.viaggi.services;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import emanuela.carrubba.viaggi.dto.DipendenteDto;
import emanuela.carrubba.viaggi.entities.Dipendente;
import emanuela.carrubba.viaggi.exceptions.EmailException;
import emanuela.carrubba.viaggi.exceptions.NotFoundException;
import emanuela.carrubba.viaggi.exceptions.UserNameException;
import emanuela.carrubba.viaggi.repositories.DipendenteRepository;
import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

@Service
public class DipendenteService {
    @Autowired
    Cloudinary cloudinary;

    @Autowired
    private DipendenteRepository dipendenteRepository;

    public Dipendente salvaDipendente(DipendenteDto dati) {
        // verifico se l'email è già presente
        if (dipendenteRepository.existsByEmail(dati.email())) {
            throw new EmailException("L'email " + dati.email() + " è già in uso!");
        }
        if (dipendenteRepository.existsByUsername(dati.username())) {
            throw new UserNameException("Lo username " + dati.username() + " è già preso!");
        }
        Dipendente nuovoDipendente = new Dipendente();

        nuovoDipendente.setUsername(dati.username());
        nuovoDipendente.setNome(dati.nome());
        nuovoDipendente.setCognome(dati.cognome());
        nuovoDipendente.setEmail(dati.email());
nuovoDipendente.setAvatarUrl(dati.avatarUrl());

        return dipendenteRepository.save(nuovoDipendente);
    }

    public Page<Dipendente> findAll(int page, int size, String sortBy) {
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        return dipendenteRepository.findAll(pageable);
    }
// cerco per id
    public Dipendente findById(Long id) {
        return dipendenteRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Dipendente non trovato"));
    }

    //cerco per id e aggiorno tipo img profilo per la patch

    public Dipendente uploadAvatar(Long id, MultipartFile picture) {
        Dipendente found = this.findById(id);

        try {
            Map result = cloudinary.uploader().upload(picture.getBytes(), ObjectUtils.emptyMap());

            found.setAvatarUrl((String) result.get("secure_url"));

            return dipendenteRepository.save(found);

        } catch (IOException e) {
            throw new RuntimeException("Errore durante l'upload del file su Cloudinary");
        }
    }


    // metodo aggiornamento completo (PUT)
    public Dipendente findByIdAndUpdate(Long id, DipendenteDto body) {
        Dipendente found = this.findById(id);

        // aggiorno i campi
        found.setNome(body.nome());
        found.setCognome(body.cognome());
        found.setUsername(body.username());
        found.setEmail(body.email());

        // salvo nel db
        return dipendenteRepository.save(found);
    }


    //elimino
    public void findByIdAndDelete(Long id) {
        Dipendente found = this.findById(id);
        dipendenteRepository.delete(found);
    }
}