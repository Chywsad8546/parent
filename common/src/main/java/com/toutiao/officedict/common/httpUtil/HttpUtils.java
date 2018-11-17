package com.toutiao.officedict.common.httpUtil;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created with IntelliJ IDEA
 *
 * @Project :boot-demo
 * @Author : kewei@nash.work
 * @Date : 2017-09-27 下午9:14 星期三
 * @Version : v1.0
 **/
public class HttpUtils {
    private static Logger logger = LoggerFactory.getLogger(HttpUtils.class);
    private final static int SOCK_TIMEOUT = 5000;
    private final static int CONN_TIMEOUT = 10000;
    private static final int HTTP_SUCCESS = 200;
    private static final String CHARSET_UTF8 = "UTF-8";
    private static final String LOG_PREFIX = "[HttpUtils]";


    public static String get(String url) {
        return get(url, CHARSET_UTF8);
    }

    public static String get(String url, String encoding) {
        HttpGet method = new HttpGet(url);
        return executeMethod(method,encoding);
    }

    public static String get(String url, Map<String, ?> paramsMap) {
        return get(url,paramsMap,CHARSET_UTF8);
    }

    public static String get(String url, Map<String, ?> paramsMap,String encoding) {
        if (paramsMap != null) {
            Set<String> keySet = paramsMap.keySet();
            StringBuilder sb = new StringBuilder(keySet.size() * 8);
            sb.append("?");
            for (String key : keySet) {
                try {
                    String value = URLEncoder.encode(String.valueOf(paramsMap.get(key)), encoding);
                    sb.append(key).append("=").append(value).append("&");
                } catch (UnsupportedEncodingException e) {
                    logger.error(e.getMessage(), e);
                }
            }
            String params = sb.toString();
            url += params.substring(0, params.length() - 1);
        }
        HttpGet method = new HttpGet(url);
        return executeMethod(method,encoding);
    }

    public static String post(String url, Map<String, ?> paramsMap) {
        return post(url,paramsMap,CHARSET_UTF8);
    }

    public static String post(String url, Map<String, ?> paramsMap,String encoding) {
        HttpPost method = new HttpPost(url);
        List<NameValuePair> nvps = new ArrayList<>(paramsMap.size());
        Set<String> keySet = paramsMap.keySet();
        for (String key : keySet) {
            nvps.add(new BasicNameValuePair(key, String.valueOf(paramsMap.get(key))));
        }
        try {
            method.setEntity(new UrlEncodedFormEntity(nvps, encoding));
        } catch (UnsupportedEncodingException e) {
            logger.error(e.getMessage(), e);
        }
        return executeMethod(method,encoding);
    }

    public static String postXml(String url, String body) {
        return postXml(url, body, CHARSET_UTF8);
    }

    public static String postXml(String url, String body,String encoding) {
        HttpPost method = new HttpPost(url);
        method.setEntity(new StringEntity(body, ContentType.create("text/xml", encoding)));
        return executeMethod(method,encoding);
    }

    public static String postJson(String url, String body) {
        return postJson(url, body, CHARSET_UTF8);
    }

    public static String postJson(String url,String body,String encoding) {
        HttpPost method = new HttpPost(url);
        method.setEntity(new StringEntity(body, ContentType.create("application/json", encoding)));
        return executeMethod(method,encoding);
    }

    public static String postJson(String url,String body,String encoding, String header) {
        HttpPost method = new HttpPost(url);

        method.addHeader("Authorization", header);

        method.setEntity(new StringEntity(body, ContentType.create("application/json", encoding)));
        return executeMethod(method,encoding);
    }

    private static final String executeMethod(HttpRequestBase method, String encoding) {
        return executeMethod(method,encoding,CONN_TIMEOUT,SOCK_TIMEOUT);
    }

    /**
     * 执行http post请求
     * @param url 请求url
     * @param body 请求参数
     * @param encoding 编码方式
     * @param connectTimeout 连接超时时间 单位为毫秒,必传
     * @param socketTimeout 响应超时时间 单位为毫秒,必传
     * @return 响应结果
     */
    public static String postXml(String url, String body,String encoding,int connectTimeout,int socketTimeout) {
        HttpPost method = new HttpPost(url);
        method.setEntity(new StringEntity(body, ContentType.create("text/xml", encoding)));
        return executeMethod(method,encoding,connectTimeout,socketTimeout);
    }

