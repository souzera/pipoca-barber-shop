package com.br.pipoca.model;

import java.util.Date;

public class GradeHorario {

    /**
        hora   index
        8h00    0
        8h30    1
        9h00    2
        9h30    3
        10h00   4
        10h30   5
        11h00   6
        11h30   7
        14h00   8
        14h30   9
        15h00   10
        15h30   11
        16h00   12
        16h30   13
        17h00   14
        17h30   15
        18h00   16
        18h30   17
     */

    private static int[][] matrix = {{8,0},{8,30},{9,0},{9,30},{10,0},{10,30},{11,0},{11,30},//manhã
            {14,0},{14,30},{15,0},{15,30},{16,0},{16,30},{17,0},{17,30},{18,0},{18,30}};//tarde


    public static Date pegarHorario(Date dia, int hora){
        Date horario = new Date();
        horario.setDate(dia.getDate());
        horario.setHours(matrix[hora][0]);
        horario.setMinutes(matrix[hora][1]);
        horario.setSeconds(0);
        return horario;
    }

    public static int[][] getMatrix() {
        return matrix;
    }
}
