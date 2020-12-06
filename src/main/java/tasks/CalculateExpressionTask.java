package tasks;

import com.github.myzhan.locust4j.AbstractTask;
import com.github.myzhan.locust4j.Locust;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class CalculateExpressionTask extends AbstractTask {
    private int weight;

    @Override
    public int getWeight() {
        return weight;
    }

    @Override
    public String getName() {
        return "Calculate Expression Task";
    }

    public CalculateExpressionTask(int weight) {
        this.weight = weight;
    }

    @Override
    public void execute() {
        try {
            Response response =
                    RestAssured.given().params(
                            "expression", "2*2"
                    ).
                            when().get("http://localhost:8080/calculate");

            assert response.getStatusCode() == 200;

            Locust.getInstance().recordSuccess("GET", getName(), response.getTime(), 1);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}