package com.TimeForStudy.service.impl;

import com.TimeForStudy.entity.User;
import com.TimeForStudy.otherDataClasses.VerificationPair;
import com.TimeForStudy.repository.UserRepository;
import com.TimeForStudy.service.LoginUserService;
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
import org.unbescape.uri.UriEscape;

import java.io.IOException;
import java.util.*;

@Service
public class LoginUserServiceImpl implements LoginUserService {

    HashMap<String, Integer> waitingList = new HashMap<>();

    public UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String CheckPhone(String phone) {
        User user = userRepository.findByPhone(phone);
        if (user != null) {
//            Integer code = (int) (Math.random() * 8999) + 1000;
            Integer code = 1111;
            waitingList.put(phone, code);
            if (user == null) {
                return "null";
            } else {
                String send = "Авторизация в системе TimeForStudy\n" +
                        "Код ";
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

                String result = "SUCCESS";
//                try (CloseableHttpResponse response = httpClient.execute(request)) {
                    // Get HttpResponse Status
//                    System.out.println(response.getStatusLine().toString());

//                    HttpEntity entity = response.getEntity();
//                    Header headers = entity.getContentType();
//                    System.out.println(headers);

//                    if (entity != null) {
//                        result = EntityUtils.toString(entity);
//                        System.out.println(result);
                        if (result.substring(0, 7).equals("SUCCESS"))
                            return "codeSended";
                        else
                            return "error";
//                    }
//                } catch (ClientProtocolException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
            }
//            return "end_error";
        }else
            return "registrationNeeded";
    }

    @Override
    public String CheckCode(VerificationPair verificationPair) {
        System.out.println(verificationPair.getPhone() + " " + verificationPair.getCode());
        if (waitingList.get(verificationPair.getPhone()).compareTo(verificationPair.getCode()) == 0) {
            return "hi, " + userRepository.findByPhone(verificationPair.getPhone()).getName();
        } else {
            return "code error";
        }
    }
}
