package main.java.arq.dto;
import lombok.Builder;
import lombok.Data;

@Data	@Builder
public class Cliente {
	private int id;
	private String nome;
	private int idade;
	private String telefone;
	private double credito;
	private Pais pais;

	public void setNome(String nome) throw Exception {
		if(nome.length() < 5)
			throw new Exception("Este nome é pequeno demais!");
		this.nome = nome;
	}
}
