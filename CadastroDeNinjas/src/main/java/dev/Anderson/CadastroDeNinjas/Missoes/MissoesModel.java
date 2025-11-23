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

    @Column(name="id")
    private Long id;

    @Column(name = "nomeDaMissao")
    private String nomeDaMissao;

    @Column(name = "dificuldade")
    private String dificuldade;

     //@OneToMany Uma missao pode ter varios ninjas
   /* @OneToMany(mappedBy = "missoes")
    @JsonIgnore
    private List<MissoesModel> missoes;*/

}
