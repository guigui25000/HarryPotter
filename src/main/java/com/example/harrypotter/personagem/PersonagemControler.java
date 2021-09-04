package com.example.harrypotter.personagem;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import rx.Observable;
import rx.Single;

@RequestMapping("/personagem")
@RestController
public class PersonagemControler {

    private final PersonagemSerivce personagemSerivce;

    @Autowired
    public PersonagemControler(PersonagemSerivce personagemSerivce){
        this.personagemSerivce = personagemSerivce;
    }

    @PostMapping(value = "/novo")
    public Single<Personagem> criarPersonagem(@RequestBody PersonagemRequest personagemRequest){
        return personagemSerivce.criarPersonagem(personagemRequest);
    }

    @GetMapping(value = "/todos")
    public Observable<PersonagemResponse> findAll(){
        return personagemSerivce.findAll();
    }

    @GetMapping(value = "/nome/{nome}")
    public Single<PersonagemResponse> findByName(@RequestParam String nome){
        return personagemSerivce.findByName(nome);
    }
}
