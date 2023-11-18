package edu.pw2.superloja.model.endereco;



import edu.pw2.superloja.model.cliente.Cliente;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cep;
    private String logradouro;
    private Integer numero;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    public Endereco(CepData data) {
        this.cep = data.cep();
        this.logradouro = data.logradouro();
        this.numero = data.numero();
        this.complemento = data.complemento();
        this.bairro = data.bairro();
        this.localidade = data.localidade();
        this.uf = data.uf();
    } 
    public Endereco(Endereco endereco) {
        this.id = endereco.id;
        this.cep = endereco.cep;
        this.logradouro = endereco.logradouro;
        this.numero = endereco.numero;
        this.complemento = endereco.complemento;
        this.bairro = endereco.bairro;
        this.localidade = endereco.localidade;
        this.uf = endereco.uf;
    }
    public Endereco(CepData data, Cliente cliente) {
        this.cep = data.cep();
        this.logradouro = data.logradouro();
        this.numero = data.numero();
        this.complemento = data.complemento();
        this.bairro = data.bairro();
        this.localidade = data.localidade();
        this.uf = data.uf();
        this.cliente = cliente;
    } 


    public void updateFromCepData(CepData cepData) {
        this.cep = cepData.cep();
        this.logradouro = cepData.logradouro();
        this.numero = cepData.numero();
        this.complemento = cepData.complemento();
        this.bairro = cepData.bairro();
        this.localidade = cepData.localidade();
        this.uf = cepData.uf();
    }

}
