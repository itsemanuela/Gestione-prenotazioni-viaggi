package emanuela.carrubba.viaggi.dto;

import java.time.LocalDate;

public record ViaggiDto(
        String destinazione,
        LocalDate data,
        String stato
) {}