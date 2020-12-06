package tasks;

import com.github.myzhan.locust4j.AbstractTask;
import com.github.myzhan.locust4j.Locust;
import io.restassured.RestAssured;
import io.restassured.response.Response;


public class OpenApplicationTask extends AbstractTask {
    private int weight;

    public int getWeight() {
        return weight;
    }

    public String getName() {
        return "Open application task";
    }

    public OpenApplicationTask(int weight) {
        this.weight = weight;
    }

    @Override
    public void execute() {
        try {
            Response response = RestAssured.given().get("http://localhost:8080/");
            Locust.getInstance().recordSuccess("http", getName(), response.getTime(), 1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

}
