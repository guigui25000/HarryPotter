package com.example.harrypotter.personagem;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonagemRepositori extends JpaRepository<Personagem,Long> {
    Personagem findPersonagemByNome(String nome);

}
