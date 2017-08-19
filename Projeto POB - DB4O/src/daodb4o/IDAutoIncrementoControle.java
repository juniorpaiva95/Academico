package daodb4o;

import java.util.List;

import com.db4o.ObjectContainer;
import com.db4o.events.CancellableObjectEventArgs;
import com.db4o.events.CommitEventArgs;
import com.db4o.events.Event4;
import com.db4o.events.EventListener4;
import com.db4o.events.EventRegistry;
import com.db4o.events.EventRegistryFactory;
import com.db4o.query.Query;

public class IDAutoIncrementoControle {
	//Gerencia geração "automatica" de ID para as classes do modelo que 
	//implementam a interface IDInterface
	protected static ObjectContainer manager;
	private static UltimoID ultimoid;		
	private static boolean gerounovoid;			


	//	================= REGISTRAR EVENTOS (Trigger)===========================	
	public static void registrar(ObjectContainer man){
		manager = man;
		EventRegistry eventRegistry = EventRegistryFactory.forObjectContainer(manager);

		//PRE-PERSIST (executado antes de gravar objeto no banco)
		//-------------------------------------------------------
		eventRegistry.creating().addListener(new EventListener4<CancellableObjectEventArgs>() {
			public void onEvent(Event4<CancellableObjectEventArgs> event4, CancellableObjectEventArgs args) {
				Object objeto = args.object();
				if(objeto instanceof IDInterface){ // o objeto a ser gravado implementa IDInterface?
					int id;
					String nomedaclasse = objeto.getClass().getName();
					// localizar UltimoID da classe do objeto 
					Query q = manager.query();
					q.constrain(UltimoID.class);
					q.descend("nomedaclasse").constrain(nomedaclasse);	
					List<UltimoID> resultados = q.execute();
					
					if (resultados.size()>0) {	//encontrou
						ultimoid = resultados.get(0);
						id = ultimoid.getId() + 1;
						ultimoid.setId(id);
					}
					else{	//nao encontrou
						id=1;	
						ultimoid = new UltimoID(nomedaclasse, id);
					}

					// atualizar o objeto com seu novo id
					// -------------------------------------------------------------
					((IDInterface) objeto).setId(id); 
					gerounovoid = true;
				}
			}});

		//POST-COMMIT  (executado apos gravar objeto no banco)
		//----------------------------------------------------
		eventRegistry.committed().addListener(new EventListener4<CommitEventArgs>() {
			public void onEvent(Event4<CommitEventArgs> commitEventArgsEvent4, CommitEventArgs args) {
				//atualizar ultimoid no banco
				if (gerounovoid) {
					gerounovoid = false;
					manager.store(ultimoid);		// atualiza o ultimoid da classe no banco
					manager.ext().purge(ultimoid);  // limpar cache de memoria do manager
				}
			}});     		
	}

	//=========================================================================
	private static class UltimoID{   // guarda ultimo id gerado de uma classe do modelo
	//=========================================================================
		private String nomedaclasse;
		private int id;
		public UltimoID(String nomedaclasse, int ultimoid) {
			this.nomedaclasse = nomedaclasse;
			this.id = ultimoid;
		}		
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getNomedaclasse() {
			return nomedaclasse;
		}
		public void setNomedaclasse(String nomedaclasse) {
			this.nomedaclasse = nomedaclasse;
		}		

	}
}
