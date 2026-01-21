package br.com.banhoetosa.gihpet.servicos.domain.validator;

import br.com.banhoetosa.gihpet.servicos.domain.entity.Servico;
import br.com.banhoetosa.gihpet.servicos.domain.enums.pagamento.TipoPagamento;
import br.com.banhoetosa.gihpet.servicos.domain.enums.servico.HorarioTaxiDog;
import br.com.banhoetosa.gihpet.servicos.domain.enums.servico.StatusServico;
import br.com.banhoetosa.gihpet.servicos.domain.enums.pagamento.StatusPagamento;
import br.com.banhoetosa.gihpet.servicos.domain.enums.servico.TipoServico;
import br.com.banhoetosa.gihpet.common.exceptions.RegistroDuplicadoException;
import br.com.banhoetosa.gihpet.servicos.repository.ServicoRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class ServicoValidator {

    private final ServicoRepository servicoRepository;

    public ServicoValidator(ServicoRepository servicoRepository) {
        this.servicoRepository = servicoRepository;
    }

    public void validar(Servico servico){
        if (servico == null){
            throw new IllegalArgumentException("Serviço não pode ser nulo!");
        }

        aplicarValoresPadrao(servico);

        if (existeServicoAgendado(servico)){
            throw new RegistroDuplicadoException("Já existe um serviço agendado para este pet no mesmo horario!");
        }
    }

    public void aplicarValoresPadrao(Servico servico) {
        if (servico == null) {
            throw new IllegalArgumentException("Serviço não pode ser nulo");
        }

        if (servico.getTipoServico() == null) {
            servico.setTipoServico(TipoServico.getTipoPadrao());
        }

        if (servico.getPreco() == null) {
            servico.setPreco(servico.getTipoServico().getPrecoPadrao());
        }

        if (servico.getTipoPagamento() == null) {
            servico.setTipoPagamento(TipoPagamento.PIX);
        }

        if (servico.getStatusPagamento() == null) {
            servico.setStatusPagamento(StatusPagamento.PENDENTE);
        }

        if (servico.getTaxiDog() == null) {
            servico.setTaxiDog(Boolean.FALSE);
        }

        if (Boolean.FALSE.equals(servico.getTaxiDog())) {
            servico.setHorarioTaxiDog(null);
        }

        if (Boolean.TRUE.equals(servico.getTaxiDog()) && servico.getHorarioTaxiDog() == null){
            servico.setHorarioTaxiDog(HorarioTaxiDog.ENTRADA_E_SAIDA);
        }

        if (servico.getPacote() == null) {
            servico.setPacote(Boolean.FALSE);
        }

        if (servico.getStatusServico() == null) {
            servico.setStatusServico(StatusServico.AGUARDANDO_APROVACAO);
        }
    }

    private boolean existeServicoAgendado(Servico servico){
        Optional<Servico> servicoExistente = servicoRepository.findByPetIdAndDataAgendamentoDataHora(
                servico.getPet().getId(),
                servico.getDataAgendamento().getDataHora()
        );

        if (servico.getId() == null){
            return servicoExistente.isPresent();
        }
        return servicoExistente.isPresent()
                && !servico.getId().equals(servicoExistente.get().getId());
    }
}