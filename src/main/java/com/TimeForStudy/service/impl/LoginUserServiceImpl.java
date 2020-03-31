package com.TimeForStudy.service.impl;

import com.TimeForStudy.entity.User;
import com.TimeForStudy.otherDataClasses.ListWaiting;
import com.TimeForStudy.repository.UserRepository;
import com.TimeForStudy.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unbescape.uri.UriEscape;

import java.util.Map;

@Service
public class LoginUserServiceImpl implements LoginUserService {

    public ListWaiting listWaiting;

    public UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public String CheckPhone(String phone) {

        User user = userRepository.findByPhone(phone);

        if (user==null) {
            return "no login";
        } else {
            String send = "Код активации:"; // текст SMS
            String code = "1273";

            String _from = "TimeFStudy";
            String apikey = "FRLC1KQK71446PS85MFH8434D63P0LEB8A8EL3AS068LC31XYQS083B1N5PL24HE";
            String url = "http://smspilot.ru/api.php" +
                    "?send=" + UriEscape.escapeUriPath(send) +
                    "&to=" + phone +
                    "&from=" + _from +
                    "&apikey=" + apikey +
                    "&charset=windows-1251";
//
//            HttpRequest request = HttpRequest.newBuilder().bu;
//            WebRequest request = WebRequest.Crea
//            HttpRequest httpRequest = HttpRequestBuilderImpl.createGet(url, String.class)
//                    .responseDeserializer(ResponseDeserializer.ignorableDeserializer()).build();
//            HttpWebRequest myHttpWebRequest =
//                    HttpRequest httpRequest =
//                    (HttpWebRequest) HttpWebRequest.Create(url);

            // выполняем запрос
//            HttpWebResponse myHttpWebResponse = (HttpWebResponse) myHttpWebRequest.GetResponse();
            return "login";
        }
    }

    @Override
    public String CheckCode(Integer code) {
        return null;
    }
}
