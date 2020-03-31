package com.TimeForStudy.service.impl;

import com.TimeForStudy.entity.User;
import com.TimeForStudy.otherDataClasses.ListWaiting;
import com.TimeForStudy.repository.UserRepository;
import com.TimeForStudy.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.unbescape.uri.UriEscape;

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

        if (user==null) {
            return "null";
        } else {
            String send = "Код активации:";
            Integer code = (int) (Math.random() * 8999) + 1000;
            ListWaiting listWaiting = new ListWaiting(code);
            waitingList.add(listWaiting);
            send+=code;
            String _from = "TimeFStudy";
            String apikey = "FRLC1KQK71446PS85MFH8434D63P0LEB8A8EL3AS068LC31XYQS083B1N5PL24HE";
            String url = "http://smspilot.ru/api.php" +
                    "?send=" + UriEscape.escapeUriPath(send+code) +
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
            return user.getName();
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
