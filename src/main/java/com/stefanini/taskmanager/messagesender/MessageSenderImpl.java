package com.stefanini.taskmanager.messagesender;

import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MessageSenderImpl implements MessageSender{
	private static final Logger logger = LogManager.getLogger(MessageSenderImpl.class);
	private MessageReceiverImpl receiver;
	private ArrayList<MessageReceiverImpl> receiverList = new ArrayList<MessageReceiverImpl>();
	
	public MessageSenderImpl () {
		this.receiverList.add(new MessageReceiverImpl("cricol.marina@extendaretail.com"));
		this.receiverList.add(new MessageReceiverImpl("cricol.pavel@extendaretail.com"));
		this.receiverList.add(new MessageReceiverImpl("dragan.olesea@extendaretail.com"));
	}
	
	public void sendMessage(Message message) {
		for (int i=0; i< receiverList.size(); i++) {
			receiver = receiverList.get(i);
			if (message.getAddress().equals(receiver.getAddress())) {
				logger.info("Message was send to " + message.getAddress());
				receiver.receive(message);
			}
		}
	}
}