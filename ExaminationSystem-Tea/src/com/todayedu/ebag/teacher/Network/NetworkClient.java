/*
 * ExaminationSystem-Tea com.todayedu.ebag.teacher.Network NetworkClient.java
 * 2012 2012-7-13 下午3:03:59
 * @author zhenzxie
 */
package com.todayedu.ebag.teacher.Network;

import java.net.InetSocketAddress;

import org.apache.mina.core.filterchain.DefaultIoFilterChainBuilder;
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
	private BaseNetworkHandler zHandler;
	
	public NetworkClient() {
	
		zConnector = new NioSocketConnector();
		// zConnector.setConnectTimeoutMillis(2000);
		DefaultIoFilterChainBuilder chain = zConnector.getFilterChain();
		ObjectSerializationCodecFactory osf = new ObjectSerializationCodecFactory();
		osf.setDecoderMaxObjectSize(osf.getDecoderMaxObjectSize() * 10);
		ProtocolCodecFilter filter = new ProtocolCodecFilter(osf);
		chain.addLast("objectFilter", filter);
	}
	
	public void setHandler(IoHandler handler) {
	
		zHandler = (BaseNetworkHandler) handler;
		zConnector.setHandler(handler);
	}
	
	public void connect() {
	
		new Thread() {
			
			@Override
			public void run() {
			
				zConnector.connect(new InetSocketAddress("211.87.227.10",
				        I.tupload.mina_server_port));
				
			}
		}.start();
	}
	
	public void disconnect() {
	
		if (zConnector != null && !zConnector.isDisposed()) {
			zConnector.dispose();
			zHandler.dismiss();
		}
	}
}
