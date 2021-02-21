import com.UpYun;
import com.upyun.UpException;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 文件类空间的demo
 */
public class FileBucketDemo2 {

    // 运行前先设置好以下三个参数
    private static final String BUCKET_NAME = "myfiledata";
    private static final String OPERATOR_NAME = "op1";


    private static UpYun upyun = null;


    public static void main(String[] args) throws IOException, UpException {

       doUpload();

    }


    public static void doUpload() throws IOException, UpException {
        // 初始化空间
        upyun = new UpYun(BUCKET_NAME, OPERATOR_NAME, OPERATOR_PWD);


        testWriteFile();
    }


    /**
     * 上传文件
     *
     * @throws IOException
     */
    public static void testWriteFile() throws IOException, UpException {


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String day = format.format(new Date());



        upload(day + "-articles.json");
        upload(day + "-blogs.json");
        upload(day + "-books.json");
        upload(day + "-news.json");


    }

    private static void upload(String pathname) throws IOException, UpException {
        File txtFile = new File(pathname);
        upyun.writeFile("/data/" + pathname, txtFile);
    }


}
