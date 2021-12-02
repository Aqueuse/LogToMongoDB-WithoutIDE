package logToMongoDB;

public class Main {
    public static void main(String[] Args) {
        LogToMongoDB logger = new LogToMongoDB();
        logger.flushLogs();
        logger.logInDB("coin coin !");
        logger.logInTerm("coin coin Windows !");
    }
}
