package org.serratec.avaliacao.exception;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErroResposta {

    private Integer status;
    private String titulo;
    private LocalDateTime dataHora;
    private List<String> erros;

}
