package com.TimeForStudy.application.user.service.impl;

import ch.qos.logback.core.status.Status;
import com.TimeForStudy.application.user.domain.UserEntity;
import com.TimeForStudy.application.otherDataClasses.VerificationPair;
import com.TimeForStudy.application.user.domain.UserRepository;
import com.TimeForStudy.application.user.model.UserDto;
import com.TimeForStudy.application.user.service.LoginUserService;
import lombok.RequiredArgsConstructor;
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

/**
 * Сервис авторизации пользователя.
 *
 * @author Velikanov Artyom
 * @author Narizhny Pavel
 */
@Service
@RequiredArgsConstructor
public class LoginUserServiceImpl implements LoginUserService {

    /**
     * Пара телефон - код
     */
    HashMap<String, String> waitingList = new HashMap<>();

    /**
     * {@link UserRepository}
     */
    public final UserRepository userRepository;

    /**
     * Проверка телефона
     *
     * @param phone телефон
     * @return статус
     */
    @Override
    public String sendCode(String phone) {
        UserEntity user = userRepository.findByPhone(phone);
        if (user != null) {
//            Integer code = (int) (Math.random() * 8999) + 1000;
            String code = "1111";
            waitingList.put(phone, code);
            new CodeTimer(phone);
            if (user == null) {
                return "null";
            } else {
                String send = "Авторизация в системе TimeForStudy\n" +
                        "Код ";
//                send += code;
                String _from = "";
                String apikey = "7CBWUPSSQK232C52P01VP1FM5Z1RA3G7D1C7DE2BTLCF50B8OZ7RKCM85GRB95E2";
//
                final CloseableHttpClient httpClient = HttpClients.createDefault();

                HttpGet request = new HttpGet(
                        "http://smspilot.ru/api.php" +
                                "?send=" + UriEscape.escapeUriPath(send) +
                                "&to=" + phone +
                                "&from=" + _from +
                                "&apikey=" + apikey);
//
                String result = "SUCCESS";
//                try (CloseableHttpResponse response = httpClient.execute(request)) {
//                     Get HttpResponse Status
//                    System.out.println(response.getStatusLine().toString());

//                    HttpEntity entity = response.getEntity();
//                    Header headers = entity.getContentType();
//                    System.out.println(headers);
//
//                    if (entity != null) {
//                        result = EntityUtils.toString(entity);
//                        System.out.println(result);
//                        if (result.substring(0, 7).equals("SUCCESS"))
                            return "codeSent";
//                        else
//                            return "error";
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

    /**
     * Проверка кода
     *
     * @param verificationPair пара код - телефон
     * @return пользователь
     */
    @Override
    public UserDto checkCode(VerificationPair verificationPair) {
        UserEntity user = userRepository.findByPhone(verificationPair.getPhone());

        UserDto dto = UserDto.of(user);
        if (waitingList.get(verificationPair.getPhone()).compareTo(verificationPair.getCode()) == 0) {
            return dto;
            } else {
            return new UserDto();
        }
    }

    /**
     * Проверка телефона в настройках
     *
     * @param phone телефон
     * @return статус
     */
    @Override
    public String settingsSendCode(String phone) {
//            Integer code = (int) (Math.random() * 8999) + 1000;
            String code = "1111";
            waitingList.put(phone, code);
            new CodeTimer(phone);
                String send = "Авторизация в системе TimeForStudy\n" +
                        "Код ";
//                send += code;
//                String _from = "";
//                String apikey = "7CBWUPSSQK232C52P01VP1FM5Z1RA3G7D1C7DE2BTLCF50B8OZ7RKCM85GRB95E2";

//                final CloseableHttpClient httpClient = HttpClients.createDefault();

//                HttpGet request = new HttpGet(
//                        "http://smspilot.ru/api.php" +
//                                "?send=" + UriEscape.escapeUriPath(send) +
//                                "&to=" + phone +
//                                "&from=" + _from +
//                                "&apikey=" + apikey);
//
                String result = "SUCCESS";
//                try (CloseableHttpResponse response = httpClient.execute(request)) {
//                     Get HttpResponse Status
//                    System.out.println(response.getStatusLine().toString());

//                    HttpEntity entity = response.getEntity();
//                    Header headers = entity.getContentType();
//                    System.out.println(headers);
//
//                    if (entity != null) {
//                        result = EntityUtils.toString(entity);
//                        System.out.println(result);
//                        if (result.substring(0, 7).equals("SUCCESS"))
                return "codeSent";
//                        else
//                            return "error";
//                    }
//                } catch (ClientProtocolException e) {
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

//            return "end_error";
    }

    /**
     * Проверка кода в настройках
     *
     * @param verificationPair пара код - телефон
     * @return пользователь
     */
    @Override
    public String settingsCheckCode(VerificationPair verificationPair) {

        if (waitingList.get(verificationPair.getPhone()).compareTo(verificationPair.getCode()) == 0) {
            return "success";
        } else {
            return "error";
        }
    }

    /**
     * Проверка телефона
     *
     * @param phone телефон
     * @return статус
     */
    @Override
    public String checkPhone(String phone) {
        if (userRepository.findByPhone(phone) != null)
            return "registered";
        else
            return "unregistered";

    }

    /**
     * Генераци времени ожидания получения кода
     */
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
