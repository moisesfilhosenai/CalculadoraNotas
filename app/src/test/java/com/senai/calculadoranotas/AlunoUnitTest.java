package com.senai.calculadoranotas;

import org.junit.Test;

import static org.junit.Assert.*;


public class AlunoUnitTest {

    @Test
    public void calcular_media_ok() {
        Aluno aluno = new Aluno("Aluno 01", "Android", 4, 5, 3, 2);
        aluno.calcularMedia();

        assertEquals(5.4, aluno.getMedia(), 0);
    }

    @Test
    public void calcular_media_nao_ok() {
        Aluno aluno = new Aluno("Aluno 01", "Android", 4, 5, 3, 2);
        aluno.calcularMedia();

        assertNotEquals(0.4, aluno.getMedia(), 0);
    }

    @Test
    public void verificar_aluno_aprovado() {
        Aluno aluno = new Aluno("Aluno 01", "Android", 4, 5, 9, 2);
        aluno.calcularMedia();
        aluno.verificarAlunoAprovado();

        assertTrue(aluno.isAprovado());
    }

    @Test
    public void verificar_aluno_reprovado_faltas() {
        Aluno aluno = new Aluno("Aluno 01", "Android", 4, 5, 9, Utils.LIMITE_FALTAS + 1);
        aluno.calcularMedia();
        aluno.verificarAlunoAprovado();

        assertFalse(aluno.isAprovado());
    }

    @Test
    public void verificar_aluno_reprovado_notas() {
        Aluno aluno = new Aluno("Aluno 01", "Android", 4, 5, 3, Utils.LIMITE_FALTAS);
        aluno.calcularMedia();
        aluno.verificarAlunoAprovado();

        assertFalse(aluno.isAprovado());
    }
}
