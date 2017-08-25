package com.cooksys.ftd.assignments.socket;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.cooksys.ftd.assignments.socket.model.Config;
import com.cooksys.ftd.assignments.socket.model.LocalConfig;
import com.cooksys.ftd.assignments.socket.model.RemoteConfig;
import com.cooksys.ftd.assignments.socket.model.Student;

/**
 * Shared static methods to be used by both the {@link Client} and {@link Server} classes.
 */
public class Utils {
    /**
     * @return a {@link JAXBContext} initialized with the classes in the
     * com.cooksys.socket.assignment.model package
     */
    public static JAXBContext createJAXBContext() {
    	try 
    	{
			JAXBContext returnMe = JAXBContext.newInstance(Config.class, LocalConfig.class, RemoteConfig.class, Student.class);
			return returnMe;
		} 
    	catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("Unsuccessful createJAXBContext");
        return null; // TODO
    }

    /**
     * Reads a {@link Config} object from the given file path.
     *
     * @param configFilePath the file path to the config.xml file
     * @param jaxb the JAXBContext to use
     * @return a {@link Config} object that was read from the config.xml file
     */
    public static Config loadConfig(String configFilePath, JAXBContext jaxb) {
    	Config returnMe;
    	
    	try {
			Unmarshaller um = jaxb.createUnmarshaller();
			returnMe = (Config) um.unmarshal(new File(configFilePath));
			return returnMe;
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	System.out.println("Unsuccessful loadConfig in Utils");
        return null; // TODO
    }
}
