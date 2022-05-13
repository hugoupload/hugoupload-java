### hugo upload sdk for java

```java
public static void main(String[] args) throws IOException {
        String[] subcate = {"aaa"};
        Upload upload = new Upload("dddd", "xxxxxxxxxxxxxxxxxx11", "admin", "0601测试2333444",
                "222", "33", subcate, "C:\\Users\\cande\\Desktop\\ed6694d207e1bf83599ddc5eddee0268.mp4");
        // 设置其他环境
        upload.SetDomain("http://localhost:8888");
        Response r = upload.uploadVideo();
        System.out.println(r);

    }
```