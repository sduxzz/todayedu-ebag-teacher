/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.Network NetworkClient.java
 * 2012 2012-7-13 下午3:03:59
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.Network;

import java.net.InetSocketAddress;

import org.apache.mina.core.future.ConnectFuture;
import org.apache.mina.core.service.IoHandler;
import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.filter.codec.serialization.ObjectSerializationCodecFactory;
import org.apache.mina.transport.socket.nio.NioSocketConnector;
import org.ebag.net.obj.I;

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
		return zConnector.connect(new InetSocketAddress("211.87.227.10",
		        I.tupload.mina_server_port));
	}
	
	public void disconnect() {
	
		zConnector.dispose();
	}
}
