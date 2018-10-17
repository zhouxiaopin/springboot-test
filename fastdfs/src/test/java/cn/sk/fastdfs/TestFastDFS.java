package cn.sk.fastdfs;


import org.csource.common.NameValuePair;
import org.csource.fastdfs.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestFastDFS {

    @Test
    public void testUpload() {
        try {
            //加载fastdfs的配置文件
            String conf_filename = "properties/fastdfs-client.properties";
            ClientGlobal.initByProperties(conf_filename);
            System.out.println("network_timeout=" + ClientGlobal.g_network_timeout + "ms");
            System.out.println("charset=" + ClientGlobal.g_charset);


            //创建一个Tracker的客户端
            TrackerClient tracker = new TrackerClient();
            TrackerServer trackerServer = tracker.getConnection();
            StorageServer storageServer = null;

            //定义一个Storage的客户端
            StorageClient1 client = new StorageClient1(trackerServer, storageServer);

            String local_filename = "station-1-small.png";

            //文件元信息
            NameValuePair[] metaList = new NameValuePair[1];
            metaList[0] = new NameValuePair("fileName", local_filename);
            String fileId = client.upload_file1("static/images/"+local_filename, "png", metaList);
            System.out.println("upload success. file id is: " + fileId);

            int i = 0;
            while (i++ < 10) {
                byte[] result = client.download_file1(fileId);
                System.out.println(i + ", download result is: " + result.length);
            }

            trackerServer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}