package com.first.demo.task;

import com.first.demo.service.FundService;
import com.first.demo.service.impl.FundServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Logger;

@Component
public class DayService {
    private static final Logger logger = Logger.getLogger(String.valueOf(FundServiceImpl.class));
    @Autowired
    FundService fundService;
    //cron一共有7位，但是最后一位是年，可以留空，所以我们可以写6位：
    //第一位，表示秒，取值0-59
    //第二位，表示分，取值0-59
    //第三位，表示小时，取值0-23
    //第四位，日期天/日，取值1-31
    //第五位，日期月份，取值1-12
    //第六位，星期，取值1-7，星期一，星期二...，注：不是第1周，第二周的意思 另外：1表示星期天，2表示星期一。
    //第七位，年份，可以留空，取值1970-2099
    //cron中，还有一些特殊的符号，含义如下：
    //(*)星号：可以理解为每的意思，每秒，每分，每天，每月，每年...
    //(?)问号：问号只能出现在日期和星期这两个位置，表示这个位置的值不确定，每天3点执行，所以第六位星期的位置，我们是不需要关注的，就是不确定的值。同时：日期和星期是两个相互排斥的元素，通过问号来表明不指定值。比如，1月10日，比如是星期1，如果在星期的位置是另指定星期二，就前后冲突矛盾了。
    //(-)减号：表达一个范围，如在小时字段中使用“10-12”，则表示从10到12点，即10,11,12
    //(,)逗号：表达一个列表值，如在星期字段中使用“1,2,4”，则表示星期一，星期二，星期四
    //(/)斜杠：如：x/y，x是开始值，y是步长，比如在第一位（秒） 0/15就是，从0秒开始，每15秒，最后就是0，15，30，45，60    另：*/y，等同于0/y
    //附表：
    //"0 0 12 * * ?" 每天中午12点触发
    //"0 15 10 ? * *" 每天上午10:15触发
    //"0 15 10 * * ?" 每天上午10:15触发
    //"0 15 10 * * ? *" 每天上午10:15触发
    //"0 15 10 * * ? 2005" 2005年的每天上午10:15触发
    //"0 * 14 * * ?" 在每天下午2点到下午2:59期间的每1分钟触发
    //"0 0/5 14 * * ?" 在每天下午2点到下午2:55期间的每5分钟触发
    //"0 0/5 14,18 * * ?" 在每天下午2点到2:55期间和下午6点到6:55期间的每5分钟触发
    //"0 0-5 14 * * ?" 在每天下午2点到下午2:05期间的每1分钟触发
    //"0 10,44 14 ? 3 WED" 每年三月的星期三的下午2:10和2:44触发
    //"0 15 10 ? * MON-FRI" 周一至周五的上午10:15触发
    //"0 15 10 15 * ?" 每月15日上午10:15触发
    //"0 15 10 L * ?" 每月最后一日的上午10:15触发
    //"0 15 10 ? * 6L" 每月的最后一个星期五上午10:15触发
    //"0 15 10 ? * 6L 2002-2005" 2002年至2005年的每月的最后一个星期五上午10:15触发
    //"0 15 10 ? * 6#3" 每月的第三个星期五上午10:15触发
    //每隔5秒执行一次：*/5 * * * * ?
    //每隔1分钟执行一次：0 */1 * * * ?
    //每天23点执行一次：0 0 23 * * ?
    //每天凌晨1点执行一次：0 0 1 * * ?
    //每月1号凌晨1点执行一次：0 0 1 1 * ?
    //每月最后一天23点执行一次：0 0 23 L * ?
    //每周星期天凌晨1点实行一次：0 0 1 ? * L
    @Scheduled(cron = "* 0 0 * * ?")
    public void downList(){
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        logger.info("当前定时任务时间 downList ：" + formatter.format(new Date(System.currentTimeMillis())));
        fundService.getFundList();
    }
    @Scheduled(cron = "* 1 12,15 * * ?")
    public void downDayData(){
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        logger.info("当前定时任务时间 downDayData ：" + formatter.format(new Date(System.currentTimeMillis())));
        fundService.getFundDetail();
    }
    @Scheduled(cron = "* 0 * * * ?")
    public void downCheck(){
        SimpleDateFormat formatter = new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss");
        logger.info("当前定时任务时间 downCheck ：" + formatter.format(new Date(System.currentTimeMillis())));
    }
}
