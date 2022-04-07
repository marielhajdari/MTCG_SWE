package server;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

import static org.junit.jupiter.api.Assertions.*;

class RequestContextTest {
    @Test
    public void testHTTPVerb() throws IOException {
        //Arrange
        RequestContext handler=new RequestContext();
        //Act
        handler.setHTTPHeader("GET /messages HTTP/1.1\n" +
                "Host: localhost:1111\n" +
                "User-Agent: curl/7.71.1\n" +
                "Accept: */*\n" +
                "Content-Type: application/json\n" +
                "Content-Length: 59\n");
        String actual=handler.readHTTPVerb();
        //Assert
        Assertions.assertEquals("GET", actual);
    }
    @Test
    public void testRequest() throws IOException {
        //Arrange
        RequestContext handler=new RequestContext();
        //Act
        handler.setHTTPHeader("GET /messages HTTP/1.1\n" +
                "Host: localhost:1111\n" +
                "User-Agent: curl/7.71.1\n" +
                "Accept: */*\n" +
                "Content-Type: application/json\n" +
                "Content-Length: 59\n");
        String actual=handler.readRequest();
        //Assert
        Assertions.assertEquals("messages", actual);
    }

    @Test
    public void testReadHeader() throws IOException{
        //Arrange
        ServerSocket test=new ServerSocket(999);
        RequestContext handler=new RequestContext();
        Runtime.getRuntime().exec("curl -X POST http://localhost:999/sessions " +
                "--header \"Content-Type: application/json\" " +
                "-d \"{\\\"Username\\\":\\\"kienboec\\\", \\\"Password\\\":\\\"different\\\"}\"");
        Socket clientSocket=test.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("Accept");
        //Act
        String header=handler.readHeader(in);
        test.close();
        clientSocket.close();
        //Assert
        Assertions.assertEquals("POST /sessions HTTP/1.1\n" +
                "Host: localhost:999\n" +
                "User-Agent: curl/7.64.1\n" +
                "Accept: */*\n" +
                "Content-Length: 28\n" +
                "Content-Type: application/x-www-form-urlencoded\n",header);
    }

    @Test
    public void testReadPayload() throws IOException{
        //Arrange
        ServerSocket test=new ServerSocket(999);
        RequestContext handler=new RequestContext();
        Runtime.getRuntime().exec("curl -X POST http://localhost:999/sessions " +
                "--header \"Content-Type: application/json\" " +
                "-d \"{\\\"Username\\\":\\\"kienboec\\\", \\\"Password\\\":\\\"daniel\\\"}\"");
        Socket clientSocket=test.accept();
        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        System.out.println("Accept");
        //Act
        handler.readHeader(in);
        String payload=handler.readPayload(in);
        test.close();
        clientSocket.close();
        //Assert
        Assertions.assertEquals("{\"Username\":\"kienboec\", \"Password\":\"different\"}",payload);
    }

}