    /**
     * 执行http请求
     * @param method
     * @param connectTimeout 连接超时时间 单位为毫秒
     * @param socketTimeout  响应超时时间 单位为毫秒
     * @param encoding 编码方式
     * @return 响应结果
     */
    private static final String executeMethod(HttpRequestBase method,String encoding,int connectTimeout,int socketTimeout) {
        CloseableHttpClient client = HttpClients.createDefault();
        RequestConfig
                config =
                RequestConfig.custom().setSocketTimeout(socketTimeout)
                        .setConnectTimeout(connectTimeout).build();
        method.setConfig(config);
        try {
            HttpResponse response = client.execute(method);
            if (HTTP_SUCCESS == response.getStatusLine().getStatusCode()) {
                return EntityUtils.toString(response.getEntity(), encoding);
            } else {
                logger.error("{}executeMethod返回失败,request={}, response={}",
                        LOG_PREFIX, method,EntityUtils.toString(response.getEntity(), encoding));
            }
        } catch (Exception e) {
            logger.warn("{}executeMethod异常,request={}, errorMsg={}",LOG_PREFIX, method,e.getMessage());
        } finally {
            method.releaseConnection();
            try {
                client.close();
            } catch (IOException e) {
                logger.error("{}连接关闭异常,errorMsg={}", LOG_PREFIX,e.getMessage());
            }
        }
        return null;
    }


    /**
     *  某小区房源列表接口http请求：
     */
    public static StringBuilder importPostHttpxiaoqu(String urlAddress, Integer id){
        URL url = null;
        StringBuilder sb = new StringBuilder(); //
        try {
            url = new URL(urlAddress);
            // 将url 以 open方法返回的urlConnection  连接强转为HttpURLConnection连接  (标识一个url所引用的远程对象连接)
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 此时cnnection只是为一个连接对象,待连接中

            // 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
            connection.setDoOutput(true);

            // 设置连接输入流为true
            connection.setDoInput(true);

            // 设置请求方式为post
            connection.setRequestMethod("POST");

            // post请求缓存设为false
            connection.setUseCaches(false);

            // 设置该HttpURLConnection实例是否自动执行重定向
            connection.setInstanceFollowRedirects(true);

            // 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
            // application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据
            // ;charset=utf-8 必须要，不然妙兜那边会出现乱码【★★★★★】
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            // 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
            connection.connect();

            // 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
            DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());

            String app_key = "conmmunityid="+ URLEncoder.encode(id.toString(), "utf-8");        // 已修改【改为错误数据，以免信息泄露】
            String agt_num = "&page="+ URLEncoder.encode("1", "utf-8");      // 已修改【改为错误数据，以免信息泄露】
            String page = "&pcount="+ URLEncoder.encode("15", "utf-8");      // 已修改【改为错误数据，以免信息泄露】
            // 格式 parm = aaa=111&bbb=222&ccc=333&ddd=444
            String parm = app_key+agt_num+page;

            // 将参数输出到连接
            dataout.writeBytes(parm);

            // 输出完成后刷新并关闭流
            dataout.flush();
            dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)

//            System.out.println(connection.getResponseCode());

            // 连接发起请求,处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;

