package com.first.demo.service.impl;

import com.first.demo.service.TestService;
import org.hibernate.annotations.common.util.impl.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.ResourceHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.converter.support.AllEncompassingFormHttpMessageConverter;
import org.springframework.http.converter.xml.SourceHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class TestServiceImpl implements TestService {
    private static Logger logger = Logger.getLogger(String.valueOf(TestServiceImpl.class));
    @Override
    public String getData() {
        RestTemplate restTemplate = new RestTemplate(
                new HttpComponentsClientHttpRequestFactory()); // 使用HttpClient，支持GZIP
        restTemplate.getMessageConverters().set(1,
                new StringHttpMessageConverter(StandardCharsets.UTF_8)); // 支持中文编码

        //天气接口  测试
//        String uri="http://wthrcdn.etouch.cn/weather_mini?city="+"深圳";
//        HttpHeaders headers = new HttpHeaders();
//        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//        headers.setContentType(type);
////        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        HttpEntity<String> entity = new HttpEntity<String>(headers);
//        String strbody = restTemplate.exchange(uri, HttpMethod.GET, entity,String.class).getBody();
//        logger.info("getData uri:" + uri);
//        logger.info("getData:" + strbody);
//        return strbody;

        //列表
        //var r = [["000001","HXCZHH","华夏成长混合","混合型","HUAXIACHENGZHANGHUNHE"],["000002","HXCZHH","华夏成长混合(后端)","混合型","HUAXIACHENGZHANGHUNHE"],["000003","ZHKZZZQA","中海可转债债券A","债券型","ZHONGHAIKEZHUANZHAIZHAIQUANA"],["000004","ZHKZZZQC","中海可转债债券C","债券型","ZHONGHAIKEZHUANZHAIZHAIQUANC"],["000005","JSZQXYDQZQ","嘉实增强信用定期债券","定开债券","JIASHIZENGQIANGXINYONGDINGQIZHAIQUAN"],["000006","XBLDLHCZHHA","西部利得量化成长混合A","混合型","XIBULIDELIANGHUACHENGZHANGHUNHEA"],["000008","JSZZ500ETFLJA","嘉实中证500ETF联接A","联接基金","JIASHIZHONGZHENG500ETFLIANJIEA"],["000009","YFDTTLCHBA","易方达天天理财货币A","货币型","YIFANGDATIANTIANLICAIHUOBIA"],["000010","YFDTTLCHBB","易方达天天理财货币B","货币型","YIFANGDATIANTIANLICAIHUOBIB"],["000011","HXDPJXHH","华夏大盘精选混合","混合型","HUAXIADAPANJINGXUANHUNHE"],["000012","HXDPJXHH","华夏大盘精选混合(后端)","混合型","HUAXIADAPANJINGXUANHUNHE"],["000013","YFDTTLCHBR","易方达天天理财货币R","货币型","YIFANGDATIANTIANLICAIHUOBIR"],["000014","HXJLZQ","华夏聚利债券","债券型","HUAXIAJULIZHAIQUAN"],["000015","HXCZZQA","华夏纯债债券A","债券型","HUAXIACHUNZHAIZHAIQUANA"],["000016","HXCZZQC","华夏纯债债券C","债券型","HUAXIACHUNZHAIZHAIQUANC"],["000017","CTKCXHH","财通可持续混合","混合型","CAITONGKECHIXUHUNHE"],["000020","JSCCPZTZHH","景顺长城品质投资混合","混合型","JINGSHUNCHANGCHENGPINZHITOUZIHUNHE"],["000021","HXYSZZHH","华夏优势增长混合","混合型","HUAXIAYOUSHIZENGZHANGHUNHE"],["000024","DMSLZQZQA","大摩双利增强债券A","债券型","DAMOSHUANGLIZENGQIANGZHAIQUANA"],["000025","DMSLZQZQC","大摩双利增强债券C","债券型","DAMOSHUANGLIZENGQIANGZHAIQUANC"],["000028","HFAXZQ","华富安鑫债券","债券型","HUAFUANXINZHAIQUAN"],["000029","FGHGCLLHPZHH","富国宏观策略灵活配置混合","混合型","FUGUOHONGGUANCELUELINGHUOPEIZHIHUNHE"],["000030","CCHXYXHH","长城核心优选混合","混合型","CHANGCHENGHEXINYOUXUANHUNHE"],["000031","HXFXHH","华夏复兴混合","混合型","HUAXIAFUXINGHUNHE"],["000032","YFDXYZZQA","易方达信用债债券A","债券型","YIFANGDAXINYONGZHAIZHAIQUANA"],["000033","YFDXYZZQC","易方达信用债债券C","债券型","YIFANGDAXINYONGZHAIZHAIQUANC"],["000037","GFJNZQ","广发景宁债券","债券型","GUANGFAJINGNINGZHAIQUAN"],["000039","NYGZZHH","农银高增长混合","混合型","NONGYINGAOZENGZHANGHUNHE"],["000041","HXQQGPQDII","华夏全球股票(QDII)","QDII","HUAXIAQUANQIUGUPIAOQDII"],["000042","ZZCTKCXFZ100ZSA","中证财通可持续发展100指数A","股票指数","ZHONGZHENGCAITONGKECHIXUFAZHAN100ZHISHUA"],["000043","JSMGCZGPRMB","嘉实美国成长股票人民币","QDII","JIASHIMEIGUOCHENGZHANGGUPIAORENMINBI"],["000044","JSMGCZGPMYXH","嘉实美国成长股票美元现汇","QDII","JIASHIMEIGUOCHENGZHANGGUPIAOMEIYUANXIANHUI"],["000045","GYCYZZQA","工银产业债债券A","债券型","GONGYINCHANYEZHAIZHAIQUANA"],["000046","GYCYZZQB","工银产业债债券B","债券型","GONGYINCHANYEZHAIZHAIQUANB"],["000047","HXSZZQA","华夏双债债券A","债券型","HUAXIASHUANGZHAIZHAIQUANA"],["000048","HXSZZQC","华夏双债债券C","债券型","HUAXIASHUANGZHAIZHAIQUANC"],["000049","ZYBPQQZYDQZZS","中银标普全球资源等权重指数","QDII-指数","ZHONGYINBIAOPUQUANQIUZIYUANDENGQUANZHONGZHISHU"],["000051","HXHS300ETFLJA","华夏沪深300ETF联接A","联接基金","HUAXIAHUSHEN300ETFLIANJIEA"],["000053","PHYCYNDKZQ","鹏华永诚一年定开债券","定开债券","PENGHUAYONGCHENGYINIANDINGKAIZHAIQUAN"],["000054","PHSZZLZQ","鹏华双债增利债券","债券型","PENGHUASHUANGZHAIZENGLIZHAIQUAN"],["000055","GFNSDK100MYXHA","广发纳斯达克100美元现汇A","QDII-指数","GUANGFANASIDAKE100MEIYUANXIANHUIA"],["000056","JXXFSJHH","建信消费升级混合","混合型","JIANXINXIAOFEISHENGJIHUNHE"],["000057","ZYXFZTHH","中银消费主题混合","混合型","ZHONGYINXIAOFEIZHUTIHUNHE"],["000058","GLAATLHPZHH","国联安安泰灵活配置混合","混合型","GUOLIANANANTAILINGHUOPEIZHIHUNHE"],["000059","GLAZZYY100A","国联安中证医药100A","股票指数","GUOLIANANZHONGZHENGYIYAO100A"],["000061","HXSSHH","华夏盛世混合","混合型","HUAXIASHENGSHIHUNHE"],["000063","CSDZXXZTLHPZHH","长盛电子信息主题灵活配置混合","混合型","CHANGSHENGDIANZIXINXIZHUTILINGHUOPEIZHIHUNHE"],["000064","DM18GYDKZ","大摩18个月定开债","定开债券","DAMO18GEYUEDINGKAIZHAI"],["000065","GFJDQDHH","国富焦点驱动混合","混合型","GUOFUJIAODIANQUDONGHUNHE"],["000066","NAHXHH","诺安鸿鑫混合","混合型","NUOANHONGXINHUNHE"],["000067","MSJYZZYXA","民生加银转债优选A","债券型","MINSHENGJIAYINZHUANZHAIYOUXUANA"],["000068","MSJYZZYXC","民生加银转债优选C","债券型","MINSHENGJIAYINZHUANZHAIYOUXUANC"],["000069","GTRYZGDJZQA","国投瑞银中高等级债券A","债券型","GUOTOURUIYINZHONGGAODENGJIZHAIQUANA"],["000070","GTRYZGDJZQC","国投瑞银中高等级债券C","债券型","GUOTOURUIYINZHONGGAODENGJIZHAIQUANC"],["000071","HXHSETFLJA","华夏恒生ETF联接A","QDII-指数","HUAXIAHENGSHENGETFLIANJIEA"],["000072","HAWJHBHH","华安稳健回报混合","混合型","HUAANWENJIANHUIBAOHUNHE"],["000073","STMGCZDLHH","上投摩根成长动力混合","混合型","SHANGTOUMOGENCHENGZHANGDONGLIHUNHE"],["000074","GYXYCZYNDKZA","工银信用纯债一年定开债A","定开债券","GONGYINXINYONGCHUNZHAIYINIANDINGKAIZHAIA"],["000075","HXHSETFLJXH","华夏恒生ETF联接现汇","QDII-指数","HUAXIAHENGSHENGETFLIANJIEXIANHUI"],["000076","HXHSETFLJXC","华夏恒生ETF联接现钞","QDII-指数","HUAXIAHENGSHENGETFLIANJIEXIANCHAO"],["000077","GYXYCZYNDKZC","工银信用纯债一年定开债C","定开债券","GONGYINXINYONGCHUNZHAIYINIANDINGKAIZHAIC"],["000078","GYXYCZLNDKZA","工银信用纯债两年定开债A","定开债券","GONGYINXINYONGCHUNZHAILIANGNIANDINGKAIZHAIA"],["000079","GYXYCZLNDKZC","工银信用纯债两年定开债C","定开债券","GONGYINXINYONGCHUNZHAILIANGNIANDINGKAIZHAIC"],["000080","TZKZZZQZQA","天治可转债增强债券A","债券型","TIANZHIKEZHUANZHAIZENGQIANGZHAIQUANA"],["000081","TZKZZZQZQC","天治可转债增强债券C","债券型","TIANZHIKEZHUANZHAIZENGQIANGZHAIQUANC"],["000082","JSYJAEFGP","嘉实研究阿尔法股票","股票型","JIASHIYANJIUAERFAGUPIAO"],["000083","HTFXFHYHH","汇添富消费行业混合","混合型","HUITIANFUXIAOFEIHANGYEHUNHE"],["000084","BSAYZQA","博时安盈债券A","债券型","BOSHIANYINGZHAIQUANA"],["000085","BSAYZQC","博时安盈债券C","债券型","BOSHIANYINGZHAIQUANC"],["000086","NFWL1NDKZA","南方稳利1年定开债A","定开债券","NANFANGWENLI1NIANDINGKAIZHAIA"],["000087","JSZZJBGZETFLJA","嘉实中证金边国债ETF联接A","联接基金","JIASHIZHONGZHENGJINBIANGUOZHAIETFLIANJIEA"],["000088","JSZZJBGZETFLJC","嘉实中证金边国债ETF联接C","联接基金","JIASHIZHONGZHENGJINBIANGUOZHAIETFLIANJIEC"],["000089","MSJYGDJXYZC","民生加银高等级信用债C","债券型","MINSHENGJIAYINGAODENGJIXINYONGZHAIC"],["000090","MSJYGDJXYZA","民生加银高等级信用债A","债券型","MINSHENGJIAYINGAODENGJIXINYONGZHAIA"],["000103","GTZGQYJWGSYZ","国泰中国企业境外高收益债","QDII","GUOTAIZHONGGUOQIYEJINGWAIGAOSHOUYIZHAI"],["000104","HCWLWJTLZQA","华宸未来稳健添利债券A","债券型","HUACHENWEILAIWENJIANTIANLIZHAIQUANA"],["000105","JXAXHBZQA","建信安心回报债券A","定开债券","JIANXINANXINHUIBAOZHAIQUANA"],["000106","JXAXHBZQC","建信安心回报债券C","定开债券","JIANXINANXINHUIBAOZHAIQUANC"],["000107","FGWJZQZQAB","富国稳健增强债券A/B","债券型","FUGUOWENJIANZENGQIANGZHAIQUANAB"],["000108","FGWJZQZQAB","富国稳健增强债券A/B(后端)","债券型","FUGUOWENJIANZENGQIANGZHAIQUANAB"],["000109","FGWJZQZQC","富国稳健增强债券C","债券型","FUGUOWENJIANZENGQIANGZHAIQUANC"],["000110","JYYAHHA","金鹰元安混合A","混合型","JINYINGYUANANHUNHEA"],["000111","YFDCZ1NDKZA","易方达纯债1年定开债A","定开债券","YIFANGDACHUNZHAI1NIANDINGKAIZHAIA"],["000112","YFDCZ1NDKZC","易方达纯债1年定开债C","定开债券","YIFANGDACHUNZHAI1NIANDINGKAIZHAIC"],["000113","JSRYBDQZQAB","嘉实如意宝定期债券A/B","定开债券","JIASHIRUYIBAODINGQIZHAIQUANAB"],["000114","JSRYBDQZQAB","嘉实如意宝定期债券A/B(后端)","定开债券","JIASHIRUYIBAODINGQIZHAIQUANAB"],["000115","JSRYBDQZQC","嘉实如意宝定期债券C","定开债券","JIASHIRUYIBAODINGQIZHAIQUANC"],["000116","JSFYCZDQZQ","嘉实丰益纯债定期债券","定开债券","JIASHIFENGYICHUNZHAIDINGQIZHAIQUAN"],["000117","GFLDPZHH","广发轮动配置混合","混合型","GUANGFALUNDONGPEIZHIHUNHE"],["000118","GFJXZQA","广发聚鑫债券A","债券型","GUANGFAJUXI

        String uri = "http://fund.eastmoney.com/js/fundcode_search.js";
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
//        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        String strbody = restTemplate.exchange(uri, HttpMethod.GET, entity,String.class).getBody();
        logger.info("getData uri:" + uri);
        logger.info("getData:" + strbody);
        return strbody;

        //某个数据 dwjz 单位净值 gsz估值？ gszzl涨跌幅？
        //getData uri:http://fundgz.1234567.com.cn/js/005827.js
        //2021-02-26 18:16:46.103  INFO 16908 --- [nio-8080-exec-1] c.f.demo.service.impl.TestServiceImpl    :
        //getData:jsonpgz({"fundcode":"005827","name":"易方达蓝筹精选混合","jzrq":"2021-02-25","dwjz":"3.1122","gsz":"3.0182","gszzl":"-3.02","gztime":"2021-02-26 15:00"});
//        String uri = "http://fundgz.1234567.com.cn/js/" + "005827" + ".js";
//        HttpHeaders headers = new HttpHeaders();
//        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
//        headers.setContentType(type);
////        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        HttpEntity<String> entity = new HttpEntity<String>(headers);
//        String strbody = restTemplate.exchange(uri, HttpMethod.GET, entity,String.class).getBody();
//        logger.info("getData uri:" + uri);
//        logger.info("getData:" + strbody);
//        return strbody;
    }
}
