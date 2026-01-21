package br.com.banhoetosa.gihpet.servicos.repository.specs;

import br.com.banhoetosa.gihpet.servicos.domain.entity.Servico;
import org.springframework.data.jpa.domain.Specification;

public interface ServicoSpecs {

    static Specification<Servico> tipoServicoLike(String tipoServico) {
        return (root, query, cb) ->
                cb.like(cb.upper(root.get("tipoServico")),
                        "%" + tipoServico.toUpperCase() + "%");
    }

    static Specification<Servico> statusServicoLike(String statusServico) {
        return (root, query, cb) ->
                cb.like(cb.upper(root.get("statusServico")),
                        "%" + statusServico.toUpperCase() + "%");
    }

    static Specification<Servico> taxiDogEqual(Boolean taxiDog) {
        return (root, query, cb) -> cb.equal(root.get("taxiDog"), taxiDog);
    }

    static Specification<Servico> horarioTaxiDogLike(String horarioTaxiDog) {
        return (root, query, cb) ->
                cb.like(cb.upper(root.get("horarioTaxiDog")),
                        "%" + horarioTaxiDog.toUpperCase() + "%");
    }

    static Specification<Servico> statusAgendamentoLike(String statusAgendamento) {
        return (root, query, cb) ->
                cb.like(cb.upper(root.join("dataAgendamento").get("statusAgendamento")),
                        "%" + statusAgendamento.toUpperCase() + "%");
    }
}