package emanuela.carrubba.viaggi.dto;


import java.time.LocalDate;

public record PrenotazioneDto(
        Long dipendenteId,
        Long viaggioId,
        LocalDate dataRichiesta,
        String note
) {}
