import java.util.Scanner;

//Classe 01
public class PrimeiraClasse{ //Essa classe DEVE ser o nome do arquivo para rodar (pelo menos no GDB)
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Digite um número: ");
        int a = scanner.nextInt();
        
        System.out.printf("Os números ímpares entre 0 e %d são: \n", a);
        
        for(int i=0;i<=a;i++){
            if((i%2)!=0){
                System.out.printf("%d ", i);
            }
        }
        
        scanner.close();
    }
}