package model;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;

public class FileHandler {
    public void historyWrite(String base, double value, String target, double result) {
        try (FileWriter flW = new FileWriter("./log/history.log", true)) {
            flW.write("%.2f [%s] >> %.2f [%s]\n".formatted(value, base, result, target));
            flW.flush();
        }
        catch (IOException e) {
            this.logWrite(e.getMessage());
        }
    }

    public String historyRead() {
        try (BufferedReader bfR = new BufferedReader(new FileReader ("./log/history.log"))) {
            StringBuilder stringBuilder = new StringBuilder();
            String currentLine;
            stringBuilder.append("================ Histórico ================\n");
            while ((currentLine = bfR.readLine()) != null) {
                stringBuilder.append(currentLine).append("\n");
            }
            return new String(stringBuilder);
        }
        catch (IOException e) {
            logWrite(e.getMessage());
            throw new RuntimeException("Houve um erro na leitura do histórico. Tente novamente!");
        }
    }

    public void logWrite(String message) {
        try (FileWriter flW = new FileWriter("./log/applicationFlux.log", true)) {
            LocalDateTime localDateTime = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd - HH:mm:ss");
            String dateTime = localDateTime.format(formatter);
            flW.write("[%s] - %s \n".formatted(dateTime, message));
            flW.flush();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void createApiKeyFile() {
        File file = new File("./enter-your-api-key-here");
        try {
            boolean operationResult = file.createNewFile();
            if (operationResult) {
                this.logWrite("Apikey file created!");
            }
        }
        catch (IOException e) {
            this.logWrite(e.getMessage());
            throw new RuntimeException();
        }
    }
}