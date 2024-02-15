package game;

import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Scanner; 



public class LoadCSVTest {

    LoadCSV loadCSV = new LoadCSV();

    @Test
    void testCreateCSVReader() throws Exception {
        try {
            Scanner scanner = loadCSV.createCSVreader(Game.playerNamesFile);
            assertTrue(scanner.hasNextLine());
        } catch (IOException e) {
        }
    }

    @Test 
    void testCreateCSVReaderException(){
        assertThrows(IOException.class, () -> loadCSV.createCSVreader("rubish path"));
    }

    @Test
    void testGetCSVReader() {
        try {
            Scanner scanner = loadCSV.getCSVReader(Game.playerNamesFile);
            assertTrue(scanner.hasNextLine());
        } catch (Exception e) {
        }
    }

    @Test
    void testGetCSVReaderException() {
        assertThrows(IOException.class, () -> loadCSV.getCSVReader("rubish path"));

    }

    @Test
    void testGetCSVRows() {
        assertEquals(7, loadCSV.getCSVRows(Game.playerNamesFile).size());
    }

    @Test
    void testSetCSVReader() {
        Scanner scanner = new Scanner(System.in);
        loadCSV.setCSVReader(scanner, null);
        assertEquals(scanner, loadCSV.csvReader);
    }

    @Test
    void testSetCSVReaderConfig() {
        Scanner scanner = new Scanner(System.in);
        loadCSV.setCSVReader(scanner, "Test");
        assertEquals("Test", loadCSV.configPath);
    }
}
