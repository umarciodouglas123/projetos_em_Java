import java.util.Scanner; //Biblioteca que tem o leitor do teclado

//Classe 01
public class PrimeiraClasse{ //Essa classe DEVE ser o nome do arquivo para rodar (pelo menos no GDB)
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in); //Leitor
        
        System.out.print("Digite o primeiro número: ");
        int vasco = scanner.nextInt();
        
        System.out.print("Digite o segundo número: ");
        int botafogo = scanner.nextInt();
        
        int resto = vasco % botafogo; //Operação do resto da divisão
        System.out.print("O resto é: " +resto); //Exibe o resto na tela
        
        scanner.close(); //Fecha o leitor
    }
}