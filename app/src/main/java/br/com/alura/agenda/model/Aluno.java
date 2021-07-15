package br.com.alura.agenda.model;

public class Aluno {
    private final String nome;
    private final String email;
    private final String telefone;

    public Aluno(String nome, String email, String telefone) {
        this.nome = nome;
        this.email = email;
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return nome;
    }
}
