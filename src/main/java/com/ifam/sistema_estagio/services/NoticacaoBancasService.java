package com.ifam.sistema_estagio.services;

import com.ifam.sistema_estagio.entity.NotificacaoBancas;
import com.ifam.sistema_estagio.repository.NotificacaoBancasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class NoticacaoBancasService extends GenericService<NotificacaoBancas, NotificacaoBancasRepository>{

    private final Boolean NAO_VISUALIZADO = false;
    private final Boolean VISUALIZADO = true;
    private final Boolean NAO_EXISTE = false;
    private final Boolean JA_VISUALIZADO = false;

    @Autowired
    private NotificacaoBancasRepository repository;

    public List<NotificacaoBancas> listarBancasAdicionadas(){
        return repository.findByJaVisualizado(NAO_VISUALIZADO);
    }

    public Boolean visualizarNotificacao(Integer id) throws Exception {
        Optional<NotificacaoBancas> notificacaoBanca = encontrarPorId(id);

        if(!notificacaoBanca.isPresent()) return NAO_EXISTE;

        if(notificacaoBanca.get().getJaVisualizado()) return JA_VISUALIZADO;

        atualizar(id, notificacaoBanca.get());

        return VISUALIZADO;
    }
}
