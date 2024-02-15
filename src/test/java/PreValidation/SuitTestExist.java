package PreValidation;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class SuitTestExist{
  final String file_name = "src/test/java/game/card/entity/SuitTest.java";
  File file = new File(file_name);
  JavaFile javaFile = new JavaFile(file);

  @Test
  void fileExists() {
    assertTrue(file.isFile());
  }

  @Test
  void displayCamelCaseHeartsExist(){
    assertTrue(javaFile.hasMethodByName("displayCamelCaseHearts"));
  }

  @Test
  void displayCamelCaseDiamondsExist(){
    assertTrue(javaFile.hasMethodByName("displayCamelCaseDiamonds"));
  }

  @Test
  void displayCamelCaseClubsExist(){
    assertTrue(javaFile.hasMethodByName("displayCamelCaseClubs"));
  }

  @Test
  void displayCamelCaseSpadesExist(){
    assertTrue(javaFile.hasMethodByName("displayCamelCaseSpades"));
  }

  @Test
  void displayFirstLetterHeartsExist(){
    assertTrue(javaFile.hasMethodByName("displayFirstLetterHearts"));
  }

  @Test
  void displayFirstLetterDiamondsExist(){
    assertTrue(javaFile.hasMethodByName("displayFirstLetterDiamonds"));
  }

  @Test
  void displayFirstLetterClubsExist(){
    assertTrue(javaFile.hasMethodByName("displayFirstLetterClubs"));
  }

  @Test
  void displayFirstLetterSpadesExist(){
    assertTrue(javaFile.hasMethodByName("displayFirstLetterSpades"));
  }

  @Test
  void testToStringExist(){
    assertTrue(javaFile.hasMethodByName("testToString"));
  }

  @Test
  void getSuitHeartsExist(){
    assertTrue(javaFile.hasMethodByName("getSuitHearts"));
  }

  @Test
  void getSuitDiamondsExist(){
    assertTrue(javaFile.hasMethodByName("getSuitDiamonds"));
  }

  @Test
  void getSuitSpadesExist(){
    assertTrue(javaFile.hasMethodByName("getSuitSpades"));
  }

  @Test
  void getSuitClubsExist(){
    assertTrue(javaFile.hasMethodByName("getSuitClubs"));
  }

  @Test
  void getSuitOrdinalHeartsExist(){
    assertTrue(javaFile.hasMethodByName("getSuitOrdinalHearts"));
  }

  @Test
  void getSuitOrdinalDiamondExist(){
    assertTrue(javaFile.hasMethodByName("getSuitOrdinalDiamond"));
  }

  @Test
  void getSuitOrdinalSpadesExist(){
    assertTrue(javaFile.hasMethodByName("getSuitOrdinalSpades"));
  }

  @Test
  void getSuitOrdinalClubsExist(){
    assertTrue(javaFile.hasMethodByName("getSuitOrdinalClubs"));
  }

}