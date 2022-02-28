package com.qst.dms.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import com.qst.dms.entity.MatchedLogRec;
import com.qst.dms.entity.MatchedTransport;
import com.qst.dms.service.LogRecService;
import com.qst.dms.service.TransportService;
public class DmsNetServer {

	public DmsNetServer() {
		/*通过不同端口接收不同数据  
		日志:6666   
		物流:6668*/
		new AcceptLogThread(6666).start();
		new AcceptTranThread(6668).start();
		System.out.println("网络服务器启动中……");
		System.out.println("网络服务器启动成功！");
	}
	
	private class AcceptLogThread extends Thread{
		private ServerSocket serverSocket;
		private Socket socket;
		private LogRecService logRecService;
		private ObjectInputStream ois;
		
		public AcceptLogThread(int port) {
		logRecService = new LogRecService();
		try {
			serverSocket = new ServerSocket(port);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
		
		public void run() {
			while(this.isAlive()) {
				try {
					socket = serverSocket.accept();
					if(socket != null) {
						ois = new ObjectInputStream(socket.getInputStream());
						ArrayList<MatchedLogRec> matchedLogs = (ArrayList<MatchedLogRec>) ois.
								readObject();
						logRecService.saveMatchLogToDB(matchedLogs);
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			try {
				ois.close();
				socket.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private class AcceptTranThread extends Thread{
		private ServerSocket serverSocket;
		private Socket socket;
		private TransportService transportService;
		private ObjectInputStream ois;
		
		public AcceptTranThread(int port) {
			transportService = new TransportService();
		try {
			serverSocket = new ServerSocket(port);
		}catch(IOException e) {
			e.printStackTrace();
		}
	}
		
		public void run() {
			while(this.isAlive()) {
				try {
					socket = serverSocket.accept();
					if(socket != null) {
						ois = new ObjectInputStream(socket.getInputStream());
						ArrayList<MatchedTransport> matchedTrans = (ArrayList<MatchedTransport>) ois.
								readObject();
						transportService.saveMatchTransportToDB(matchedTrans);
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			try {
				ois.close();
				socket.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	
    public static void main(String[] args) {
	new DmsNetServer();
    }
}

