
package DefArticles;

/**
 *
 * @author clara
 */
public class Article {
    
    private String nom;
    private int codi;
    private boolean esborrat;

    public Article() {
        esborrat = false;
    }    
    

    public Article(String nom, int codi) {
        this.nom = nom;
        this.codi = codi;
        this.esborrat = false;
    }
    
    

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        this.codi = codi;
    }

    public boolean isEsborrat() {
        return esborrat;
    }

    public void setEsborrat(boolean esborrat) {
        this.esborrat = esborrat;
    }

    @Override
    public String toString() {
        return "Articles{" + "nom=" + nom + ", codi=" + codi + ", esborrat=" + esborrat + '}';
    }   
    
    
}


