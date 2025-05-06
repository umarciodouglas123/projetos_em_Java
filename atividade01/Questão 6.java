import java.util.Scanner;

//Classe 01
public class PrimeiraClasse{ //Essa classe DEVE ser o nome do arquivo para rodar (pelo menos no GDB)
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Digite seu primeiro nome: ");
        String a = scanner.nextLine();
        
        System.out.print("Digite seu sobrenome: ");
        String b = scanner.nextLine();
        
        System.out.printf("Seu nome é: %s %s\n", a, b);
        
        scanner.close();
    }
}