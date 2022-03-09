package tue.shop4cf.integration.rest;

import org.camunda.bpm.engine.RuntimeService;
import tue.shop4cf.integration.ngsild.controller.TaskController;

import java.io.IOException;
import java.io.DataOutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.inject.Named;

@Named
public class CamundaRESTCalls {

    @Inject
    private RuntimeService runtimeService;

    private final Logger LOGGER = Logger.getLogger(TaskController.class.getName());

    public void TaskComplete (String engine_host_address, String engine_host_port, String process_instance_id, String task_instance_id, String postJsonData) {

        try {

            //System.out.print("It's here " + engine_host_address);
            String url = "http://"+ engine_host_address + ":"+ engine_host_port + "/engine-rest/task/" + task_instance_id + "/complete";
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

            // Setting basic post request
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            //add variables
            //String postJsonData = "{}";
            //String postJsonData = "{\"variables\":\r\n    {\"aVariable\": {\"value\": \"aStringValue\"},\r\n    \"anotherVariable\": {\"value\": 42},\r\n    \"aThirdVariable\": {\"value\": true}}\r\n}";
            postJsonData = "{\"variables\":\r\n" + postJsonData + "\r\n}";

            // Send post request
            conn.setDoOutput(true);

//        OutputStream os = conn.getOutputStream();
//        os.write(postJsonData.getBytes());
//        os.flush();
//        os.close();
            System.out.println("Sending 'POST' request to URL : " + url);
            System.out.println("Post Data : " + postJsonData);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(postJsonData);
            wr.flush();
            wr.close();

            int responseCode = conn.getResponseCode();
            System.out.println("Response Code : " + responseCode);
            conn.disconnect();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    public void DeleteInstances (String engine_host_address, String engine_host_port, String processDefinitionKey) {

        try {

            //System.out.print("It's here " + engine_host_address);
            String url = "http://"+ engine_host_address + ":"+ engine_host_port + "/engine-rest/process-instance/delete";
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();

            // Setting basic post request
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");

            //String postJsonData = "{}";
            String postJsonData = "{\"processInstanceQuery\":\r\n {\"processDefinitionKey\": \"" + processDefinitionKey + "\"}}";

            // Send post request
            conn.setDoOutput(true);

//        OutputStream os = conn.getOutputStream();
//        os.write(postJsonData.getBytes());
//        os.flush();
//        os.close();
            System.out.println("Sending 'POST' request to URL : " + url);
            System.out.println("Post Data : " + postJsonData);
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.writeBytes(postJsonData);
            wr.flush();
            wr.close();

            int responseCode = conn.getResponseCode();
            System.out.println("Response Code : " + responseCode);
            conn.disconnect();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();

        }
    }

}
