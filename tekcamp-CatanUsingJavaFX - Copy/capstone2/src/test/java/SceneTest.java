import com.teksystems.bootcamp.capstone2.scene.SceneGame;
import com.teksystems.bootcamp.capstone2.scene.SceneSelectNumberOfPlayers;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.testng.Assert;
import org.testng.annotations.Test;
public class SceneTest {
    @Test
    public void sceneTest() throws Exception {
    TestApplication.startRunning();
    Scene scene= new SceneGame(new Stage(), 2);
        Assert.assertEquals(scene.getClass(),new SceneGame(new Stage(), 2).getClass());
    TestApplication.stopRunning();
    }
    @Test
    public void sceneWelcomeTest() throws Exception {
        TestApplication.startRunning();
        Scene scene= new SceneSelectNumberOfPlayers(new Stage());
        Assert.assertEquals(scene.getClass(),new SceneSelectNumberOfPlayers(new Stage()).getClass());
        TestApplication.stopRunning();
    }
}
