package com.cooksys.ftd.assignments.socket;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.Student;

public class Server extends Utils {

    /**
     * Reads a {@link Student} object from the given file path
     *
     * @param studentFilePath the file path from which to read the student config file
     * @param jaxb the JAXB context to use during unmarshalling
     * @return a {@link Student} object unmarshalled from the given file path
     */
    public static Student loadStudent(String studentFilePath, JAXBContext jaxb) {
    	Student returnMe;
    	try {
			Unmarshaller um = jaxb.createUnmarshaller();
			returnMe = (Student) um.unmarshal(new File(studentFilePath));
			return returnMe;
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("unsuccessful loadStudent in Server class");
        return null; // TODO
    }

    /**
     * The server should load a {@link com.cooksys.ftd.assignments.socket.model.Config} object from the
     * <project-root>/config/config.xml path, using the "port" property of the embedded
     * {@link com.cooksys.ftd.assignments.socket.model.LocalConfig} object to create a server socket that
     * listens for connections on the configured port.
     *
     * Upon receiving a connection, the server should unmarshal a {@link Student} object from a file location
     * specified by the config's "studentFilePath" property. It should then re-marshal the object to xml over the
     * socket's output stream, sending the object to the client.
     *
     * Following this transaction, the server may shut down or listen for more connections.
     */
    public static void main(String[] args) {
        // TODO
    	Config config = Utils.loadConfig("C:/Users/ftd-3/code/combined-assignments/4-socket-io-serialization/config/config.xml", Utils.createJAXBContext());
    	
    	System.out.println("What happened: " + config.getLocal().getPort());
    	
    	//create a server socket that listens for connections on the configured port
    	while(true)
    	{
    		try (ServerSocket server = new ServerSocket(config.getLocal().getPort());){
				Socket client = server.accept();
				System.out.println("Server just accepted a client");
				Unmarshaller um = Utils.createJAXBContext().createUnmarshaller();
				Student student = (Student) um.unmarshal(new File(config.getStudentFilePath()));
				System.out.println("Server! Student done: " + student.getFirstName());
				
				
				//now marshal it back
				 Utils.createJAXBContext().createMarshaller().marshal(student, client.getOutputStream());
				 client.getOutputStream().close();
				System.out.println("I believe I just marshalled it back");
				server.close();
				client.close();
				System.exit(0);//all done!
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JAXBException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		finally
    		{
    		}
    	}
    	
    }
}
