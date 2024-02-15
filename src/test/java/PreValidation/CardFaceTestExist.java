package PreValidation;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class CardFaceTestExist{
  final String file_name = "src/test/java/game/card/entity/CardFaceTest.java";
  File file = new File(file_name);
  JavaFile javaFile = new JavaFile(file);

  @Test
  void fileExists() {
    assertTrue(file.isFile());
  }

  @Test
  void getValueAceExist(){
    assertTrue(javaFile.hasMethodByName("getValueAce"));
  }

  @Test
  void getValueKingExist(){
    assertTrue(javaFile.hasMethodByName("getValueKing"));
  }

  @Test
  void getValueQueenExist(){
    assertTrue(javaFile.hasMethodByName("getValueQueen"));
  }

  @Test
  void getValueJackExist(){
    assertTrue(javaFile.hasMethodByName("getValueJack"));
  }

  @Test
  void getValueTenExist(){
    assertTrue(javaFile.hasMethodByName("getValueTen"));
  }

  @Test
  void getValueNineExist(){
    assertTrue(javaFile.hasMethodByName("getValueNine"));
  }

  @Test
  void getRankTenExist(){
    assertTrue(javaFile.hasMethodByName("getRankTen"));
  }

  @Test
  void displayCamelCaseExist(){
    assertTrue(javaFile.hasMethodByName("displayCamelCase"));
  }

  @Test
  void shortDisplayTenExist(){
    assertTrue(javaFile.hasMethodByName("shortDisplayTen"));
  }

  @Test
  void testToStringThreeExist(){
    assertTrue(javaFile.hasMethodByName("testToStringThree"));
  }

}