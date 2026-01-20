package br.com.banhoetosa.gihpet.pets.domain.enums.pet;

public enum TipoAnimal {
    CACHORRO,
    GATO,
    COELHO;

    public static TipoAnimal getTipoPadrao() {
        return CACHORRO;
    }
}