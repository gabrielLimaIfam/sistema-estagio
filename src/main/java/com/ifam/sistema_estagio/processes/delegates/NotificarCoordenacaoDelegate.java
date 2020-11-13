package com.ifam.sistema_estagio.processes.delegates;

import com.ifam.sistema_estagio.dto.BancaDto;
import com.ifam.sistema_estagio.dto.NotificacaoBancasDto;
import com.ifam.sistema_estagio.dto.UsuarioDto;
import com.ifam.sistema_estagio.entity.Coordenadora;
import com.ifam.sistema_estagio.entity.NotificacaoBancas;
import com.ifam.sistema_estagio.processes.SolicitarBancaProcess;
import com.ifam.sistema_estagio.services.CoordenadoraService;
import com.ifam.sistema_estagio.services.NoticacaoBancasService;
import com.ifam.sistema_estagio.util.FormatarData;
import com.ifam.sistema_estagio.util.enums.FuncaoEstagio;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service("notificarCoordenacaoService")
public class NotificarCoordenacaoDelegate implements JavaDelegate {

    @Autowired
    private CoordenadoraService coordenadoraService;

    @Autowired
    private NoticacaoBancasService noticacaoBancasService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        String idProcesso = execution.getProcessInstanceId();
        BancaDto banca = (BancaDto) execution.getVariable(SolicitarBancaProcess.VAR_BANCA);

        Optional<UsuarioDto> coordenadoraDto = banca
                .getParticipantes()
                .stream()
                .filter(participante -> participante.getFuncao() == FuncaoEstagio.COORDENADOR)
                .findFirst();

        Boolean coordenadorNaoExiste = !coordenadoraDto.isPresent();

        if(coordenadorNaoExiste) throw new Exception("[notificação-banca] Sem coordenador(a) de banca");

        String nomeCompleto = coordenadoraDto.get().getNome();
        Optional<Coordenadora> coordenadora = coordenadoraService.findByNomeCompleto(nomeCompleto);

        Boolean coordenadorNaoEncontrado = !coordenadora.isPresent();

        if(coordenadorNaoEncontrado) throw new Exception("[notificação-banca] Coordenadora não existe.");

        String dataNotificacao = FormatarData.porMascaraDataPadrao(new Date());

        noticacaoBancasService.salvar(NotificacaoBancas.builder()
                .dataNotificacao(dataNotificacao)
                .idProcesso(idProcesso)
                .coordenadora(coordenadora.get())
                .build()
        );
    }
}
