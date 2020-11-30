package com.ifam.sistema_estagio.dto;

import com.ifam.sistema_estagio.entity.NotificacaoBancas;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class NotificacaoBancasDto implements IObjetoDto<NotificacaoBancas>{
    private String id;
    private String dataNotificacao;
    private String idProcesso;
    private UsuarioDto coordenadora;

    @Override
    public NotificacaoBancas construirEntidade() {
        return NotificacaoBancas.builder()
                .id(id)
                .dataNotificacao(dataNotificacao)
                .idProcesso(idProcesso)
                .coordenadora(coordenadora.construirCoordenadora())
                .build();
    }
}
