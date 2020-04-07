package com.TimeForStudy.application.user.service.impl;

import com.TimeForStudy.application.user.domain.User;
import com.TimeForStudy.application.otherDataClasses.VerificationPair;
import com.TimeForStudy.application.user.domain.UserRepository;
import com.TimeForStudy.application.user.model.UserDto;
import com.TimeForStudy.application.user.service.LoginUserService;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unbescape.uri.UriEscape;

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
    public String sendCode(String phone) {
        User user = userRepository.findByPhone(phone);
        if (user != null) {
//            Integer code = (int) (Math.random() * 8999) + 1000;
            Integer code = 1111;
            waitingList.put(phone, code);
            new CodeTimer(phone);
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
                            return "codeSent";
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
    public UserDto checkCode(VerificationPair verificationPair) {
        User user = userRepository.findByPhone(verificationPair.getPhone());
        UserDto dto = UserDto.of(user);
        System.out.println(waitingList.toString());
        //TODO убрать комментарии снизу
//        if (waitingList.get(verificationPair.getPhone()).compareTo(verificationPair.getCode()) == 0) {
            return dto;
//            } else {
//            return new UserDto();
//        }
    }

    @Override
    public String checkPhone(String phone) {
        if (userRepository.findByPhone(phone) != null)
            return "registered";
        else
            return "unregistered";

    }

    private class CodeTimer  {
        private Timer timer;
        private TimerTask timerTask;
        private int id;
        private String phone;

        CodeTimer(String phone) {
            this.phone = phone;
            setTimerTask();
            timer = new Timer();
            // будем запускать каждых 60 секунд (60 * 1000 миллисекунд)
            timer.schedule(timerTask, 60 * 1000);
        }

        void setTimerTask() {
            timerTask = new TimerTask() {
                @Override
                public void run() {
                    waitingList.remove(phone);
                }
            };
        }

        void stopTimer(){
            timer.cancel();
        }
    }
}
