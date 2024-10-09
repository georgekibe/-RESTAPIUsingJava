import io.restassured.path.json.JsonPath;
import org.testng.Assert;

public class TraversingNestedJSON {

    public static void main(String[] args) {


        JsonPath js = new JsonPath(payload.mockResponse());

        // 1. Print No of courses returned by API
        int count = js.getInt("courses.size()");
        System.out.println(count);


        //2.Print Purchase Amount

        int totalAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println(totalAmount);


        //3. Print Title of the first course

        String firstCourseTitle = js.getString("courses[0].title");
        System.out.println(firstCourseTitle);

        //4. Print All course titles and their respective Prices

        for (int i = 0; i < count; i++) {
            String courses = js.getString("courses[" + i + "].title");
            int prices = js.getInt("courses[" + i + "].price");
            System.out.println("The course: " + courses + ". The price " + prices);
        }


        // 5. Print no of copies sold by RPA Course


        for (int k = 0; k < count; k++) {
            String courseRPA = js.getString("courses[" + k + "].title");
            if (courseRPA.equalsIgnoreCase("RPA")) {
                int copiesRPA = js.getInt("courses[" + k + "].copies");
                System.out.println("RPA: " + copiesRPA);
                break;

            }
        }

       // 6. Verify if Sum of all Course prices matches with Purchase Amount
        int totalPrice = 0;
        for (int m = 0; m < count; m++) {

            int coursePrices = js.getInt("courses[" + m + "].price");
            int courseCopies = js.getInt("courses[" + m + "].copies");
            totalPrice += (coursePrices * courseCopies);
        }
        Assert.assertEquals(totalAmount, totalPrice);

    }
}
