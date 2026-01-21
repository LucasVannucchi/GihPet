package br.com.banhoetosa.gihpet.usuarios.repository.specs;

import br.com.banhoetosa.gihpet.usuarios.domain.models.Cliente;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public interface ClienteSpecs {

    public static Specification<Cliente> nomeLike(String nome){
        return (root, query, cb) ->
                cb.like(cb.upper(root
                        .get("nome")), "%" +
                        nome.toUpperCase() + "%");
    }

    public static Specification<Cliente> cidadeLike(String cidade){
        return (root, query, cb) -> {
            Join<Object, Object> endereco = root.join("enderecos", JoinType.INNER);
            return cb.like(cb.upper(endereco
                    .get("cidade")), "%" +
                    cidade.toUpperCase() + "%");
        };
    }

    public static Specification<Cliente> bairroLike(String bairro) {
        return (root, query, cb) -> {
            Join<Object, Object> endereco = root.join("enderecos", JoinType.INNER);
            return cb.like(cb.upper(endereco
                    .get("bairro")), "%" +
                    bairro.toUpperCase() + "%");
        };
    }

    public static Specification<Cliente> logradouroLike(String logradouro){
        return (root, query, cb) -> {
            Join<Object, Object> endereco = root.join("enderecos", JoinType.INNER);
            return cb.like(cb.upper(endereco
                    .get("logradouro")), "%" +
                    logradouro.toUpperCase() + "%");
        };
    }

    public static Specification<Cliente> nomePetLike(String nome){
        return (root, query, cb) -> {
            Join<Object, Object> pet = root.join("pets", JoinType.INNER);
            return cb.like(cb.upper(pet
                    .get("nome")), "%" +
                    nome.toUpperCase() + "%");
        };
    }
}
