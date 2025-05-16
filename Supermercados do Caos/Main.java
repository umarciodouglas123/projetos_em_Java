//Autor: Márcio Douglas da Silva dos Santos Vieira

import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

class Produto{ //No GDB, não pode ter duas classes com "public", ou seja, deixa a principal com "public"
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
                FileWriter fw = new FileWriter("estoque.txt", false); // sobrescreve o arquivo
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

//A classe abaixo DEVE ter o nome do arquivo, pelo menos no GDB (não só no GDB, mas pra TODO lugar(máquina virtual java e afins))
public class Main{ //A classe principal é a que tem o nome do arquivo e "public"
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int i, opcao;
        int total = 0;
        List<Produto> produtos = new ArrayList<>();
        float totalCompra = 0;
        
        Produto.carregarArquivo(produtos);
        
        do{
            System.out.println("\n=======SUPERMERCADOS DO CAOS=======");
            System.out.println("===UMA EXPLOSÃO DE PREÇOS ALTOS!===");
            System.out.println("1 - Cadastrar Produtos");
            System.out.println("2 - Consultar Estoque");
            System.out.println("3 - Comprar Produtos");
            System.out.println("4 - Aplicar Desconto");
            System.out.println("0 - Sair");
            System.out.println("===================================");
            System.out.println("SELECIONE UMA OPÇÃO: ");
            opcao = scanner.nextInt();
            
            switch(opcao){
                case 1:
                    Produto novo = new Produto();
                    novo.addProduto(scanner);
                    produtos.add(novo);
                    novo.salvarNoArquivo(); //Chama a função para salvar no arquivo
                    System.out.println("Produto cadastrado e salvo.");
                    break;
                case 2:
                    if(produtos.isEmpty()){
                        System.out.println("Estoque vazio!");
                    }
                    else{
                        System.out.println("\n--- ESTOQUE ---");
                        for(i=0;i<produtos.size();i++){
                            System.out.printf("\nProduto %d:\n", i + 1);
                            produtos.get(i).exibirDados();
                        }
                    }
                    break;
                case 3:
                    if(produtos.isEmpty()){
                        System.out.println("Nenhum produto cadastrado.");
                        break;
                    }
                    int escolha;
                    
                    do{
                        System.out.println("\n--- ESTOQUE ---");
                        for (i=0;i<produtos.size();i++){
                            Produto p = produtos.get(i);
                            System.out.printf("%d - %s (R$%.2f) - Estoque: %d\n", i + 1, p.nome, p.preco, p.qtd);
                        }
                        System.out.print("Digite o número do produto que deseja comprar (0 para finalizar): ");
                        escolha = scanner.nextInt();
                        if((escolha>0) && (escolha<=produtos.size())){
                            Produto p = produtos.get(escolha - 1);
                            totalCompra += p.fazerCompra(scanner, produtos);
                        }
                        else if(escolha!=0){
                            System.out.println("Produto inválido.");
                        }
                    }while(escolha!=0);
                    System.out.printf("Total da compra: R$%.2f\n", totalCompra);
                    break;
                case 4:
                    if(totalCompra<=0){
                        System.out.printf("Carrinho Vazio!");
                    }
                    else{
                        System.out.printf("Total da compra até agora: R$%.2f\n", totalCompra);
                    }
                    System.out.printf("Deseja aplicar desconto? (0 - Não/ 1 - Sim)\n");
                    int desejo = scanner.nextInt();
                    if(desejo==0){
                        System.out.printf("Total: R$%.2f\n", totalCompra);
                    }
                    else if(desejo==1){
                        System.out.printf("Total da compra até agora: R$%.2f\n", totalCompra);
                        System.out.printf("Deseja aplicar quantos %% de desconto?\n");
                        float percentual = scanner.nextFloat();
                        float novoTotal = produtos.get(0).aplicarDesconto(percentual, totalCompra);
                        totalCompra = novoTotal;
                    }
                    else{
                        System.out.println("Opção inválida.");
                    }
                    break;
                default:
                    System.out.printf("Selecione uma opção válida!");
            }
        }while(opcao!=0);
    }
}
