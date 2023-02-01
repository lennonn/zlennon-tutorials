package com.zl.socket.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class ChatSocketClient implements Runnable {
	Socket socket = null;
	StringBuffer recevieData;

	public ChatSocketClient() {
		try {
			socket = new Socket("127.0.0.1", 8180);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String sendData(InputStreamReader inputStreamReader) {
		BufferedReader clientBufferedReader = new BufferedReader(
				inputStreamReader);
		StringBuffer clientData = new StringBuffer();
		try {
			while (clientBufferedReader.readLine() != null) {
				clientData.append(clientBufferedReader.readLine());
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return clientData.toString();
	}

	@Override
	public void run() {
		try {
			BufferedReader serverBufferedReader = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			// System.out.println("Come from Server:"+
			// serverBufferedReader.readLine());
			recevieData = new StringBuffer(serverBufferedReader.readLine());
			System.out.println("Come from Server:"
					+ serverBufferedReader.readLine());
			while (recevieData.length()!=0) {
				recevieData.append(serverBufferedReader.readLine());
				recevieData = new StringBuffer(serverBufferedReader.readLine());
				System.out.println("Come from Server:"
						+ serverBufferedReader.readLine());
			}
			socket.close();
			serverBufferedReader.close();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (socket != null) {
				try {
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}

	}
}
