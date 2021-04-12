package exercici5;

import DefArticles.Article;
import DefArticles.Finestra;
import DefArticles.FitxersArticles;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;

/**
 *
 * @author clara
 */
public class Exercici5 {

    public static void main(String[] args) {
            try { //Control de l'aspecte
            javax.swing.UIManager.setLookAndFeel(
                    javax.swing.UIManager.getCrossPlatformLookAndFeelClassName());
        } catch (Exception e) {
            System.out.println("No s'ha establert el look desitjat: " + e);
        }
        new Finestra().setVisible(true);

        FitxersArticles fa = new FitxersArticles();
        Article articleProv;
        int trobat;
        boolean tots = false;
        

        boolean sortir = false;
        int opcio;
        while (!sortir) {
            menu();
            opcio = llegirNum("\n\n\tInserir opció: ");
            switch (opcio) {
                case 1: //Donar d'alta                       
                    articleProv = donarAlta();
                    //comprovar si hi es                    
                    trobat = fa.cercaRegistre(articleProv.getCodi());
                    if (trobat !=-1) {
                        System.out.println("Error, article existent a la posició: "+ trobat);
                        
                    } else {
                        fa.escriureArticle(articleProv);
                    }
                    break;
                case 2: //Modifica                    
                    int codi = llegirNum("Introdueix el codi de l'article a modificar: ");
                    String nouNom = llegirNom("Nou nom: ");
                    fa.modifica(codi, nouNom);                    
                    break;
                case 3: //Baixa
                    codi = llegirNum("Introdueix el codi de l'article a eliminar: ");
                    fa.elimina(codi);
                    break;
                case 4: //mostra fitxer
                    fa.mostraArticles(tots);
                    break;
                case 5: //mostra TOTS
                    tots = true;
                    fa.mostraArticles(tots);
                    tots = false;                    
                    break;
                case 6: //esborra definitivament
                    fa.esborraDefinitiu();
                    break;
                case 0:
                    sortir = true;
                    break;
            }
        }
    }

    private static Article donarAlta() {
        final int longExacta = 20;
        String nom;
        int codi;
        Article art = new Article();
        nom = llegirNom("Nom article: ");
        codi = llegirNum("Codi: ");
        if (nom.length() < longExacta) {
            for (int i = nom.length(); i < longExacta; i++) {
                nom = nom + " ";
            }
        } else {
            nom = nom.substring(0, longExacta - 1);
        }

        art.setCodi(codi);
        art.setNom(nom);
        return art;
    }

    private static void menu() {
        System.out.println("\n\nACTUALITZACIÓ DE FITXERS D'ARTICLES");
        System.out.println("\n\t1. Donar d'alta article");
        System.out.println("\t2. Modificar el nom d'un article");
        System.out.println("\t3. Donar de baixa un article");
        System.out.println("\t4. Mostra el contingut del fitxer");
        System.out.println("\t5. Mostra tots els articles, inclosos els esborrats");
        System.out.println("\t6. Esborra definitivament els articles");
        System.out.println("\t0. Sortir");
    }

    private static int llegirNum(String msg) {
        int x = 0;
        try {
            String s;
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(msg);
            s = in.readLine();
            x = Integer.parseInt(s);
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return x;
    }

    private static String llegirNom(String msg) {
        String s = null;
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            System.out.print(msg);
            s = in.readLine();
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
        }
        return s;
    }
}
