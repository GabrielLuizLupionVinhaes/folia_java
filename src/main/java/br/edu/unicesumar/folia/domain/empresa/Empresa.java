package br.edu.unicesumar.folia.domain.empresa;

import br.edu.unicesumar.folia.domain.endereco.Endereco;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Table(name = "EMPRESA")
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {
    //Gabriel é um corno

    @Id
    @Setter(AccessLevel.NONE)
    private UUID uuid;
    @Column
    private String nome;
    @Column
    private String cnpj;
    @ManyToOne()
    @JoinColumn(name = "ENDERECO_FK")
    private Endereco endereco;


}
