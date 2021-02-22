package nl.han.dea.http;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;

public class HtmlPageReader {
    public String readFile(String filename) throws Exception404 {
        var fullFileName = "pages/".concat(filename);
        try {
            ClassLoader classLoader = getClass().getClassLoader();

            var file = new File(classLoader.getResource(fullFileName).getFile()).toPath();

            var fileAsString = new String(Files.readAllBytes(file));

            return fileAsString;
        } catch (Exception e) {
            throw new Exception404("");
        }
    }

    public String buildHttpHeader(String filename) throws Exception404 {
        String Content = readFile(filename);

        return "HTTP/1.1 200 OK\n" +
                "Date: " + getServerTime() + "/n" +
                "HttpServer: Simple DEA Webserver\n" +
                "Content-Length: " + Content.length() + "\n" +
                "Content-Type: text/html\n";

    }

    String getServerTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "EEE, dd MMM yyyy HH:mm:ss z");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
        return dateFormat.format(calendar.getTime());
    }
}