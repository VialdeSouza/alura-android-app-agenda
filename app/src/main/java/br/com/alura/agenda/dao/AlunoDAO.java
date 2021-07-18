package br.com.alura.agenda.dao;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.agenda.model.Aluno;

public class AlunoDAO {

    private final static List<Aluno> alunos = new ArrayList<>();
    private  static  int contadorDeIds = 1;

    public void salvar(Aluno aluno) {
        aluno.setId(contadorDeIds);
        alunos.add(aluno);
        contadorDeIds++;
    }
    public void edita(Aluno aluno){
        Aluno alunoEncontrado = null;
        Log.i("AlunoEncontrado", String.valueOf(alunos.get(0)));
        for(Aluno a: alunos){
            if (a.getId() == aluno.getId()){
                alunoEncontrado = a;

            }
        }
        if(alunoEncontrado != null){
            int posicaoDoALuno = alunos.indexOf(alunoEncontrado);
            alunos.set(posicaoDoALuno, aluno);
        }
    }
    public List<Aluno> todos() {
        return new ArrayList<>(alunos);
    }
}