package ejb;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import entities.Account;
import entities.ProductCatalog;
/*
 * Så langt lager den bare brukere og persister de
 */
@Singleton
@Startup
public class LoadData {
	
	@PersistenceContext(unitName = "auctionApplication")
	private EntityManager em;
	
	@PostConstruct
	public void createData() {
		int numberOfAccounts = 10;
		ArrayList<Account> accounts = generateAccounts(numberOfAccounts);
		assert(accounts.size()==numberOfAccounts);
		
		accounts.forEach(s->em.persist(s));
		em.flush();

		
//		Account acc1 = new Account("Philip", "87654321", 5, "philip@hvl.no");
//		
//		em.persist(acc1);
//		
//		em.flush();
//		
		
	}
	
	private ArrayList<Account> generateAccounts(int n) {
		ArrayList<String> maleNames = new ArrayList<>();
		Collections.addAll(maleNames, "Hans","Joakim","Mikal","Phillip","Magnus","Truls","Rolf",
				"Nils","Ola","Jan", "Jon", "Bendik", "Aksel", "Per","Paal","Espen", "Ivar", 
				"Henrik", "Tor", "Odin", "Michael","Carl");
		
		ArrayList<String> femaleNames = new ArrayList<>();
		Collections.addAll(femaleNames, "Johanne", "Ida", "Frida", "Michelle", "Vilde", "Camilla",
				"Sigrid","Ingrid", "Hilde");
		
		ArrayList<String> surNames = new ArrayList<>();
		Collections.addAll(surNames, "Oedegaard", "NedreBoe", "Ovreboe", "Oppedal", "Mongstad", "Nordmann"
				,"Lie","Tolkien", "Goncalves","Dell","Ringnes", "Thon", "Gates");
		maleNames.forEach(s->{
			surNames.add(s+"sen");surNames.add(s+"son");
		});
		
		ArrayList<String> emailSuffix = new ArrayList<>();
		Collections.addAll(emailSuffix, "@gmail.com", "@hotmail.no","@uib.no","@google.com");
		
		ArrayList<Account> accounts = new ArrayList<>();
		Random rand=new Random();
		
		//Just to avoid adding multiple dummy-users with the same name
		ArrayList<String> takenNames = new ArrayList<>();
		
		for(int i=0;i<n;i++) {
			String name = rand.nextBoolean() ?
					maleNames.get(rand.nextInt(maleNames.size())):
					femaleNames.get(rand.nextInt(femaleNames.size()));
			name+=" "+surNames.get(rand.nextInt(surNames.size()));
			
			if(!takenNames.contains(name)) {
				takenNames.add(name);
				
				String phoneNumber="";
				for(int j=0;j<8;j++)
					phoneNumber+=rand.nextInt(10);
				
				//rating is random, change?
				float rating =rand.nextFloat()*5;
				
				String email = name.toLowerCase().replaceAll(" ", "")
						+ emailSuffix.get(rand.nextInt(emailSuffix.size()));
				
				accounts.add(new Account(name,phoneNumber,rating,email));
				
			}
			else
				i--; //make new random user
		}
		return accounts;
	}
	
}
