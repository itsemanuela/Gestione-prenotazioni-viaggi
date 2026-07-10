package emanuela.carrubba.viaggi.controllers;

import emanuela.carrubba.viaggi.dto.ViaggiDto;
import emanuela.carrubba.viaggi.entities.Viaggi;
import emanuela.carrubba.viaggi.services.ViaggiService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/viaggi")
public class ViaggiController {

    @Autowired
    private ViaggiService viaggiService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Viaggi creaViaggio(@RequestBody @Valid ViaggiDto body) {
        return viaggiService.creaNuovoViaggio(body);
    }
}