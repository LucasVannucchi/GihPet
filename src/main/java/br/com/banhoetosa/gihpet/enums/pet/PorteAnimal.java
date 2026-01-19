package br.com.banhoetosa.gihpet.enums.pet;

public enum PorteAnimal {
    GRANDE,
    MEDIO,
    PEQUENO;

    public static PorteAnimal getPortePadrao(){
        return MEDIO;
    }
}
