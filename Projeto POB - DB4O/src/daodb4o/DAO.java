/**IFPB - Curso SI - Disciplina de POB
 * @author Prof Fausto Ayres
 */

package daodb4o;

import java.lang.reflect.ParameterizedType;
import java.util.List;

import com.db4o.Db4oEmbedded;
import com.db4o.ObjectContainer;
import com.db4o.config.EmbeddedConfiguration;
import com.db4o.cs.Db4oClientServer;
import com.db4o.cs.config.ClientConfiguration;

import modelo.*;

public  class DAO<T> implements DAOInterface<T> {
	protected static ObjectContainer manager;
	public static void abrir(){	
		if(manager==null){	
			Backup.criar("banco.db4o");
			abrirBancoLocal();
			//abrirBancoRemoto();
			IDAutoIncrementoControle.registrar(manager);
		}
	}
	public static void abrirBancoLocal(){
		EmbeddedConfiguration config =  Db4oEmbedded.newConfiguration(); 
		config.common().messageLevel(0);  // 0,1,2,3...
		config.common().objectClass(Administrador.class).cascadeOnDelete(true);;
		config.common().objectClass(Administrador.class).cascadeOnUpdate(true);;
		config.common().objectClass(Administrador.class).cascadeOnActivate(true);  //sem cascata
		config.common().objectClass(Comentario.class).cascadeOnDelete(true);;
		config.common().objectClass(Comentario.class).cascadeOnUpdate(true);;
		config.common().objectClass(Comentario.class).cascadeOnActivate(true);  //sem cascata
		config.common().objectClass(Postagem.class).cascadeOnDelete(true);;
		config.common().objectClass(Postagem.class).cascadeOnUpdate(true);;
		config.common().objectClass(Postagem.class).cascadeOnActivate(true);  //sem cascata
		config.common().objectClass(Usuario.class).cascadeOnDelete(true);;
		config.common().objectClass(Usuario.class).cascadeOnUpdate(true);;
		config.common().objectClass(Usuario.class).cascadeOnActivate(true);  //sem cascata
		
		manager = 	Db4oEmbedded.openFile(config, "banco.db4o");
	}
	public static void abrirBancoRemoto(){
		ClientConfiguration config = Db4oClientServer.newClientConfiguration( ) ;
		config.common().messageLevel(0);   //0,1,2,3,4
		config.common().objectClass(Administrador.class).cascadeOnDelete(true);;
		config.common().objectClass(Administrador.class).cascadeOnUpdate(true);;
		config.common().objectClass(Administrador.class).cascadeOnActivate(true);  //sem cascata
		config.common().objectClass(Comentario.class).cascadeOnDelete(true);;
		config.common().objectClass(Comentario.class).cascadeOnUpdate(true);;
		config.common().objectClass(Comentario.class).cascadeOnActivate(true);  //sem cascata
		config.common().objectClass(Postagem.class).cascadeOnDelete(true);;
		config.common().objectClass(Postagem.class).cascadeOnUpdate(true);;
		config.common().objectClass(Postagem.class).cascadeOnActivate(true);  //sem cascata
		config.common().objectClass(Usuario.class).cascadeOnDelete(true);;
		config.common().objectClass(Usuario.class).cascadeOnUpdate(true);;
		config.common().objectClass(Usuario.class).cascadeOnActivate(true);  //sem cascata
		
		manager = Db4oClientServer.openClient(config,"10.0.4.179",34000,"usuario1","senha1");	
		//manager = Db4oClientServer.openClient(config,"localhost",34000,"usuario1","senha1");	
	}
	
	public static void fechar(){
		if(manager!=null) {
			manager.close();
			manager=null;
		}
	}
	public void persistir(T obj){
		manager.store( obj );
	}
	public T atualizar(T obj){
		manager.store(obj);
		return obj;
	}
	public void apagar(T obj) {
		manager.delete(obj);
	}
	public void reler(T obj){
		manager.ext().refresh(obj, Integer.MAX_VALUE);
	}

	//--------transa��o---------------
	public static void iniciar(){	}		// tem que ser vazio
	public static void efetivar(){
		manager.commit();
	}
	public static void cancelar(){
		manager.rollback();
	}
	@SuppressWarnings("unchecked")
	public List<T> listar(){
		Class<T> type = (Class<T>) ((ParameterizedType) this.getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
		return (List<T>) manager.query(type);
	}

	public static void flush(){	//commit intermediario
		efetivar();
	}

}

