package com.example.harrypotter.personagem;


import com.example.harrypotter.api.ConsumirAPI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rx.Observable;
import rx.Single;

import java.util.stream.Collectors;

@Service
public class PersonagemSerivce {

    private final PersonagemRepositori personagemRepositori;
    private final ConsumirAPI consumirAPI;

    @Autowired
    public PersonagemSerivce(PersonagemRepositori personagemRepositori, ConsumirAPI consumirAPI){
        this.personagemRepositori = personagemRepositori;
        this.consumirAPI = consumirAPI;
    }

    public Single<Personagem> criarPersonagem(PersonagemRequest personagem){
        return Single.create(singleSubscriber -> {
            Personagem novo = consumirAPI.sortingHat();
            novo.setNome(personagem.getNome());
            Personagem save = personagemRepositori.save(novo);
            singleSubscriber.onSuccess(save);
        });
    }

    public Single<PersonagemResponse> findByName(String name){
        return Single.create(singleSubscriber -> {
            Personagem personagem = personagemRepositori.findPersonagemByNome(name);
            singleSubscriber.onSuccess(personagemResponse(personagem));
        });
    }

    public Observable<PersonagemResponse> findAll(){
        return Observable.from(personagemRepositori.findAll()
                .stream().map(this::personagemResponse).collect(Collectors.toList()));
    }

    public PersonagemResponse personagemResponse(Personagem personagem){
        Casa casa = consumirAPI.findHouse(personagem);
        return  new PersonagemResponse(personagem,casa);
    }


    public Personagem DTOparaNormal(PersonagemRequest personagemRequest){
        Personagem personagem = new Personagem();
        personagem.setNome(personagemRequest.getNome());
        return personagem;
    }

}
