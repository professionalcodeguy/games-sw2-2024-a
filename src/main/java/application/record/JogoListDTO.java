package application.record;

import application.model.Jogo;

public record JogoListDTO() {
    public  JogoListDTO(Jogo jogo){
        this(jogo.getId(), jogo.getTitulo()); 
    }
}
