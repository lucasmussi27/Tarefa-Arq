package main.java.arq.dto;
import lombok.Data;
import lombok.Builder;

@Data @Builder
public class Pais {
    private int id;
    private String nome;
    private String sigla;
    private int codigo;
}
