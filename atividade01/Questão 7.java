import java.util.Scanner;

//Classe 01
public class PrimeiraClasse{ //Essa classe DEVE ser o nome do arquivo para rodar (pelo menos no GDB)
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Digite um número: ");
        Double a = scanner.nextDouble();
        
        System.out.print("Digite mais um número: ");
        Double b = scanner.nextDouble();
        
        double soma = a + b;
        
        System.out.printf("%f + %f ≅ %.2f", a, b, soma);
        
        scanner.close();
    }
}