import java.util.Scanner;

/*
Autor: Márcio Douglas da Silva dos Santos Vieira
Futuramente desejo adicionar um arquivo com os produtos e o programa ir lendo e adicionando produtos nesse arquivo
*/

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
    
    public float fazerCompra(Scanner scanner){ //Passei o scanner como parâmetro porque em Produto não tem Scanner
        System.out.printf("Produto: %s | Preço: R$%.2f | Estoque: %d\n", nome, preco, qtd);
        System.out.print("Quantas unidades deseja comprar? ");
        int quantidade = scanner.nextInt();

        if(quantidade<=qtd){
            qtd -= quantidade;
            float subtotal = preco * quantidade;
            System.out.printf("Compra realizada! Subtotal: R$%.2f\n", subtotal);
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
}

//A classe abaixo DEVE ter o nome do arquivo, pelo menos no GDB
public class Main{ //A classe principal é a que tem o nome do arquivo e "public"
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        int i, opcao;
        int total = 0;
        Produto[] produtos = null;
        float totalCompra = 0;
        
        do{
            System.out.println("\n=====SUPERMERCADOS DO CAOS=====");
            System.out.println("1 - Cadastrar Produtos");
            System.out.println("2 - Consultar Estoque");
            System.out.println("3 - Comprar Produtos");
            System.out.println("4 - Aplicar Desconto");
            System.out.println("0 - Sair");
            System.out.println("===============================");
            System.out.println("SELECIONE UMA OPÇÃO: ");
            opcao = scanner.nextInt();
            
            switch(opcao){
                case 1:
                    System.out.print("Quantos produtos deseja adicionar? ");
                    total = scanner.nextInt();
                    produtos = new Produto[total];
                    
                    for(i=0;i<total;i++){
                        System.out.printf("\nProduto %d:\n", i + 1);
                        produtos[i] = new Produto();
                        produtos[i].addProduto(scanner);
                    }
                    break;
                case 2:
                    if(produtos == null){
                        System.out.printf("Estoque vazio!");
                    }
                    else{
                        System.out.println(); //Pula linha
                        System.out.printf("----------ESTOQUE----------");
                        for(i=0; i<total; i++){
                            System.out.printf("\nProduto %d:\n", i + 1);
                            produtos[i].exibirDados();
                        }
                        System.out.printf("---------------------------");
                    }
                    break;
                case 3:
                    if(produtos==null){
                        System.out.println("Nenhum produto cadastrado.");
                        break;
                    }
                    int escolha;
                    
                    do{
                        System.out.println();
                        System.out.printf("----------ESTOQUE----------");
                        System.out.println();
                        for(i=0;i<total;i++){
                            System.out.printf("%d - %s (R$%.2f) - Estoque: %d\n", i + 1, produtos[i].nome, produtos[i].preco, produtos[i].qtd);
                        }
                        System.out.printf("---------------------------");
                        System.out.println();
                        System.out.print("Digite o número do produto que deseja comprar (0 para finalizar): ");
                        escolha = scanner.nextInt();
                        
                        if((escolha>0) && (escolha<=total)){
                            Produto p = produtos[escolha - 1];
                            totalCompra += p.fazerCompra(scanner);
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
                        float novoTotal = produtos[0].aplicarDesconto(percentual, totalCompra);
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