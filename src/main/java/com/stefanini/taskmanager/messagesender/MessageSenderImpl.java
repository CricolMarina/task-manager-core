package com.stefanini.taskmanager.messagesender;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class MessageSenderImpl implements MessageSender{
	private static final Logger logger = LogManager.getLogger(MessageSenderImpl.class);
	private List<MessageReceiver> receiverList = new ArrayList<>();
	
	public MessageSenderImpl () {
		this.receiverList.add(new MessageReceiverImpl("cricol.marina@extendaretail.com"));
		this.receiverList.add(new MessageReceiverImpl("cricol.pavel@extendaretail.com"));
		this.receiverList.add(new MessageReceiverImpl("dragan.olesea@extendaretail.com"));
	}
	
	public void sendMessage(Message message) {
		for (MessageReceiver receiver : receiverList) {
			if (message.getAddress().equals(receiver.getAddress())) {
				System.out.println("Message was send to " + message.getAddress());
				receiver.receive(message);
				break;
			}
		}
	}
}