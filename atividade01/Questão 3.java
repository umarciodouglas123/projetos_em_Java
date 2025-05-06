//Classe 01
public class PrimeiraClasse{ //Essa classe DEVE ser o nome do arquivo para rodar (pelo menos no GDB)
    public static void main(String[] args){
        //Talvez seria mais inteligente não usar esse monte de variável
        float a = 3;
        float b = 4;
        float c = 5;
        float d = 7;
        float e = 8;
        float f = 9;
        float g = 15;
        float h = 72;
        
        //Primeira operação
        float op0 = c*(-1);
        float op1 = op0 + d * e; //-5 + 7 * 8
        System.out.printf("O resultado da primeira operação é: %.2f\n", op1);
        
        //Segunda operação
        float op2 = h + f % f; //72 + 9 mod 9
        System.out.printf("O resultado da segunda operação é: %.2f\n", op2);
        
        //Terceira operação
        float op3 = c + g / a * a - b % a; //5 + 15 / 3 * 3 – 4 % 3
        System.out.printf("O resultado da terceira operação é: %.2f\n", op3);
    }
}