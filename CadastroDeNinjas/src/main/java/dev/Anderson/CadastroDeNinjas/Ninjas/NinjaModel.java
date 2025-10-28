package dev.Anderson.CadastroDeNinjas.Ninjas;


import dev.Anderson.CadastroDeNinjas.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_cadastro")
@NoArgsConstructor //Lombok constroi o construtor vazio automaticamente
@AllArgsConstructor //Lombok constroi o construtores cheios automaticamente
@Data //Lombok constroi os Getters e Setters automaticamente
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "id")
    private Long id;

    @Column (name = "nome")
    private String nome;

    @Column(unique = true)
    private String email;

    @Column (name = "img_url")
    private String imgUrl;

    @Column (name = "rank")
    private String rank;

    @Column (name = "idade")
    private int idade;




    //@ManyToOne um ninja tem uma única missão
    @ManyToOne
    @JoinColumn(name = "missoes_id")//Foreign Key ou chave estrangeira

    private MissoesModel missoes;




}