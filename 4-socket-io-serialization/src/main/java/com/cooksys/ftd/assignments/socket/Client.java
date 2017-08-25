package com.cooksys.ftd.assignments.socket;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.Student;

public class Client {

    /**
     * The client should load a {@link com.cooksys.ftd.assignments.socket.model.Config} object from the
     * <project-root>/config/config.xml path, using the "port" and "host" properties of the embedded
     * {@link com.cooksys.ftd.assignments.socket.model.RemoteConfig} object to create a socket that connects to
     * a {@link Server} listening on the given host and port.
     *
     * The client should expect the server to send a {@link com.cooksys.ftd.assignments.socket.model.Student} object
     * over the socket as xml, and should unmarshal that object before printing its details to the console.
     */
    public static void main(String[] args) {
        // TODO
    	Config config = Utils.loadConfig("C:/Users/ftd-3/code/combined-assignments/4-socket-io-serialization/config/config.xml", Utils.createJAXBContext());
    	
    	try(Socket client = new Socket(config.getRemote().getHost(), config.getRemote().getPort());) {
			
			System.out.println("Client class just made a client socket");
			
			Unmarshaller um = Utils.createJAXBContext().createUnmarshaller();
			
			Student student = (Student)um.unmarshal(client.getInputStream());
			System.out.println("Unmarshalled");
			System.out.println("CLIENT! Student done: " + student);
			client.close();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
}
