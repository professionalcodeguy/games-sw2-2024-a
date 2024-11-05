package application.model;

import java.util.Set;

import application.record.JogoDTO;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "jogos")
public class Jogo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private String titulo;
    @ManyToMany

    @JoinTable(name = "jogos_possuem_plataformas",
        joinColumns = @JoinColumn(name = "id_jogo"),
        inverseJoinColumns = @JoinColumn(name = "id_plataforma"))
    private Set<Plataforma> plataformas;
    
    public Jogo(JogoDTO jogo){
        this.id = jogo.id();
        this.titulo = jogo.titulo();
        this.plataformas = jogo.plataformas();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Set<Plataforma> getPlataformas() {
        return plataformas;
    }

    public void setPlataformas(Set<Plataforma> plataformas) {
        this.plataformas = plataformas;
    }

    public Jogo(){}
   
}
