import java.util.Scanner;

//Classe 01
public class PrimeiraClasse{ //Essa classe DEVE ser o nome do arquivo para rodar (pelo menos no GDB)
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Digite um número: ");
        int a = scanner.nextInt();
        
        System.out.print("Digite mais um número: ");
        int b = scanner.nextInt();
        
        System.out.printf("Os números digitados foram %d e %d. \n", a, b);
        
        if(a>b){
            System.out.printf("%d é maior que %d!", a, b);
        }
        else if(a<b){
            System.out.printf("%d é menor que %d!", a, b);
        }
        else{
            System.out.printf("Os números digitados são iguais!");
        }
    }
}