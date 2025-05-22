//Autor: Márcio Douglas da Silva dos Santos Vieira

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;


public class Produto{ //No GDB, não pode ter duas classes com "public", ou seja, deixa a principal com "public"
    String nome;
    float preco;
    int qtd = 0;
    int product = 0;
    int op;
    float precofinal;
    float percentual;
    
    public void exibirDados(){
        System.out.printf("Nome: %s\n", nome);
        System.out.printf("Preço: R$%.2f\n", preco);
        System.out.printf("Quantidade: %d\n", qtd);
    }
    
    public float aplicarDesconto(float percentual, float valorCompra){ //Recebe um percentual e o valor total da compra como parâmetro porque precisa 
        precofinal = valorCompra - ((percentual / 100) * valorCompra);
        System.out.printf("Preço com desconto de %.1f%%: R$%.2f\n", percentual, precofinal);
        return precofinal;
    }
    
    public float fazerCompra(Scanner scanner, List<Produto> produtos){
        System.out.printf("Produto: %s | Preço: R$%.2f | Estoque: %d\n", nome, preco, qtd);
        System.out.print("Quantas unidades deseja comprar? ");
        int quantidade = scanner.nextInt();
        
        if(quantidade<=qtd){
            qtd -= quantidade;
            float subtotal = preco * quantidade;
            System.out.printf("Compra realizada! Subtotal: R$%.2f\n", subtotal);
            try{
                FileWriter fw = new FileWriter("estoque.txt", false);
                for(Produto p : produtos){
                    fw.write(p.nome + ";" + p.preco + ";" + p.qtd + "\n");
                }
                fw.close();
            }
            catch(IOException e){
                System.out.println("Erro ao salvar o estoque atualizado.");
            }
            return subtotal;
        }
        else{
            System.out.println("Erro: estoque insuficiente.");
            return 0;
        }
    }

    
    public void addProduto(Scanner scanner){
        scanner.nextLine(); //Limpa o buffer
        
        System.out.printf("Digite o nome do produto: ");
        this.nome = scanner.nextLine();
        
        System.out.printf("Digite o preço(Use ponto ao invés de vírgula): R$");
        this.preco = scanner.nextFloat();
        
        System.out.printf("Digite a quantidade recebida: ");
        this.qtd = scanner.nextInt();
        
    }
    
    public void salvarNoArquivo(){ //Função para escrever os produtos adicionados no estoque (arquivo)
        try{
            FileWriter fw = new FileWriter("estoque.txt", true);
            fw.write(nome + ";" + preco + ";" + qtd + "\n");
            fw.close();
        }
        catch(IOException e){
            System.out.println("Erro ao salvar no arquivo.");
        }
    }
    
    public static void carregarArquivo(List<Produto> produtos){ //Função para ler o arquivo (estoque)
        try{
            File file = new File("estoque.txt");
            Scanner leitor = new Scanner(file);
            while(leitor.hasNextLine()){
                String linha = leitor.nextLine();
                String[] partes = linha.split(";");
                Produto p = new Produto();
                p.nome = partes[0];
                p.preco = Float.parseFloat(partes[1]);
                p.qtd = Integer.parseInt(partes[2]);
                produtos.add(p);
            }
            leitor.close();
        }
        catch(FileNotFoundException e){
            System.out.println("Estoque não encontrado! Criando um novo...");
        }
    }
}