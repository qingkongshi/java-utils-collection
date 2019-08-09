package red.kea.javautilscollection.http_utils.restTemplate_utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName RestTemplateUtils
 * @Author KeA
 * @Date 2019/7/9 9:58
 * @Version 1.0
 */
@RestController
public class RestTemplateUtils {
    //TODO 关于RestTemplate使用比较少，以后用到了再来补充这个

    //注入
    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("/testRestTemplate")
    public Object faceInfo(String startTime,String endTime,Integer size ){
        String apiURL = "https://story.hhui.top/detail?id=666106231640";
        HttpHeaders headers = new HttpHeaders();
        //设置请求头，json格式
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> requestParam = new HashMap<>();

//        requestParam.put("startTime", startTime);
//        requestParam.put("endTime", endTime);
//        requestParam.put("size", size);

        HttpEntity<Map<String, Object>> request = new HttpEntity<Map<String, Object>>(requestParam, headers);

        ResponseEntity<String> entity = restTemplate.postForEntity(apiURL, request, String.class);

        String body = entity.getBody();

        return body;
    }

}
