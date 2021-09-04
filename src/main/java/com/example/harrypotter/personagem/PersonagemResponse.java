package com.example.harrypotter.personagem;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class PersonagemResponse {

    private Long id;
    private Personagem personagem;
    private Casa casa;

    public PersonagemResponse(Personagem personagem, Casa casa){
        this.id = personagem.getId();
        this.personagem = personagem;
        this.casa = casa;
    }
}
