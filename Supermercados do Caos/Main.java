//Autor: Márcio Douglas da Silva dos Santos Vieira

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

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