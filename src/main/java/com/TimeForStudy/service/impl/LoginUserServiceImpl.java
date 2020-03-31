package com.TimeForStudy.service.impl;

import com.TimeForStudy.entity.User;
import com.TimeForStudy.otherDataClasses.ListWaiting;
import com.TimeForStudy.repository.UserRepository;
import com.TimeForStudy.service.LoginUserService;
//import jdk.internal.net.http.HttpRequestBuilderImpl;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.WebRequest;
import org.unbescape.uri.UriEscape;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.http.HttpRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class LoginUserServiceImpl implements LoginUserService {

    List<ListWaiting> waitingList = new ArrayList<ListWaiting>();

    public UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String CheckPhone(String phone) {
        User user = userRepository.findByPhone(phone);
//        return "Hello, " + user.getName();

        if (user==null) {
            return "null";
        } else {
//            String send = "Авторизация в системе TimeForStudy\n" +
//                    "Код ";
            String send = "Code ";
            Integer code = (int) (Math.random() * 8999) + 1000;
            ListWaiting listWaiting = new ListWaiting(code);
            waitingList.add(listWaiting);
            send += code;
            String _from = "";
            String apikey = "7CBWUPSSQK232C52P01VP1FM5Z1RA3G7D1C7DE2BTLCF50B8OZ7RKCM85GRB95E2";

            final CloseableHttpClient httpClient = HttpClients.createDefault();

            HttpGet request = new HttpGet(
                "http://smspilot.ru/api.php" +
                        "?send=" + UriEscape.escapeUriPath(send) +
                        "&to=" + phone +
                        "&from=" + _from +
                        "&apikey=" + apikey);

            String result = "";
            try (CloseableHttpResponse response = httpClient.execute(request)) {
                // Get HttpResponse Status
                System.out.println(response.getStatusLine().toString());

                HttpEntity entity = response.getEntity();
                Header headers = entity.getContentType();
                System.out.println(headers);

                if (entity != null) {
                    result = EntityUtils.toString(entity);
                    System.out.println(result);
                    if (result.substring(0, 6) == "SUCCESS")
                        return "codeSended";
                }

            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return result;
        }
    }

    @Override
    public String CheckCode(ListWaiting listWaiting) {
        if (waitingList.indexOf(listWaiting)==-1) {
            return "неверный код";
        } else {
            return "входите";
        }
    }
}
