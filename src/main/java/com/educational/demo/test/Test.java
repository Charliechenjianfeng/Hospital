package com.educational.demo.test;


import net.sf.json.JSONObject;

/**
 * @Author: Mångata
 * @Email:1787506199a@gmail.com
 * @create 2021-01-31 20:14
 */
public class Test {
    public static void main(String[] args) {
        String jsonStr= "{\"param\":\"sdhfjsdhf\",\"paramRel\":\"qwjqewq\",\"paramType\":\"dsjf\"}";
       // JSONObject jsonObject = JSONObject.fromObject(jsonStr);
        TestVO vo = new TestVO();

        vo = (TestVO) net.sf.json.JSONObject.toBean(JSONObject.fromObject(jsonStr), TestVO.class);
        System.out.println(vo);
        vo.setParam("{\"hsdfjhsd\":\"sdhfjsdhf\",\"jshdfjsdhf\":\"qwjqewq\"}");
        vo.setParamRel("{\"snfgskd\":\"skdhfskd\",\"sdfhskdf\":\"fsdnfksd\"}");
        vo.setParamType("{\"snfgskd\":\"skdhfskd\",\"sdfhskdf\":\"fsdnfksd\"}");
        JSONObject json = JSONObject.fromObject(vo);
        System.out.println(json.toString());
    }
}
