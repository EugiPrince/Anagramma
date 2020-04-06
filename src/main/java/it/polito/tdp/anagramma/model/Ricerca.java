package it.polito.tdp.anagramma.model;

import java.util.ArrayList;
import java.util.List;

public class Ricerca {
	
	private List<String> soluzione;

	/**
	 * Genera tutti gli anagrammi della parola specificata in ingresso
	 * @param parola Parola da anagrammare
	 * @return elenco di tutti gli anagrammi della parola data
	 */
	public List<String> anagrammi(String parola) {
		this.soluzione = new ArrayList<String>();
		
		//Caso iniziale, avvio della ricorsione
		parola = parola.toUpperCase();
		List<Character> disponibili = new ArrayList<Character>();
		for(int i=0; i<parola.length(); i++)
			disponibili.add(parola.charAt(i));
		
		cerca("", 0, disponibili);
		
		return this.soluzione;
	}
	
	/**
	 * Procedura ricorsiva per il calcolo dell'anagramma.
	 * 
	 * @param parziale Indica la parte iniziale dell'anagramma costruito fin'ora
	 * @param livello Livello della ricorsione, sempre uguale a parziale.lenght() (potrei non metterlo)
	 * @param disponibili Insieme di lettere non ancora utilizzate
	 */
	private void cerca(String parziale, int livello, List<Character> disponibili) {
		if(disponibili.size()==0) { // quando disponibli e' vuoto oppure la lunghezza della parola e' uguale a livello
			//caso terminale
			this.soluzione.add(parziale); //La sol parziale e' quella finale
		}
		
		//caso normale (il caso iniziale sara' nel chiamante (anagrammi) che prepara la chiamata per il liv 0
		//Devo provare ad aggiungere alla soluzione parziale, tutti i caratteri presenti tra i dispoinibili,
		//cioe' iterare su tutti i caratteri disponibili, e per ciascuno di essi, costruisco un tentativo nuovo
		//che ottengo aggiungendo a parziale un singolo carattere in coda.
		
		for(Character ch : disponibili) {
			String tentativo = parziale + ch; //Costruisco una nuova stringa da usare come tentativo parziale
			//del livello successivo... Siccome String e' immutabile non cambio la string "parziale" attuale,
			//ma ne creo una nuova... questo mi risolve il problema del backtracking, non devo farlo, perche'
			//la soluzione parziale non la modifico!
			
			List<Character> rimanenti = new ArrayList<>(disponibili); // faccio cosi' perche' non posso rimuovere
			rimanenti.remove(ch); //un oggetto da una lista che sto iterando! (Tuttavia rallenta il programma fare cosi')
			cerca(tentativo, livello+1, rimanenti);
		}
	}
}

/* Dato di partenza: parola da angrammare di lunghezza N
 * 
 * Soluzione parziale: una parte dell'anagramma gia' costruito (i primi caratteri).
 * 
 * Livello: numero di lettere di cui e' composta la soluzione parziale. Man mano che vado avanti piu' lettere
 * si aggiungono all'anagramma
 * 
 * Soluzione finale: soluzione di lunghezza N -> capisco di essere nel caso terminale
 * 
 * Caso terminale: salvare la soluzione trovata
 * 
 * Generazione nuove soluzioni: provare ad aggiungere una lettera, scegliendola, tra quelle che non sono
 * ancora sstate utilizzate nella soluzione parziale
 */
