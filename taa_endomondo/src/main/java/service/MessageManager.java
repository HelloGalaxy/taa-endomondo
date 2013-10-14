package service;

import java.util.Collection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.database.Message;
import datadao.MessageDao;

/*
 * getMessageById
 * getAllMessages
 * getMessageByUser -> from and to
 * getMessageByContent
 * 
 */
@Path("/message")
public class MessageManager extends BasicService {

	@GET
	@Path("/getMessageById/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Message getMessageById(@PathParam("id") int id) {

		beginPersitence();
		MessageDao dataManager = new MessageDao(em);
		Message model = dataManager.getMessageById(id);
		endPersitence();

		return model;

	}

	@GET
	@Path("/getAllMessages")
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<Message> getMessages(@PathParam("id") int id) {

		beginPersitence();
		MessageDao dataManager = new MessageDao(em);
		Collection<Message> models = dataManager.getAllMessages();
		endPersitence();

		return models;
	}

	@GET
	@Path("/getMessagesByUserId/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Collection<Message> getMessagesByUserId(@PathParam("id") int id) {

		beginPersitence();
		MessageDao dataManager = new MessageDao(em);
		Collection<Message> models = dataManager.getMessagesByUserId(id);
		endPersitence();

		return models;

	}
}
