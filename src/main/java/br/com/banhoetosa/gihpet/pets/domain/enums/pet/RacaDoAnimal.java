package br.com.banhoetosa.gihpet.pets.domain.enums.pet;

public enum RacaDoAnimal {
    LABRADOR_RETRIEVER,
    GOLDEN_RETRIEVER,
    BULDOGUE,
    PASTOR_ALEMAO,
    PINSCHER,
    POODLE,
    SHIH_TZU,
    LHASA_APSO,
    MALTES,
    ROTTWEILER,
    DOBERMAN,
    BEAGLE,
    BORDER_COLLIE,
    YORKSHIRE_TERRIER,
    CHIHUAHUA,
    AKITA,
    HUSKY_SIBERIANO,
    SAMOIEDA,
    BOXER,
    DALMATA;

    public static RacaDoAnimal getRacaPadrao(){
        return SHIH_TZU;
    }
}