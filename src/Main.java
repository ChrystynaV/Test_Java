import java.io.BufferedReader;
import java.io.FileReader;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.util.Arrays;
import java.util.Scanner;
import java.util.function.DoubleToIntFunction;

public class Main {
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new BufferedReader(new FileReader("C:\\Users\\User\\Desktop\\4 курс\\Теорія прийняття рішень\\lab2.txt")));
        //int r=14;
        // char[] names  = {'M', 'D'}; //new char[];


        double[] m1 = new double[5];
        double[] m2 = new double[5];
        double[] m3 = new double[4];
        double M1, M2, M3, M31, M32;
        //int[][] matrix = new int[r][r];
        double[][] matrix = new double[3][5];
        while (sc.hasNextLine()) {
            for (int i = 0; i < matrix.length; i++) {
                String[] line = sc.nextLine().trim().split(" ");
                for (int j = 0; j < line.length; j++) {
                    matrix[i][j] = Double.parseDouble(line[j]);
                }

            }
        }

        for (int i = 0; i < 5; i++) {
            m1[i] = matrix[0][i];
            m2[i] = matrix[1][i];
            if (i < 4) {
                m3[i] = matrix[2][i];
            }
        }

        System.out.println("Дані з файлу:");
        System.out.println("M1=" + m1[0] + ", D1=" + m1[1] + ", p1=" + m1[2] + ", D2=" + m1[3] + ", p2=" + m1[4]);
        System.out.println("M2=" + m2[0] + ", D1=" + m2[1] + ", p1=" + m2[2] + ", D2=" + m2[3] + ", p2=" + m2[4]);
        System.out.println("M3: p3=" + m3[0] + ", p4=" + m3[1] + ", p1=" + m3[2] + ", p2=" + m3[3]);
        System.out.println();

        System.out.println("Компанія розглядає питання про будівництво заводу. Можливі три варіанти:"+
"\nА) M1 -  Побудувати великий завод вартістю "+Integer.toString((int) m1[0])+" тис. доларів. При цьому варіанті можливі великий попит (річний дохід в розмірі "+Integer.toString((int) m1[1])+
"тис. доларів протягом наступних 5 років) \nз ймовірністю "+m1[2]+" і низький попит (щорічні збитки "+Integer.toString((int) m1[3])+" тис. доларів) з ймовірністю "+m1[4]+"."+
"\nБ) M2 - Побудувати маленький завод вартістю "+Integer.toString((int) m2[0])+" тис. доларів. При цьому варіанті можливі великий попит (річний дохід в розмірі "+Integer.toString((int) m2[1])+
"тис. доларів протягом наступних 5 років) \nз ймовірністю "+m2[2]+" і низький попит (щорічні збитки "+Integer.toString((int) m2[3])+" тис. доларів) з ймовірністю "+m2[4]+"."+
 "\nВ) M3 - Відкласти будівництво заводу на 1 рік для збору додаткової інформації, яка може бути позитивною або негативною з ймовірністю "+
m3[0] + " і " +m3[1] + " відповідно.\nУ разі позитивної інформації (М3.1) можна побудувати заводи з зазначеним вище розцінками, що відповідають вузлам M3.11 та M3.12, а ймовірності великого і "+
"низького попиту \nзмінюються на "+m3[2]+" і "+m3[3]+" відповідно. Доходи на наступні 4 роки залишаються колишніми. \nУ разі негативної інформації (М3.2)"+
" компанія заводи будувати не буде.\n\n");

                M1 = Pm(m1[2], m1[4], m1[1], m1[3]);
        M2 = Pm(m2[2], m2[4], m2[1], m2[3]);
        M31 = Pm(m3[2], m3[3], m1[1], m1[3]);
        M32 = Pm(m3[2], m3[3], m2[1], m2[3]);

        System.out.println("Дохід при події M1 = " + m1[2] + "*" + Integer.toString((int) m1[1]) + "-" + m1[4] + "*" + Integer.toString((int) Math.abs(m1[3])) + " = " + M1 + "x5 з втратами -" + Integer.toString((int) m1[0]) + ": " + (M1 * 5 - m1[0])+ " тис. доларів.");
        System.out.println("Дохід при події M2 = " + m2[2] + "*" + Integer.toString((int) m2[1]) + "-" + m2[4] + "*" + Integer.toString((int) Math.abs(m2[3])) + " = " + M2 + "x5 з втратами -" + Integer.toString((int) m2[0]) + ": " + (M2 * 5 - m2[0])+ " тис. доларів.");

        System.out.println("Дохід при події M3.11 = " + m3[2] + "*" + Integer.toString((int) m1[1]) + "-" + m3[3] + "*" + Integer.toString((int) Math.abs(m1[3])) + " = " + M31 + "x4 з втратами -" + Integer.toString((int) m1[0]) + ": " + (M31 * 4 - m1[0])+ " тис. доларів.");
        System.out.println("Дохід при події M3.12 = " + m3[2] + "*" + Integer.toString((int) m2[1]) + "-" + m3[3] + "*" + Integer.toString((int) Math.abs(m2[3])) + " = " + M32 + "x4 з втратами -" + Integer.toString((int) m2[0]) + ": " + (M32 * 4 - m2[0])+ "тис. доларів.");
        System.out.println("Дохід при події M3.2 = 0*" + m3[1] + " = 0 тис. доларів.");

        System.out.println();

        M1 = M1 * 5 - m1[0];
        M2 = M2 * 5 - m2[0];
        M31 = M31 * 4 - m1[0];
        M32 = M32 * 4 - m2[0];
       // M3 = 0*m3[1];
       // if (M31>0 && M32>0){
        if (M31 > M32) {
            M3 = M31 * m3[0];
            System.out.println("Дохід при події M3.11 вигідніший, ніж при М3.12,  отже, М3.1 = " + M31); // + Integer.toString((int) M31) + "*" + m3[0] + " = " + (M31 * m3[0]));
        } else {
            M3 = M32 * m3[0];
            System.out.println("Дохід при події M3.12 вигідніший, ніж при М3.11, отже, М3.1 = " +M32); // + Integer.toString((int) M32) + "*" + m3[0] + " = " + (M32 * m3[0]));
        }
        System.out.println();

       M3 = findM3(M31, M32, M3, m3[0]);
        /*if (M3<0) {
            M3 = 0;
            System.out.println("Дохід при події M3.2 вигідніший, ніж при М3.1, отже, М3 = 0");
        }
        else if (M31 > M32) {
          //  M3 = M31 * m3[0];
            System.out.println("Дохід при події M3.1 вигідніший, ніж при М3.2, отже, M3  = " + Integer.toString((int) M31) + "*" + m3[0] + " = " + (M31 * m3[0]));
        } else {
          //  M3 = M32 * m3[0];
            System.out.println("Дохід при події M3.1 вигідніший, ніж при М3.2, отже, M3 = " + Integer.toString((int) M32) + "*" + m3[0] + " = " + (M32 * m3[0]));
        }
        */

        System.out.println();
        System.out.println("Дохід при події M1 = " + M1 + " тис. доларів.");
        System.out.println("Дохід при події M2 = " + M2+ " тис. доларів.");
        System.out.println("Дохід при події M3 = " + M3+ " тис. доларів.");

        System.out.println();
        if (M1 > M2) {
            if (M1 > M3) {
                System.out.println("Найкраще рішення - А) Побудувати великий завод - з доходом " + M1+ " тис. доларів.");
            } else
                System.out.println("Найкраще рішення - В) Відкласти будівництво заводу на 1 рік з подальшим доходом " + M3+ " тис. доларів.");
        } else if (M2 > M3) {
            System.out.println("Найкраще рішення - Б) Побудувати маленький завод - з доходом " + M2 + " тис. доларів.");
        } else
            System.out.println("Найкраще рішення - В) Відкласти будівництво заводу на 1 рік з подальшим доходом " + M3 + " тис. доларів.");


        Result result = JUnitCore.runClasses(MainTest.class);
    }

public static double Pm(double p1, double p2, double d1, double d2){
        double Pm;
        Pm=p1*d1-Math.abs(p2*d2);
    //System.out.println("Ймовірність події "+ pp + " = " + Pm);
        return Pm;
}

public static double findM3 (double M31, double M32, double M3, double p){
    if (M3<0) {
        M3 = 0;
        System.out.println("Дохід при події M3.2 вигідніший, ніж при М3.1, отже, М3 = 0");
    }
    else if (M31 > M32) {
        M3 = M31 * p;
        System.out.println("Дохід при події M3.1 вигідніший, ніж при М3.2, отже, M3  = " + Integer.toString((int) M31) + "*" + p + " = " + (M31 * p));
    } else {
        M3 = M32 * p;
        System.out.println("Дохід при події M3.1 вигідніший, ніж при М3.2, отже, M3 = " + Integer.toString((int) M32) + "*" + p + " = " + (M32 * p));
    }
    return M3;
}

}