package com.unind.qms.provider;

import com.unind.base.data.ApiResponseResult;
import com.unind.base.provider.BaseService;
import com.unind.base.provider.BusinessException;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class HttpClientService {
    /**
     * 日志处理
     */
    public final Logger logger = LoggerFactory.getLogger(this.getClass());

    public ApiResponseResult getHttpClient(String url) throws BusinessException {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        try {
            httpclient = HttpClients.createDefault();
            // 创建httpget.
            //"http://nanbo.f3322.net:8165/openapi/v1/prod/getitemandvendor"
            HttpGet httpget = new HttpGet(url);
            // 执行get请求.
            response = httpclient.execute(httpget);
            // 获取响应实体
            HttpEntity entity = response.getEntity();
            // 打印响应状态
//            System.out.println(response.getStatusLine().getStatusCode());

            if (entity != null&&response.getStatusLine().getStatusCode() == 200) {
                //获取响应内容
                String entityStr = EntityUtils.toString(entity).trim();
                return ApiResponseResult.success().data(entityStr);
            }
        }catch (Exception e) {
            logger.error("get remote interface data exception", e);
        }finally{
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ApiResponseResult.failure("获取远程数据失败");
    }

    /**
     * post方式提交json代码
     * @throws Exception
     */
    public static void postJson() throws Exception{
        //创建默认的httpClient实例.
        CloseableHttpClient httpclient = null;
        //接收响应结果
        CloseableHttpResponse response = null;
        try {
            //创建httppost
            httpclient = HttpClients.createDefault();
            String url ="http://192.168.16.36:8081/goSearch/gosuncn/deleteDocs.htm";
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader(HTTP.CONTENT_TYPE,"application/x-www-form-urlencoded");
            //参数
            String json ="{'ids':['html1','html2']}";
            StringEntity se = new StringEntity(json);
            se.setContentEncoding("UTF-8");
            se.setContentType("application/json");//发送json需要设置contentType
            httpPost.setEntity(se);
            response = httpclient.execute(httpPost);
            //解析返结果
            HttpEntity entity = response.getEntity();
            if(entity != null){
                String resStr = EntityUtils.toString(entity, "UTF-8");
                System.out.println(resStr);
            }
        } catch (Exception e) {
            throw e;
        }finally{
            httpclient.close();
            response.close();
        }
    }

    /**
     * post方式提交表单
     * @throws BusinessException
     */
    public ApiResponseResult postForm(String url, List<NameValuePair> formparams) throws BusinessException  {
        // 创建默认的httpClient实例.
        CloseableHttpClient httpclient = null;
        //发送请求
        CloseableHttpResponse response = null;
        try {
            httpclient = HttpClients.createDefault();
            // 创建httppost
//            String url= "http://localhost:8080/search/ajx/user.htm";
            HttpPost httppost = new HttpPost(url);
            // 创建参数队列
//            List<NameValuePair> formparams = new ArrayList<NameValuePair>();
//            formparams.add(new BasicNameValuePair("username", "admin"));
//            formparams.add(new BasicNameValuePair("password", "123456"));
            //参数转码
            UrlEncodedFormEntity uefEntity = new UrlEncodedFormEntity(formparams, "UTF-8");
            httppost.setEntity(uefEntity);
            response = httpclient.execute(httppost);
            HttpEntity entity = response.getEntity();
//            if (entity != null) {
//                System.out.println(EntityUtils.toString(entity, "UTF-8"));
//            }
            if (entity != null&&response.getStatusLine().getStatusCode() == 200) {
                //获取响应内容
                String entityStr = EntityUtils.toString(entity, "UTF-8").trim();
                return ApiResponseResult.success().data(entityStr);
            }
            //释放连接
        } catch (Exception e) {
            logger.error("post remote interface data exception", e);
        }finally{
            try {
                httpclient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ApiResponseResult.failure("提交POST远程数据失败");
    }

//    public static void main(String[] args) {
//        HttpClientService httpClientService = new HttpClientService();
//        try {
//            httpClientService.getHttpClient("http://nanbo.f3322.net:8165/openapi/v1/prod/getitemandvendor");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
