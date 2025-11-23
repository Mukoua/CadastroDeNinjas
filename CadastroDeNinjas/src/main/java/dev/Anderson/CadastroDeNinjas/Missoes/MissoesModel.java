package dev.Anderson.CadastroDeNinjas.Missoes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;


@Entity
@Table(name = "tb_missoes")
@NoArgsConstructor
@AllArgsConstructor
@Data
@ToString


public class MissoesModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome_da_missao")
    private String nomeDaMissao;

    private String dificuldade;

    // GETTERS OBRIGATÓRIOS (EXATAMENTE ASSIM!)
    public Long getId() {
        return id;
    }

    public String getNomeDaMissao() {  // ← EXATAMENTE ESSE NOME!
        return nomeDaMissao;
    }

    public String getDificuldade() {   // ← EXATAMENTE ESSE NOME!
        return dificuldade;
    }

    // setters (se precisar)
    public void setId(Long id) { this.id = id; }
    public void setNomeDaMissao(String nomeDaMissao) { this.nomeDaMissao = nomeDaMissao; }
    public void setDificuldade(String dificuldade) { this.dificuldade = dificuldade; }
}
//@OneToMany Uma missao pode ter varios ninjas
   /* @OneToMany(mappedBy = "missoes")
    @JsonIgnore
    private List<MissoesModel> missoes;*/


