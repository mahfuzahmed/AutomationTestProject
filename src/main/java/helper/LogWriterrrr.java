//package helper;
//import java.io.*;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Properties;
//
//import applicationsettings.ApplicationSettings;
//import org.apache.log4j.Logger;
//import org.apache.log4j.PropertyConfigurator;
//public class LogWriterrrr {
//    public enum logLevel {
//        DEBUG,
//        INFO,
//        WARN,
//        ERROR,
//        FATAL
//    }
//    List<String> log4j2Properties = Arrays.asList(
//            "status = warn",
//            "name= RollingFileLogConfigDemo",
//
//            "property.basePath = C:/Users/abir/Documents/GitHub/AutomationTestProject/test-output/logs/",
//
//            "appender.rolling.type = RollingFile",
//            "appender.rolling.name = fileLogger",
//            "appender.rolling.fileName= ${basePath}/log_of_${date:yyyy_MM_dd__HH_mm_ss_a}.log",
//            "appender.rolling.filePattern= ${basePath}/app_%d{yyyyMMdd}.log.gz",
//            "appender.rolling.layout.type = PatternLayout",
//            "appender.rolling.layout.pattern = %d{yyyy-MM-dd HH:mm:ss.SSS} %level [%t] [%l] - %msg%n",
//            "appender.rolling.policies.type = Policies",
//
//            "appender.rolling.policies.size.type = SizeBasedTriggeringPolicy",
//            "appender.rolling.policies.size.size = 10MB",
//            "appender.rolling.policies.time.type = TimeBasedTriggeringPolicy",
//            "appender.rolling.policies.time.interval = 1",
//            "appender.rolling.policies.time.modulate = true",
//            "appender.rolling.strategy.type = DefaultRolloverStrategy",
//            "appender.rolling.strategy.delete.type = Delete",
//            "appender.rolling.strategy.delete.basePath = ${basePath}",
//            "appender.rolling.strategy.delete.maxDepth = 10",
//            "appender.rolling.strategy.delete.ifLastModified.type = IfLastModified",
//
//            "appender.rolling.strategy.delete.ifLastModified.age = 30d",
//
//            "rootLogger.level = debug",
//            "rootLogger.appenderRef.rolling.ref = fileLogger"
//    );
//
//    public void generateOutputDirectory() {
//        try {
//            File currentDirectory = new File("");
//            boolean configurationCreateStatus = new File(currentDirectory.getCanonicalPath() + "/src/test/resources/Configurations").mkdirs();
//            boolean log4jCreateStatus = new File(currentDirectory.getCanonicalPath() + "/src/test/resources/Configurations/log4j.properties").createNewFile();
//            boolean testConfigurationsCreateStatus = new File(currentDirectory.getCanonicalPath() + "/src/test/resources/Configurations/TestConfigurations.properties").createNewFile();
//            boolean testngCreateStatus = new File(currentDirectory.getCanonicalPath() + "/src/test/java/testng.xml").createNewFile();
//            boolean testReportsCreateStatus = new File(currentDirectory.getCanonicalPath() + "/TestReports").mkdirs();
//            Writer output = new BufferedWriter(new FileWriter(currentDirectory.getCanonicalPath() + "/src/test/resources/Configurations/log4j.properties"));
//            for(String line: log4j2Properties){
//                output.append(line).append("\n");
//            }
//            output.close();
//
//            LocalDateTime now = LocalDateTime.now();
//            String pattern = "yyyy_MM_dd_hh_mm_ss_a";
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
//            String formattedDate = now.format(formatter);
//            String directoryPath = currentDirectory.getCanonicalPath() + "/TestReports/" + formattedDate;
//            boolean createStatus = new File(directoryPath).mkdirs();
//            if (createStatus) {
//                Common.outputDirectory = directoryPath;
//            }
//            if (configurationCreateStatus && log4jCreateStatus && testConfigurationsCreateStatus && testngCreateStatus && testReportsCreateStatus){
//                System.out.println("All files have been created successfully");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * Implementing generate output directory functionality
//     */
//    public void generateOutputDirectory() {
//        try {
//            File currentDirectory = new File("");
//            boolean log4j2CreateStatus = new File(currentDirectory.getCanonicalPath() + "/src/main/resources/log4j2.properties").createNewFile();
//            Writer output = new BufferedWriter(new FileWriter(currentDirectory.getCanonicalPath() + "/src/main/resources/log4j2.properties"));
//            for(String line: log4j2Properties){
//                output.append(line).append("\n");
//            }
//            output.close();
//
//            LocalDateTime now = LocalDateTime.now();
//            String pattern = "yyyy_MM_dd_hh_mm_ss_a";
//            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
//            String formattedDate = now.format(formatter);
//            String directoryPath = currentDirectory.getCanonicalPath() + "/TestReports/" + Common.reportGenerationTimeStamp;
//            String directoryPath = System.getProperty("user.dir") + "/test-output/test-reports/" + ApplicationSettings.getReportDirectory() + "/" + fileName + "_" + getDate() + ".log";
//            boolean createStatus = new File(directoryPath).mkdirs();
//            if (createStatus) {
//                Common.outputDirectory = directoryPath;
//            }
//            if (log4j2CreateStatus && testConfigurationsCreateStatus && testngCreateStatus && testReportsCreateStatus){
//                System.out.println("All files have been created successfully");
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    /**
//     * Implementing create log file functionality
//     */
//    public void createLogFile() {
//        String log4jPropertyFileName = "./src/test/resources/Configurations/log4j.properties";
//        String logFilePath = Common.outputDirectory + "\\LogFile.log";
//        try {
//            logFilePath = logFilePath.replace("/", "\\");
//            FileInputStream fileInputStream = new FileInputStream(log4jPropertyFileName);
//            Properties properties = new Properties();
//            properties.load(fileInputStream);
//            properties.setProperty("log4j.appender.FA.File", logFilePath);
//            FileOutputStream output = new FileOutputStream(log4jPropertyFileName);
//            properties.store(output, "Log file path updated to : " + logFilePath);
//            PropertyConfigurator.configure(log4jPropertyFileName);
//            output.close();
//            fileInputStream.close();
//            Common.logger = Logger.getLogger(logFilePath);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//    /**
//     * Implementing write to log File functionality
//     *
//     * @param level   log level
//     * @param message message to write
//     */
//    public static void writeToLogFile(logLevel level, String message) {
//        if (level.equals(logLevel.DEBUG))
//            Common.logger.debug(message);
//        else if (level.equals(logLevel.INFO))
//            Common.logger.info(message);
//        else if (level.equals(logLevel.WARN))
//            Common.logger.warn(message);
//        else if (level.equals(logLevel.ERROR))
//            Common.logger.error(message);
//        else if (level.equals(logLevel.FATAL))
//            Common.logger.fatal(message);
//        else
//            Common.logger.error("Invalid log level : '" + level + "' provided to log writer. Unable to log the message.");
//    }
//}