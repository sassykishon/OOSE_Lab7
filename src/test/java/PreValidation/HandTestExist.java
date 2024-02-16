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



  

  

}