package nl.han.dea.http;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ConnectionHandler {
    private Socket socket;
    private String requestHeaders;

    public ConnectionHandler(Socket socket) {
        this.socket = socket;
        handle();
    }

    public void handle() {
        try {
            var inputStreamReader = new BufferedReader(
                    new InputStreamReader(socket.getInputStream(), StandardCharsets.US_ASCII));
            var outputStreamWriter = new BufferedWriter(
                    new OutputStreamWriter(socket.getOutputStream(), StandardCharsets.US_ASCII));

            parseRequest(inputStreamReader);
            writeResponse(outputStreamWriter);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void parseRequest(BufferedReader inputStreamReader) throws IOException {
        var request = inputStreamReader.readLine();

        while (request != null && !request.isEmpty()) {
//            System.out.println(request);
            requestHeaders += request;
            request = inputStreamReader.readLine();
        }
    }

    private void writeResponse(BufferedWriter outputStreamWriter) {
        try {
            String[] requestInfo = requestHeaders.split(" ");
            var pageReader = new HtmlPageReader();
            outputStreamWriter.write(pageReader.buildHttpHeader(requestInfo[1]));
            outputStreamWriter.newLine();
            outputStreamWriter.write(pageReader.readFile(requestInfo[1]));
            outputStreamWriter.newLine();
            outputStreamWriter.flush();
        } catch (IOException | Exception404 e) {
            if (e.getMessage().equals("404")) {
                try {
                    var pageReader = new HtmlPageReader();
                    outputStreamWriter.write(pageReader.buildHttpHeader("404.html"));
                    outputStreamWriter.newLine();
                    outputStreamWriter.write(pageReader.readFile("404.html"));
                    outputStreamWriter.newLine();
                    outputStreamWriter.flush();

                } catch (IOException | Exception404 exception) {
                    throw new RuntimeException(exception);
                }
            }else{
                throw new RuntimeException(e);
            }

        }

    }
}
