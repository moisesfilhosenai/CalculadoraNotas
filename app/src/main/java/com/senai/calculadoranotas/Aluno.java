package com.senai.calculadoranotas;

import java.util.Locale;

public class Aluno {
    private String nome;
    private String disciplina;
    private int notaPrimeiraAtividade;
    private int notaSegundaAtividade;
    private int notaProva;
    private int qtdeFaltas;
    private double media;
    private boolean aprovado;

    public Aluno(String nome, String disciplina, int notaPrimeiraAtividade, int notaSegundaAtividade, int notaProva, int qtdeFaltas) {
        this.nome = nome;
        this.disciplina = disciplina;
        this.notaPrimeiraAtividade = notaPrimeiraAtividade;
        this.notaSegundaAtividade = notaSegundaAtividade;
        this.notaProva = notaProva;
        this.qtdeFaltas = qtdeFaltas;
        this.aprovado = false;
    }

    /**
     * A média é calculada da seguinte forma:
     * Soma das atividades tem peso 40%
     * Nota da prova tem peso 60%
     */
    public void calcularMedia() {
        double notaAtividades = (this.notaPrimeiraAtividade + this.notaSegundaAtividade) * Utils.PESO_ATIVIDADES;
        this.media = notaAtividades + (this.notaProva * Utils.PESO_PROVA);
    }

    /**
     * Para o aluno ser aprovado ele precisa atender as condições:
     * - Se a qtde de faltas ultrapassar 4 está reprovado
     * - Sua média ser igual ou maior que 6 e não ultrapassar 4 faltas está aprovado
     */
    public String verificarAlunoAprovado() {
        Locale locale = new Locale("pt", "BR");

        String mensagem = "";

        if (this.qtdeFaltas > Utils.LIMITE_FALTAS) {
            this.aprovado = false;
            mensagem = String.format(locale, "Aluno %s reprovado na disciplina de %s por faltas, qtde: %d",
                    this.nome, this.disciplina, this.qtdeFaltas);
        } else if (this.media >= 6) {
            this.aprovado = true;
            mensagem = String.format(locale, "O aluno %s está aprovado na disciplina de %s com média %.2f",
                    this.nome, this.disciplina, this.media);
        } else if (this.media < 6) {
            this.aprovado = false;
            mensagem = String.format(locale, "O aluno %s está reprovado na disciplina de %s com média %.2f",
                    this.nome, this.disciplina, this.media);
        }

        return mensagem;
    }

    public double getMedia() {
        return media;
    }

    public boolean isAprovado() {
        return aprovado;
    }
}