            // 循环读取流,若不到结尾处
            while ((line = bf.readLine()) != null) {
//                sb.append(bf.readLine());
                sb.append(line).append(System.getProperty("line.separator"));
            }
            bf.close();    // 重要且易忽略步骤 (关闭流,切记!)
            connection.disconnect(); // 销毁连接

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }


    //麦田小区接口
    public static StringBuilder importPostHttpxiaoquMaitian(String urlAddress, String id){
        urlAddress+="?GardenID="+id+"&PS=100";
        URL url = null;
        StringBuilder sb = new StringBuilder(); //
        try {
            url = new URL(urlAddress);
            // 将url 以 open方法返回的urlConnection  连接强转为HttpURLConnection连接  (标识一个url所引用的远程对象连接)
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 此时cnnection只是为一个连接对象,待连接中

            // application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据
            // ;charset=utf-8 必须要，不然妙兜那边会出现乱码【★★★★★】

            connection.setRequestProperty("Authorization", "Basic ios_ad9a3d13a089df11ef95448e02d19e12");
            // 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
            connection.connect();

//            System.out.println(connection.getResponseCode());

            // 连接发起请求,处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;

            // 循环读取流,若不到结尾处
            while ((line = bf.readLine()) != null) {
//                sb.append(bf.readLine());
                sb.append(line).append(System.getProperty("line.separator"));
            }
            bf.close();    // 重要且易忽略步骤 (关闭流,切记!)
            connection.disconnect(); // 销毁连接

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }

    //麦田房源接口
    public static StringBuilder importPostHttpMaitianDetail(String urlAddress, String id){
        urlAddress+="?HouseCode="+id;
        URL url = null;
        StringBuilder sb = new StringBuilder(); //
        try {
            url = new URL(urlAddress);
            // 将url 以 open方法返回的urlConnection  连接强转为HttpURLConnection连接  (标识一个url所引用的远程对象连接)
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 此时cnnection只是为一个连接对象,待连接中

            // application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据
            // ;charset=utf-8 必须要，不然妙兜那边会出现乱码【★★★★★】

            connection.setRequestProperty("Authorization", "Basic ios_ad9a3d13a089df11ef95448e02d19e12");
            // 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
            connection.connect();

//            System.out.println(connection.getResponseCode());

            // 连接发起请求,处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;

            // 循环读取流,若不到结尾处
            while ((line = bf.readLine()) != null) {
//                sb.append(bf.readLine());
                sb.append(line).append(System.getProperty("line.separator"));
            }
            bf.close();    // 重要且易忽略步骤 (关闭流,切记!)
            connection.disconnect(); // 销毁连接

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }

    /**
     *  某房源详情接口：
     */
    public static StringBuilder importPostHttpHouseDetail(String urlAddress, Integer id){
        URL url = null;
        StringBuilder sb = new StringBuilder(); //
        try {
            url = new URL(urlAddress);
            // 将url 以 open方法返回的urlConnection  连接强转为HttpURLConnection连接  (标识一个url所引用的远程对象连接)
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 此时cnnection只是为一个连接对象,待连接中

            // 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
            connection.setDoOutput(true);

            // 设置连接输入流为true
            connection.setDoInput(true);

            // 设置请求方式为post
            connection.setRequestMethod("POST");

            // post请求缓存设为false
            connection.setUseCaches(false);

            // 设置该HttpURLConnection实例是否自动执行重定向
            connection.setInstanceFollowRedirects(true);

            // 设置请求头里面的各个属性 (以下为设置内容的类型,设置为经过urlEncoded编码过的from参数)
            // application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据
            // ;charset=utf-8 必须要，不然妙兜那边会出现乱码【★★★★★】
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=utf-8");

            // 建立连接 (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
            connection.connect();

            // 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
            DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());

            String app_key = "hid="+ URLEncoder.encode(id.toString(), "utf-8");        // 已修改【改为错误数据，以免信息泄露】
            // 格式 parm = aaa=111&bbb=222&ccc=333&ddd=444
            String parm = app_key;

            // 将参数输出到连接
            dataout.writeBytes(parm);

            // 输出完成后刷新并关闭流
            dataout.flush();
            dataout.close(); // 重要且易忽略步骤 (关闭流,切记!)


            // 连接发起请求,处理服务器响应  (从连接获取到输入流并包装为bufferedReader)
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;

            // 循环读取流,若不到结尾处
            while ((line = bf.readLine()) != null) {
//                sb.append(bf.readLine());
                sb.append(line).append(System.getProperty("line.separator"));
            }
            bf.close();    // 重要且易忽略步骤 (关闭流,切记!)
            connection.disconnect(); // 销毁连接

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }
}
