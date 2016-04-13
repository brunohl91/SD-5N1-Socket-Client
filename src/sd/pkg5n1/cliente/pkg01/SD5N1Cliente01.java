
package sd.pkg5n1.cliente.pkg01;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author Bruno
 */
public class SD5N1Cliente01 {

    private Socket socket;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /*
         1. Solicitar/criar a conexão
         2. Criar inputs de entrada e saída
         3. Tratar a conversação com o servidor
        */
        try {
            SD5N1Cliente01 cliente = new SD5N1Cliente01();
            System.out.println("1) Criando a conexão...");
            //Socket socket = cliente.criarConexao("localhost", 5555);
            Socket socket = cliente.criarConexao("10.5.10.80", 5555);
            System.out.println("2) Tratar conexão...");
            cliente.trataConexao(socket);
        } catch (Exception e) {
            System.out.println("Ocorreu um erro: " + e.getMessage());
        }
    }
    
    public Socket criarConexao (String host, int porta) throws IOException {
        socket = new Socket(host, porta);
        return socket;
    }
    
    public void trataConexao (Socket socket) throws IOException {
        
        // variáveis de input e output
        DataInputStream input = new DataInputStream(socket.getInputStream());
        DataOutputStream output = new DataOutputStream(socket.getOutputStream());

        String msgEnviar = "";
        while (!msgEnviar.equals("exit")) {
            System.out.println("$");
            Scanner scanner = new Scanner( System.in );
            msgEnviar = scanner.nextLine().toUpperCase();
            output.writeUTF(msgEnviar);
            System.out.println("-->" + input.readUTF());
            
            // padrão de resposta
            // operacaoreply#somparam1#param2#paramN
            // erro#mensagem
            
        }
        
        // envia informação
        String msgCliente = "Oi";
        output.writeUTF(msgCliente);
        
        // recebe informação
        String msgServidor = input.readUTF();
        System.out.println("Mensagem recebida: " + msgServidor);
        
    }
    
}
