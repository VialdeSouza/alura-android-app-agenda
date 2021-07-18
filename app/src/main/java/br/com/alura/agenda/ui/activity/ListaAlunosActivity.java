package br.com.alura.agenda.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import br.com.alura.agenda.R;
import br.com.alura.agenda.dao.AlunoDAO;
import br.com.alura.agenda.model.Aluno;

import static br.com.alura.agenda.ui.activity.Constantes.CHAVE_ALUNO;

public class ListaAlunosActivity extends AppCompatActivity {
    public static final String TITULO_APPBAR = "Lista de Alunas";
    private AlunoDAO dao = new AlunoDAO();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(TITULO_APPBAR);
        setContentView(R.layout.activity_lista_alunos);
        configuraFABAddAluno();
        dao.salvar(new Aluno("Tamyres", "vial@gmail.com", "66903838"));

    }

    private void configuraFABAddAluno() {
        FloatingActionButton btnAddAluno = findViewById(R.id.activity_lista_alunos_fab_novo_aluno);
        btnAddAluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abreFormularioModoCadastroAluno();
            }
        });
    }

    private void abreFormularioModoCadastroAluno() {
        startActivity(new Intent(this, FormularioAlunoActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        configuraLista();
    }

    private void configuraLista() {
        List<Aluno> alunos = dao.todos();
        ListView listaDeAlunos = findViewById(R.id.activity_lista_de_alunos_listview);
        configuraAdapter(alunos, listaDeAlunos);
        configuraListenerDeCliquePorItem(alunos, listaDeAlunos);
    }

    private void configuraListenerDeCliquePorItem(List<Aluno> alunos, ListView listaDeAlunos) {
        listaDeAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
                abreFormularioNoModoEditaAluno(alunoEscolhido);
            }
        });
    }

    private void abreFormularioNoModoEditaAluno(Aluno alunoEscolhido) {
        Intent vaiParaFormularioAcitivity = new Intent(ListaAlunosActivity.this, FormularioAlunoActivity.class);
        vaiParaFormularioAcitivity.putExtra(CHAVE_ALUNO, alunoEscolhido);
        startActivity(vaiParaFormularioAcitivity);
    }

    private void configuraAdapter(List<Aluno> alunos, ListView listaDeAlunos) {
        listaDeAlunos.setAdapter(new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1,
                alunos));
    }
}
