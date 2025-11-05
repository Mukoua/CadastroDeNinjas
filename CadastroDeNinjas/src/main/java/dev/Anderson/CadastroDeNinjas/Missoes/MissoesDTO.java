package dev.Anderson.CadastroDeNinjas.Missoes;

import dev.Anderson.CadastroDeNinjas.Missoes.MissoesModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class MissoesDTO {

    private  Long id;
    private String nome;
    private String dificuldade;
    private MissoesModel missoes;

}
