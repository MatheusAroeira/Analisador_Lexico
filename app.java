import java.io.FileNotFoundException;
import java.util.Arrays;

public class app {

    public static void main(String[] args) throws FileNotFoundException, TokenDesconhecidoException {

        leituraLinha leitor = new leituraLinha();
        Analisador analisador = new Analisador(leitor.getLexemas());
        analisador.analise();

        System.out.println("Lista de Tokens = " + Arrays.toString(analisador.getLista_de_tokens()));
        System.out.println("Tabela de Simbolos = " + Arrays.toString(analisador.getTabela_de_simbolos()));

    }

}
