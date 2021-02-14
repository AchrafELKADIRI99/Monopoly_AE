package Controllers;


import java.util.ArrayList;


import model.Case;
import views.MainWindow;
import javafx.concurrent.Service;
import javafx.concurrent.Task;


/**
 * Lance la partie
*/

public class Party {

	/**
	 * @see PlateauMonopoly
	 */
	private Boardmonop pm;
	
	/**
	 * @see FenetrePrincipale
	 */
	private MainWindow fp;
	
	private boolean pausePartie = false;
	public final static long VITESSE_PARTIE = 1000;
	public final static boolean PARTIE_AUTO = false;
	
	/* CONSTRUCTEUR PARTIE */
	
	/**
	 * Cr�e une partie en fonction du nombre de joueurs
	 * @param nombreDeJoueurs
	 * @param fp
	 */
	public Party(int nombreDeJoueurs, ArrayList<String> nomsDesJoueurs, MainWindow fp) {
		this.pm = new Boardmonop(nombreDeJoueurs);
		this.fp = fp;

		for(int i=0; i<nombreDeJoueurs; i++) {
			pm.getJoueur(i).setNom(nomsDesJoueurs.get(i));
		}
	}
	/**
	 * D�marre la partie
	 * @see JoueurMonopoly
	 * @see Case
	 */
	public void demarrerLaPartie() {
		
		final Service<Void> partieService = new Service<Void>() {

            @Override
            protected Task<Void> createTask() {
                return new Task<Void>() {

                    @Override
                    protected Void call() throws Exception {
                		
                		Playermonop joueur;
                		int lanc�;
                		Case caze;
                		
                		
                		while(!pm.finPartie() && pm.getNbTours() <= 100) {
                			
                			joueur = pm.getJoueurActif();
                			
                			if(pm.getJoueurActifID() == 0)
                				
                			fp.afficherMessage("C'est au tour de " + joueur.getNom() + " (poss�de " + joueur.getArgent() + "DH)");
                			
                			if(!joueur.getEstBanqueroute()) {
                				Thread.sleep(VITESSE_PARTIE);
                				
                				lanc� = pm.des.lancerDes();
                				
                				if(!joueur.getEstPrison()) {
                					
                    				fp.afficherDes(pm);
                					pm.deplacerJoueur(joueur, lanc�);
                					fp.deplacerPion(joueur);
                					
                					caze = pm.getCase(joueur.getPosition());
                				}
                				else {
                					
                					caze = pm.getCase(joueur.getPosition());
                				}
            					Thread.sleep(VITESSE_PARTIE);

                				pausePartie = true;
            					caze.fenetreAction(fp);
            					
                    			while(pausePartie && !PARTIE_AUTO){ Thread.sleep(200); }
                    			
                				caze.actionCase(joueur, pm, fp);
                    			
                			}
                		
                			
                			Thread.sleep(400);
                			fp.deplacerPion(joueur);
                			fp.refreshLabels(pm);
                			
                			pausePartie = !joueur.getEstBanqueroute();
                			while(pausePartie && !PARTIE_AUTO){ Thread.sleep(200); }
                			
                			fp.effacerDes();
                			pm.setJoueurSuivant();
                			
                		}
                		
                        
                		fp.afficherVainqueur(pm);
                		
                		return null;
                    }
                };
            }
        };
        partieService.start();
	}
	
	/**
	 * Renvoie le plateau du Monopoly
	 * @return pm
	 */
	public Boardmonop getPM() {
		return this.pm;
	}
	
	/**
	 * Permet de reprendre le cours de la partie
	 */
	public void reprendrePartie() {
		this.pausePartie = false;
	}
	public void pausePartie(){
		this.pausePartie = true;
	}
	public boolean getPausePartie(){
		return this.pausePartie;
	}

	/**
	 * Il est l� car il le doit c'est tout
	 */
	@Override
	public String toString() {
		return "Partie [plateauM=" + pm.toString() + "]";
	}
	
}
