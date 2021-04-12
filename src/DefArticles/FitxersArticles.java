/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DefArticles;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 *
 * @author clara
 */
public class FitxersArticles {

    public File arxiu;
    public RandomAccessFile f;
    final int longExacta = 20;
    final long MIDAREG = (longExacta * 2) + 4 + 1;

    public FitxersArticles() {
    }

    public void escriureArticle(Article art) {
        try {
            arxiu = new File("directe.dat");
            f = new RandomAccessFile(arxiu, "rw");

            if (f.length() != 0) {
                f.seek(f.length());
            }

            f.writeInt(art.getCodi());
            f.writeChars(art.getNom());
            f.writeBoolean(art.isEsborrat());
            f.close();
            System.out.println("REGISTRE INSERIT");

        } catch (Exception e) {
        }

    }

    public int cercaRegistre(int codi) {
        int trobat = -1;
        //int posicio;
        int codiComparar;

        try {
            arxiu = new File("directe.dat");
            f = new RandomAccessFile(arxiu, "r");
            for (int posicio = 0; (posicio * MIDAREG) < f.length(); posicio++) {
                f.seek(posicio * MIDAREG);
                codiComparar = f.readInt();
                if (codi == codiComparar) {
                    trobat = posicio;
                    break;
                }
            }
            f.close();
        } catch (IOException e) {
        }
        return trobat;
    }

    public void modifica(int codi, String nomNou) {
        try {
            int posicio = cercaRegistre(codi);
            arxiu = new File("directe.dat");
            f = new RandomAccessFile(arxiu, "rw");

            if (nomNou.length() < longExacta) {
                for (int i = nomNou.length(); i < longExacta; i++) {
                    nomNou = nomNou + " ";
                }
            } else {
                nomNou = nomNou.substring(0, longExacta - 1);
            }
            f.seek((posicio * MIDAREG) + 4);
            f.writeChars(nomNou);
            System.out.println("Registre actualitzat");
        } catch (Exception e) {
        }
    }

    public void elimina(int codi) {
        try {
            int posicio = cercaRegistre(codi);
            arxiu = new File("directe.dat");
            f = new RandomAccessFile(arxiu, "rw");
            f.seek(((posicio + 1) * MIDAREG) - 1);
            f.writeBoolean(true);
            System.out.println("Registre eliminat");

        } catch (Exception e) {
        }

    }

    public void esborraDefinitiu() {
        int codi;
        String nom = "";
        boolean esborrat;
        File arxiunou = new File("provisional.dat");

        try {
            arxiu = new File("directe.dat");
            f = new RandomAccessFile(arxiu, "rw");
            long numreg = f.length() / MIDAREG;
            for (int r = 0; r < numreg; r++) {
                codi = f.readInt();
                for (int i = 0; i < longExacta; ++i) {
                    nom += f.readChar();
                }
                esborrat = f.readBoolean();
                if(!esborrat){                    
                    RandomAccessFile fa = new RandomAccessFile(arxiunou, "rw");
                    fa.seek(fa.length());                    
                    fa.write(codi);
                    fa.writeChars(nom);
                    fa.writeBoolean(esborrat);     
                }                
                nom = "";
            }            
            
        } catch (Exception e) {
        }
    }

    public void mostraArticles(boolean tots) {
        int codi;
        String nom = "";
        boolean esborrat;

        // 2 bytes cada caracter string 4 bytes per cada enter més 1 byte per boolean
        try {
            arxiu = new File("directe.dat");
            f = new RandomAccessFile(arxiu, "r");

            long numreg = f.length() / MIDAREG;
            for (int r = 0; r < numreg; r++) {
                codi = f.readInt();
                for (int i = 0; i < longExacta; ++i) {
                    nom += f.readChar();
                }
                esborrat = f.readBoolean();
                if (tots) {
                    System.out.println("Codi: " + codi + " Article: " + nom + " Esborrat?: " + esborrat);                    
                }
                if (!esborrat && !tots) {
                    System.out.println("Codi: " + codi + " Article: " + nom + " Esborrat?: " + esborrat);                    
                }
                nom = "";
            }
            f.close();
        } catch (IOException e) {
        }
    }

}
