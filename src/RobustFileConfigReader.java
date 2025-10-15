
    import java.io.*;

public class RobustFileConfigReader {

    public static void main(String[] args) {
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader("config.txt"));
            
            // Read the first line (version)
            String versionLine = reader.readLine();
            int version = Integer.parseInt(versionLine);
            
            // Throw custom exception if version too old
            if (version < 2) {
                throw new Exception("Config version too old!");
            }

            // Read the second line (path)
            String filePath = reader.readLine();
            File file = new File(filePath);
            
            if (!file.exists()) {
                throw new IOException("File at path does not exist: " + filePath);
            }

            System.out.println("Config successfully read!");
            System.out.println("Version: " + version);
            System.out.println("Path: " + filePath);

        } catch (FileNotFoundException e) {
            System.out.println("Error: config.txt not found!");
        } catch (NumberFormatException e) {
            System.out.println("Error: First line is not a valid number!");
        } catch (IOException e) {
            System.out.println("I/O Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Config read attempt finished.");
            try {
                if (reader != null) reader.close();
            } catch (IOException e) {
                System.out.println("Error closing file: " + e.getMessage());
            }
        }}}