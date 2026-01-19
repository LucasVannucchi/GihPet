package br.com.banhoetosa.gihpet.enums.pet;

public enum TipoAnimal {
    CACHORRO,
    GATO,
    COELHO;

    public static TipoAnimal getTipoPadrao() {
        return CACHORRO;
    }
}