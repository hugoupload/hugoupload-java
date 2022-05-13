package com.hugocut.upload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hugocut.model.*;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.multipart.FilePart;
import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
import org.apache.commons.httpclient.methods.multipart.Part;
import org.apache.commons.httpclient.methods.multipart.StringPart;

import java.io.*;
import java.lang.annotation.Repeatable;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.hugocut.model.InitData;
//import org.apache.commons.httpclient.HttpClient;
//import org.apache.commons.httpclient.HttpException;
//import org.apache.commons.httpclient.methods.GetMethod;
//import org.apache.commons.httpclient.methods.PostMethod;
//import org.apache.commons.httpclient.methods.multipart.FilePart;
//import org.apache.commons.httpclient.methods.multipart.MultipartRequestEntity;
//import org.apache.commons.httpclient.methods.multipart.Part;
//import org.apache.commons.httpclient.methods.multipart.StringPart;
//import org.apache.commons.httpclient.params.HttpMethodParams;
//import org.apache.commons.httpclient.util.HttpURLConnection;


public class Upload {
    // 分片的大小
    private final long cutSize = 30 << 20;
    private String token = "";
    private String identifier = "";   // 必填
    private String user = "";   // 必填
    private String title = "";
    private String image = "";
    private String rule = "";   // 必填
    private String cat = "";   // 必填
    private String[] subcat;  // 必填
    private String actor = "";
    private String domain = "";
    private String filename = "";  // 必填
    private String newFileName = "";
    private int cutNum = 0;
    private long upload_id = 0;

    public void SetDomain(String domain) {
        this.domain = domain;
    }

    public void SetActor(String actor) {
        this.actor = actor;
    }

    public void SetCover(String image) {
        this.image = image;
    }


    public Upload(String token, String identifier, String user, String title,
                  String rule, String cat, String[] subcat, String filename) {
        this.token = token;
        this.identifier = identifier;

        this.user = user;
        this.title = title;
        this.rule = rule;
        this.cat = cat;
        this.subcat = subcat;
        this.filename = filename;

        //获取文件的后缀名 .jpg
        this.Valid();
    }

