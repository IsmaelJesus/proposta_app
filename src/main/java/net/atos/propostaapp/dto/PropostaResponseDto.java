package net.atos.propostaapp.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PropostaResponseDto {
    public Long id;
    public String nome;
    public String sobrenome;
    public String telefone;
    public String cpf;
    public Double renda;
    public  String valorSolicitadoFmt;
    public int prazoPagamento;
    public Boolean aprovado;
    public String observacao;
}
