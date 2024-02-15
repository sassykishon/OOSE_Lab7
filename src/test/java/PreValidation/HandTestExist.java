package PreValidation;
import org.junit.jupiter.api.Test;
import java.io.File;
import static org.junit.jupiter.api.Assertions.*;

public class HandTestExist{
  final String file_name = "src/test/java/game/card/entity/HandTest.java";
  File file = new File(file_name);
  JavaFile javaFile = new JavaFile(file);

  @Test
  void fileExists() {
    assertTrue(file.isFile());
  }

  @Test
  void getFirstCardExist(){
    assertTrue(javaFile.hasMethodByName("getFirstCard"));
  }

  @Test
  void getLastCardExist(){
    assertTrue(javaFile.hasMethodByName("getLastCard"));
  }

  @Test
  void getHandOfCardsExist(){
    assertTrue(javaFile.hasMethodByName("getHandOfCards"));
  }

  @Test
  void playACardNoParamExist(){
    assertTrue(javaFile.hasMethodByName("playACardNoParam"));
  }

  @Test
  void playACardIntegerExist(){
    assertTrue(javaFile.hasMethodByName("playACardInteger"));
  }

  @Test
  void copyExist(){
    assertTrue(javaFile.hasMethodByName("copy"));
  }

  @Test
  void copySizeExist(){
    assertTrue(javaFile.hasMethodByName("copySize"));
  }

  @Test
  void isEmptyExist(){
    assertTrue(javaFile.hasMethodByName("isEmpty"));
  }

  @Test
  void sizeExist(){
    assertTrue(javaFile.hasMethodByName("size"));
  }

  @Test
  void sizeZeroExist(){
    assertTrue(javaFile.hasMethodByName("sizeZero"));
  }

  @Test
  void findACardExist(){
    assertTrue(javaFile.hasMethodByName("findACard"));
  }

}