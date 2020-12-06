import com.github.myzhan.locust4j.Locust;
import tasks.CalculateExpressionTask;
import tasks.OpenApplicationTask;

public class LocustMain {

    public static void main(String[] args) {
        Locust locust = Locust.getInstance();
        locust.setMasterHost("127.0.0.1");
        locust.setMasterPort(5557);

        locust.run(
                new OpenApplicationTask(50),
                new CalculateExpressionTask(50)
        );

    }

}
