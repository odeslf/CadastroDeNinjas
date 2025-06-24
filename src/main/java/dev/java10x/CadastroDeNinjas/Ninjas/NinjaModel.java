package dev.java10x.CadastroDeNinjas.Ninjas;
import dev.java10x.CadastroDeNinjas.Missoes.MissoesModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity //Entity transforma uma classe em uma entidade do DB
@Table(name = "tb_cadastro")
@NoArgsConstructor
@AllArgsConstructor
@Data

public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "nome")
    private String nome;
    @Column(unique = true)//Somente aceitar emails únicos
    private String email;
    @Column(name = "idade")
    private int idade;

    @ManyToOne //@ManyToOne um ninja só poder ter uma missão
    @JoinColumn(name = "missoes_id")// Foreign key
    private MissoesModel missoes;
    @Column(name = "rank")
    private String rank;
}
