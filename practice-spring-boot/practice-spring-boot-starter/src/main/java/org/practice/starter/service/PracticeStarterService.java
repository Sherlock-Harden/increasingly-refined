package org.practice.starter.service;

import com.baidubce.http.ApiExplorerClient;
import com.baidubce.http.AppSigner;
import com.baidubce.http.HttpMethodName;
import com.baidubce.model.ApiExplorerRequest;
import com.baidubce.model.ApiExplorerResponse;
import lombok.Data;
import org.practice.starter.config.PracticeStarterProperties;

/**
 * @author sherlock[q541458352@126.com]
 * @since 2020/11/24 20:55
 **/
@Data
public class PracticeStarterService {

  private PracticeStarterProperties practiceStarterProperties;

  public static void main(String[] args) {
    String path = "http://gsd.api.bdymkt.comhttp://gwgp-g8eennmvmcz.n.bdcloudapi.com/sms";
    ApiExplorerRequest request = new ApiExplorerRequest(HttpMethodName.POST, path);
    request.setCredentials("abe59a6ee58e41af992469e1415970a7", "9ba1ce7b55e942cd84d5d730dfe3dcf9");

    request.addHeaderParameter("Content-Type", "application/json;charset=UTF-8");

    request.addQueryParameter("mobile", "13701300009");

    ApiExplorerClient client = new ApiExplorerClient(new AppSigner());

    try {
      ApiExplorerResponse response = client.sendRequest(request);
      // 返回结果格式为Json字符串
      System.out.println(response.getResult());
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public String sout() {
    return practiceStarterProperties.toString();
  }

}
