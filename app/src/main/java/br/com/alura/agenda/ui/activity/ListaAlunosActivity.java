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
    private ArrayAdapter<Aluno> adapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle(TITULO_APPBAR);
        setContentView(R.layout.activity_lista_alunos);
        configuraFABAddAluno();
        configuraLista();
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
        atualizaAlunos();
    }

    private void atualizaAlunos() {
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    private void configuraLista() {
        ListView listaDeAlunos = findViewById(R.id.activity_lista_de_alunos_listview);
        configuraAdapter(listaDeAlunos);
        configuraListenerDeCliquePorItem( listaDeAlunos);
        configuraListenerDeLongoClickPorItem(listaDeAlunos);
    }

    private void configuraListenerDeLongoClickPorItem(ListView listaDeAlunos) {
        listaDeAlunos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int posicao, long id) {
                Aluno alunoEscolhido = (Aluno) adapterView.getItemAtPosition(posicao);
                remove(alunoEscolhido);
                return true;
            }
        });
    }

    private void remove(Aluno aluno) {
        dao.remove(aluno);
        adapter.remove(aluno);
    }

    private void configuraListenerDeCliquePorItem(ListView listaDeAlunos) {
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

    private void configuraAdapter(ListView listaDeAlunos) {
        adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1);
        listaDeAlunos.setAdapter(adapter);
    }
}
