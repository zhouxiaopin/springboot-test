package cn.sk.springdataredistest.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * 配置redis消息的监听器：
 *  获取redis中的消息并处理
 */
@Component
public class RedisMessageListener implements MessageListener {

    /**
     * 处理消息
     * @param message  完整的消息（频道消息，以及消息的具体内容）
     * @param pattern 获取的频道消息
     */
    @Override
    public void onMessage(Message message, @Nullable byte[] pattern) {
        System.out.println("从channel为："+new String(message.getChannel())+"获取了一条新的消息，消息内容为："
                + new String(message.getBody()));
    }
}
