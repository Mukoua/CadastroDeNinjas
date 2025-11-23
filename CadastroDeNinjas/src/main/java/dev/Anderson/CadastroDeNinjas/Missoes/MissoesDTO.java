package dev.Anderson.CadastroDeNinjas.Missoes;

import dev.Anderson.CadastroDeNinjas.Missoes.MissoesModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MissoesDTO {

    private Long id;
    private String nomeDaMissao;   // ← tem que ter exatamente esse nome
    private String dificuldade;    // ← tem que ter exatamente esse nome

    // GETTERS OBRIGATÓRIOS (exatamente assim!)
    public Long getId() {
        return id;
    }

    public String getNomeDaMissao() {   // ← NOME EXATO!
        return nomeDaMissao;
    }

    public String getDificuldade() {    // ← NOME EXATO!
        return dificuldade;
    }

    // setters (se precisar)
    public void setId(Long id) { this.id = id; }
    public void setNomeDaMissao(String nomeDaMissao) { this.nomeDaMissao = nomeDaMissao; }
    public void setDificuldade(String dificuldade) { this.dificuldade = dificuldade; }
}

