/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.Network NetworkClient.java
 * 2012 2012-7-13 下午3:03:59
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.Network;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;

/**
 * @author zhenzxie
 * 
 */
public class NetworkClient {
	
	private NioSocketConnector zConnector;
	
	public NetworkClient() {
	
		zConnector = new NioSocketConnector();
		zConnector.getFilterChain().addLast("codec",
				new ProtocolCodecFilter(new ObjectSerializationCodecFactory()));
	}
	
	public void setHandler(IoHandler handler) {
	
		zConnector.setHandler(handler);
	}
	
	public ConnectFuture connect() {
	
		return zConnector.connect();
	}
	
	public void disconnect() {
	
		zConnector.dispose();
	}
}
