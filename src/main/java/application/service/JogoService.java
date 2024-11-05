package application.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import application.model.Jogo;
import application.record.JogoDTO;
import application.record.JogoListDTO;
import application.repository.JogoRepository;

@Service
public class JogoService {
    @Autowired
    private JogoRepository jogoRepo;

    public Iterable<JogoListDTO> getAllSimple(){
        return jogoRepo.findAll().stream().map(JogoListDTO::new).toList();
    }
    public Iterable<JogoDTO> getAll(){
        return jogoRepo.findAll().stream().map(JogoDTO::new).toList();
    }

    public JogoDTO add(JogoDTO jogo){
        return new JogoDTO(jogoRepo.save(new Jogo(jogo)));
    }

    public JogoDTO update(long id, JogoDTO jogo){
        Optional<Jogo> result = jogoRepo.findById(id);
        if(result.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Jogo Não Encontrado"
            );
        }
        result.get().setPlataformas(jogo.plataformas());
        result.get().setTitulo(jogo.titulo());

        return new JogoDTO(jogoRepo.save(result.get()));
    }

    public void deleteById(long id) {
        if(!jogoRepo.existsById(id)){
            throw new ResponseStatusException(
              HttpStatus.NOT_FOUND, "Jogo Não Encontrado"  
            );
        }
        jogoRepo.deleteById(id);
    }

}
