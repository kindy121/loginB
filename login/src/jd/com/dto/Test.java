package jd.com.dto;

import org.apache.commons.collections.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void  main(String args[]){
        decode("\\u77ED\\u4FE1\\u5185\\u5BB9");

    }
    /**
     * 把unicode编码转换为中文
     *
     * @param str
     * @return
     */
    public static void decode(String str) {
        String sg = "\\u";
        //int a = 0;
        List<String> list = new ArrayList<>();
        while (str.contains(sg)) {
            str = str.substring(2);
            String substring;
            if (str.contains(sg)) {
                substring = str.substring(0, str.indexOf(sg));
            } else {
                substring = str;
            }
            if (str.contains(sg)) {
                str = str.substring(str.indexOf(sg));
            }
            list.add(substring);
        }
        StringBuffer sb = new StringBuffer();
        if (!list.isEmpty()){
            for (String string : list) {
                sb.append((char) Integer.parseInt(string, 16));
            }
        }
        System.out.println(sb.toString());
    }

}
