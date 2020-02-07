package com.example.testematematia.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.testematematia.R;
import com.example.testematematia.database.TreinamentoDatabase;
import com.example.testematematia.database.dao.DaoTreinandoRoom;
import com.example.testematematia.model.Treinando;

import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    public int numeroAcerto = 0;
    public int numeroErro = 0;
    public TextView pontuacao;
    public TextView primeiro;
    public TextView operador;
    public TextView segundo;
    public Button botaoum;
    public Button botaodois;
    public Button botaotres;
    public Button botaoquatro;
    public TextView acertos;
    public TextView erros;
    public Random random = new Random();
    public int aleatorio1 = 0;
    public int aleatorio2 = 0;
    public int aleatorio3 = 0;
    public int aleatorio4 = 0;
    public int posicaoCerta = 0;
    public int resultadoCerto = 0;
    public int valorPrimeiro = 0;
    public int valorSegundo = 0;
    public DaoTreinandoRoom daoTreinandoRoom;
    public Treinando treinando;
    public int ttlMultiplicacao = 0;
    public boolean multiplicacao = true;
    public boolean divisao = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setTitle("Treino Matematica Ana Luíza");

        recuperaElementosTela();

        daoTreinandoRoom = TreinamentoDatabase.getInstance(this).getDaoTreinandoRoom();

        configuraInicioJogo();

        gerarNovoCalculo();

        configuraTelaBotoes();

        configuraBotaoCerto();


        final int posicaoBotao = 0;

        botaoum.setOnClickListener(configuraClickBotao(posicaoBotao));
        botaodois.setOnClickListener(configuraClickBotao(1));
        botaotres.setOnClickListener(configuraClickBotao(2));
        botaoquatro.setOnClickListener(configuraClickBotao(3));
    }

    private void configuraInicioJogo() {
        List<Treinando> todos = daoTreinandoRoom.todos();
        if (todos.size() == 0) {
            treinando = new Treinando(1, "Ana Luiza", 0, 0);
            daoTreinandoRoom.salva(treinando);
            ttlMultiplicacao = 0;
        } else {
            treinando = daoTreinandoRoom.consultaPontual(1);
            ttlMultiplicacao = treinando.getTtlMultiplicacao();
            pontuacao.setText("Pontuacáo : " + String.valueOf(ttlMultiplicacao));
        }
    }

    private void recuperaElementosTela() {
        pontuacao = findViewById(R.id.id_pontuacao);
        primeiro = findViewById(R.id.id_primeiro_numero);
        operador = findViewById(R.id.id_operador);
        segundo = findViewById(R.id.id_segundo_numero);

        botaoum = findViewById(R.id.item_resultado_um);
        botaodois = findViewById(R.id.item_resultado_dois);
        botaotres = findViewById(R.id.item_resultado_tres);
        botaoquatro = findViewById(R.id.item_resultado_quatro);

        acertos = findViewById(R.id.id_acertos);
        erros = findViewById(R.id.id_erros);
    }

    private View.OnClickListener configuraClickBotao(final int posicaoBotao) {
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (posicaoCerta == posicaoBotao) {
                    numeroAcerto = numeroAcerto + 1;
                    acertos.setText("Acertos= " + String.valueOf(numeroAcerto));
                    gerarNovoCalculo();
                    configuraTelaBotoes();
                    configuraBotaoCerto();
                    formataBotaoInicial();

                } else {
                    numeroErro = numeroErro + 1;
                    erros.setText("Erros:  = " + String.valueOf(numeroErro));
                    formataBotaoErro(posicaoBotao);

                }
            }
        };
    }

    private void formataBotaoErro(int posicaoBotao) {
        if (posicaoBotao == 0) {
            botaoum.setBackgroundResource(R.drawable.botaoerro);
        }
        if (posicaoBotao == 1) {
            botaodois.setBackgroundResource(R.drawable.botaoerro);
        }
        if (posicaoBotao == 2) {
            botaotres.setBackgroundResource(R.drawable.botaoerro);
        }
        if (posicaoBotao == 3) {
            botaoquatro.setBackgroundResource(R.drawable.botaoerro);
        }
    }

    private void formataBotaoInicial() {
        botaoum.setBackgroundResource(R.drawable.botaosucesso);
        botaodois.setBackgroundResource(R.drawable.botaosucesso);
        botaotres.setBackgroundResource(R.drawable.botaosucesso);
        botaoquatro.setBackgroundResource(R.drawable.botaosucesso);
    }

    private void gerarNovoCalculo() {
        if (numeroAcerto == 5) {
            reiniciaFase();
        }

        if (multiplicacao) {
            geraAleatorioValoresMultiplicacao();
            geraResultadoCerto();
            geraAleatorioMultiplicacao();
        } else {
            geraAleatorioValoresDivisao();
            geraResultadoCerto();
            geraAleatorioDivisao();
        }
    }

    private void reiniciaFase() {
        numeroAcerto = 0;
        numeroErro = 0;
        if (multiplicacao) {
            multiplicacao = false;
            divisao = true;
        } else {
            multiplicacao = true;
            divisao = false;
        }


        ttlMultiplicacao = ttlMultiplicacao + 1;

        treinando = daoTreinandoRoom.consultaPontual(1);
        treinando.setTtlMultiplicacao(ttlMultiplicacao);
        daoTreinandoRoom.edita(treinando);
        pontuacao.setText("Pontuacáo : " + String.valueOf(ttlMultiplicacao));
    }

    private void configuraTelaBotoes() {
        primeiro.setText(String.valueOf(valorPrimeiro));
        segundo.setText(String.valueOf(valorSegundo));
        if (multiplicacao) {
            operador.setText("X");
        } else {
            operador.setText("÷");
        }

        botaoum.setText(String.valueOf(aleatorio1));
        botaodois.setText(String.valueOf(aleatorio2));
        botaotres.setText(String.valueOf(aleatorio3));
        botaoquatro.setText(String.valueOf(aleatorio4));
    }

    private void geraAleatorioMultiplicacao() {
        boolean continua = true;
        while (continua) {
            aleatorio1 = random.nextInt(101);
            if (aleatorio1 != resultadoCerto) {
                continua = false;
            }
        }

        continua = true;
        while (continua) {
            aleatorio2 = random.nextInt(101);
            if (aleatorio2 != resultadoCerto && aleatorio2 != aleatorio1) {
                continua = false;
            }
        }

        continua = true;
        while (continua) {
            aleatorio3 = random.nextInt(101);
            if (aleatorio3 != resultadoCerto && aleatorio3 != aleatorio1 && aleatorio3 != aleatorio2) {
                continua = false;
            }
        }

        continua = true;
        while (continua) {
            aleatorio4 = random.nextInt(101);
            if (aleatorio4 != resultadoCerto && aleatorio4 != aleatorio1 && aleatorio4 != aleatorio2 && aleatorio4 != aleatorio3 ) {
                continua = false;
            }
        }
    }

    private void geraAleatorioDivisao() {

        boolean continua = true;
        while (continua) {
            aleatorio1 = random.nextInt(10);
            if (aleatorio1 != resultadoCerto) {
                continua = false;
            }
        }

        continua = true;
        while (continua) {
            aleatorio2 = random.nextInt(10);
            if (aleatorio2 != resultadoCerto) {
                continua = false;
            }
        }

        continua = true;
        while (continua) {
            aleatorio3 = random.nextInt(10);
            if (aleatorio3 != resultadoCerto) {
                continua = false;
            }
        }

        continua = true;
        while (continua) {
            aleatorio4 = random.nextInt(10);
            if (aleatorio4 != resultadoCerto) {
                continua = false;
            }
        }
    }

    private void geraAleatorioValoresMultiplicacao() {
        valorPrimeiro = random.nextInt(10);
        valorSegundo = random.nextInt(10);
    }

    private void geraAleatorioValoresDivisao() {
        valorPrimeiro = random.nextInt(10);

        boolean continua = true;

        while (continua) {
            valorSegundo = random.nextInt(10);
            if (valorSegundo > 0) {
                continua = false;
            }
        }
    }

    private void configuraBotaoCerto() {
        posicaoCerta = random.nextInt(4);

        if (posicaoCerta == 0) {
            botaoum.setText(String.valueOf(resultadoCerto));
        }
        if (posicaoCerta == 1) {
            botaodois.setText(String.valueOf(resultadoCerto));
        }
        if (posicaoCerta == 2) {
            botaotres.setText(String.valueOf(resultadoCerto));
        }
        if (posicaoCerta == 3) {
            botaoquatro.setText(String.valueOf(resultadoCerto));
        }
    }

    private void geraResultadoCerto() {
        if (multiplicacao) {

            resultadoCerto = valorPrimeiro * valorSegundo;
        } else {
            resultadoCerto = valorPrimeiro;
            valorPrimeiro = valorPrimeiro * valorSegundo;

        }
    }
}