    public String GetSubcate() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this.subcat);
    }

    public boolean Valid() {
        if (this.filename == "") {
            throw new RuntimeException("filename can not empty");
        }
        if (this.rule == "") {
            throw new RuntimeException("rule can not empty");
        }
        if (this.title == "") {
            throw new RuntimeException("title can not empty");
        }
        if (this.user == "") {
            throw new RuntimeException("user can not empty");
        }
        if (this.identifier == "") {
            throw new RuntimeException("identifier can not empty");
        }
        if (this.cat == "") {
            throw new RuntimeException("cat can not empty");
        }
        if (this.subcat.length == 0) {
            throw new RuntimeException("subcat can not empty");
        }
        int lastIndexOf = filename.lastIndexOf(".");
        if (lastIndexOf == -1) {
            throw new RuntimeException("filename is error");
        }
        if (this.domain == "") {
            this.domain = "https://ddp28.com";
        }
        this.newFileName = identifier + filename.substring(lastIndexOf);
        return true;
    }

    private String sendPost(String urlParam, String params) {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlParam);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            // connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式<br>、　　　　　　//因为要登陆才可以执行请求，所以这里要带cookie的header
            connection.setRequestProperty("Token", this.token);
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            //一定要用BufferedReader 来接收响应， 使用字节来接收响应的方法是接收不到内容的
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(params);
            out.flush();
            out.close();
            // 读取响应
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            String res = "";
            while ((line = reader.readLine()) != null) {
                res += line;
            }
            reader.close();
            return res;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "error"; // 自定义错误信息
    }

    private String sendPost(String urlParam, String params, long upload_id) {
        BufferedReader reader = null;
        try {
            URL url = new URL(urlParam);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            // connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式<br>、　　　　　　//因为要登陆才可以执行请求，所以这里要带cookie的header
            connection.setRequestProperty("Token", this.token);
            connection.setRequestProperty("upload_id", Long.toString(upload_id));
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            //一定要用BufferedReader 来接收响应， 使用字节来接收响应的方法是接收不到内容的
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(params);
            out.flush();
            out.close();
            // 读取响应
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            String res = "";
            while ((line = reader.readLine()) != null) {
                res += line;
            }
            reader.close();
            return res;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "error"; // 自定义错误信息
    }

    private UploadResponse upload(int partNumber, byte[] b) throws IOException {
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(this.domain + "/upload");
        File f = new File(this.identifier + "_" + partNumber);
        FileOutputStream fos = new FileOutputStream(f);
        BufferedOutputStream bos = new BufferedOutputStream(fos);
        bos.write(b);
        Part[] parts = {
                new StringPart("user", this.user, "UTF-8"),
                new StringPart("uploadId", Long.toString(this.upload_id), "UTF-8"),
                new StringPart("partNumber", String.valueOf(partNumber), "UTF-8"),
                new FilePart("file", f)
        };

        MultipartRequestEntity entity = new MultipartRequestEntity(parts, postMethod.getParams());
        postMethod.setRequestEntity(entity);
        postMethod.setRequestHeader("Token", this.token);
        postMethod.setRequestHeader("upload_id", Long.toString(upload_id));
        postMethod.setRequestHeader("Content-Type", entity.getContentType());
        httpClient.executeMethod(postMethod);
        String result = postMethod.getResponseBodyAsString();
        postMethod.releaseConnection();
        bos.close();
        fos.close();
        f.delete();

        return UploadResponse.toObject(result);

    }


    private Response complete() throws IOException {
        HttpClient httpClient = new HttpClient();
        PostMethod postMethod = new PostMethod(this.domain + "/complete");

        ArrayList<Part> parts = new ArrayList<>();
        parts.add(new StringPart("user", this.user, "UTF-8"));
        parts.add(new StringPart("uploadId", Long.toString(this.upload_id), "UTF-8"));
        parts.add(new StringPart("identifier", this.identifier, "UTF-8"));
        parts.add(new StringPart("title", this.title, "UTF-8"));
        parts.add(new StringPart("rule", this.rule, "UTF-8"));
        parts.add(new StringPart("cat", this.cat, "UTF-8"));
        parts.add(new StringPart("actor", this.actor, "UTF-8"));

        parts.add(new StringPart("subcat", this.GetSubcate(), "UTF-8"));
        if (this.image != "") {
            parts.add(new FilePart("image", new File(this.image)));
        }
        Part[] ps = new Part[parts.size()];
        parts.toArray(ps);
        MultipartRequestEntity entity = new MultipartRequestEntity(ps, postMethod.getParams());
        postMethod.setRequestEntity(entity);
        postMethod.setRequestHeader("Token", this.token);
        postMethod.setRequestHeader("upload_id", Long.toString(upload_id));
        postMethod.setRequestHeader("Content-Type", entity.getContentType());
        httpClient.executeMethod(postMethod);
        String result = postMethod.getResponseBodyAsString();
        postMethod.releaseConnection();
        CompleteResponse cp = CompleteResponse.toObject(result);
        if (cp.getCode() == 2) {
            // 如果错误码是2， 那么需要重试一次upload
            Response r = uploadApi(cp.getData());
            if (r.getCode() == 0) {
                return this.complete();
            }
        }
        return CompleteResponse.toObject(result);
    }

    private Response initApi() throws JsonProcessingException {
        File f = new File(this.filename);
        long len = f.length() / cutSize;
        // 获取分片数
        long parts = (int) (f.length() % cutSize) == 0 ? len : len + 1;
        this.cutNum = (int) parts;
        // 组装 init 接口的参数
        InitData id = new InitData();
        id.setFileName(this.newFileName);
        id.setTotalSize(f.length());
        id.setTotalParts(this.cutNum);
        id.setUser(this.user);
        String res = this.sendPost(this.domain + "/init", id.toString());
        InitResponse r = InitResponse.toObject(res);

        this.upload_id = r.getData().getUploadId();
        return r;
    }

    private Response uploadApi() throws IOException {
        File f = new File(this.filename);
        InputStream in = new FileInputStream(f);
        Response result = new Response();
        for (int i = 0; i < this.cutNum; i++) {
            int bytelen = (int) this.cutSize;
            if (i == this.cutNum - 1) {
                if ((f.length() % cutSize) != 0) {
                    bytelen = (int) (f.length() % cutSize);
                }
            }
            byte[] r = new byte[bytelen];

            in.skip(bytelen);
            in.read(r);

            result = this.upload(i + 1, r);
            if (result.getCode() != 0) {
                return  result;
            }
        }

        return result;
    }

    private Response uploadApi(int[] miss) throws IOException {
        File f = new File(this.filename);
        InputStream in = new FileInputStream(f);
        String message = "ok";
        List m = Arrays.asList(miss);

        for (int i = 0; i < this.cutNum; i++) {
            if  (!m.contains(i)) {
                continue;
            }
            int bytelen = (int) this.cutSize;
            if (i == this.cutNum - 1) {
                if ((f.length() % cutSize) != 0) {
                    bytelen = (int) (f.length() % cutSize);
                }
            }
            byte[] r = new byte[bytelen];

            in.skip(bytelen);
            in.read(r);

            Response result = this.upload(i + 1, r);
            if (result.getCode() != 0) {
                return  result;
            }
            message = result.getMessage();
        }
        Response r = new Response();
        r.setCode(1);
        r.setMessage(message);
        return r;
    }


    private Response compelteApi() throws IOException {
        return this.complete();
    }

    public Response uploadVideo() throws IOException {
        Response r;
        r = this.initApi();
        if (r.getCode()!=0) {
            return r;
        }
        // upload  --------------  2
        r = this.uploadApi();
        if (r.getCode()!=0) {
            return r;
        }
        // complete   ----------------- 3
        return this.compelteApi();
    }

    public static void main(String[] args) throws IOException {
        String[] subcate = {"网红热点"};
        Upload upload = new Upload("9252b3ee5f7f9a07f652d4e75faa9a268a6f27a3", "xxxxxxxxxxxxxxxxxx11", "admin", "0601测试2333444",
                "videotest", "test", subcate, "C:\\Users\\cande\\Desktop\\ed6694d207e1bf83599ddc5eddee0268.mp4");
        // 设置其他环境
        upload.SetDomain("http://localhost:8888");
        Response r = upload.uploadVideo();
        System.out.println(r);

    }
}
