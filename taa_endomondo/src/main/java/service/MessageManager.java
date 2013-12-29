package service;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.database.Message;
import model.database.User;
import datadao.MessageDao;
import datadao.UserDao;

/*
 * getMessageById
 * getAllMessages
 * getMessageByUser -> from and to
 * getMessageByContent
 * 
 */
@Path("/msg")
public class MessageManager {

	protected EntityManagerFactory emf;
	protected EntityManager em;

	public MessageManager() {
		emf = Persistence.createEntityManagerFactory("endomondo");
		em = emf.createEntityManager();
	}

	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Message getMessageById(@PathParam("id") int id) {

		// beginPersitence();
		MessageDao dataManager = new MessageDao(em);
		Message model = dataManager.getMessageById(id);
		// endPersitence();
		//model.getFromWho().getFriends().clear();
		//model.getFromWho().getPlans().clear();

		return model;

	}

	@GET
	@Path("/getAllMessages")
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<Message> getMessages(@PathParam("id") int id) {

		// beginPersitence();
		MessageDao dataManager = new MessageDao(em);
		Collection<Message> models = dataManager.getAllMessages();
		// endPersitence();

		return models;
	}

	@GET
	@Path("/getMessagesByUserId/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<Message> getMessagesByUserId(@PathParam("id") int id) {

		// beginPersitence();
		MessageDao dataManager = new MessageDao(em);
		Collection<Message> models = dataManager.getMessagesByUserId(id);
		// endPersitence();

		return models;

	}

	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean createMessage(Message obj) {

		// beginPersitence();
		MessageDao dataManager = new MessageDao(em);
		obj.setId(0);
		boolean result = dataManager.create(obj);
		// endPersitence();
		return result;
	}

	@PUT
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean updateMessage(Message obj) {

		MessageDao dataManager = new MessageDao(em);
		boolean result = dataManager.update(obj);

		return result;
	}

	
	
	@POST
	@Path("send/{sendMail}/{tarMail}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean sendMessage(Message mes, @PathParam("sendMail") String ufrom, @PathParam("tarMail") String uto) {
       /* 
        * - retreive the sender and  the receiver objets
        * - create a message and send it after populating it with these informations
        *  */
		
		MessageDao mssgManager = new MessageDao(em);
		UserDao usrManager = new UserDao(em);
		User sender = usrManager.getUserByEmail(ufrom);
		User receiver = usrManager.getUserByEmail(uto);
		//Message message = mssgManager.create(mes);
		if(sender==null || receiver ==null ){
			System.out.println("Either the sender or receiver were not found"); return false;
		}
		mes.setFromWho(sender);
	    mes.setToWho(receiver);
		
		boolean result = mssgManager.create(mes);
		return result;
	}

	
	
	@DELETE
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public boolean deleteCoord(@PathParam("id") int id) {

		MessageDao dataManager = new MessageDao(em);
		boolean result = dataManager.delete(id);

		return result;
	}
}
