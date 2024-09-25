import java.io.FileNotFoundException;
import java.util.Arrays;

public class app {

    public static void main(String[] args) throws FileNotFoundException, TokenDesconhecidoException {

        Analisador analisador = new Analisador();
        leituraLinha leitor = new leituraLinha();
        analisador.analise();
        System.out.println(Arrays.toString(analisador.getLista_de_tokens()));
        System.out.println(Arrays.toString(analisador.getTabela_de_simbolos()));

    }

}
