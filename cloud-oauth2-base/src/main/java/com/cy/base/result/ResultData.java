package com.cy.base.result;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * 自定义响应结构
 */
@Data
public class ResultData {

  // 响应业务状态
  private Integer code;

  // 响应消息
  private String message;

  // 响应中的数据
  private Object data;

  public ResultData() {
  }
  public ResultData(Object data) {
    this.code = 200;
    this.message = "OK";
    this.data = data;
  }
  public ResultData(String message, Object data) {
    this.code = 200;
    this.message = message;
    this.data = data;
  }

  public ResultData(Integer code, String message, Object data) {
    this.code = code;
    this.message = message;
    this.data = data;
  }

  public static ResultData ok() {
    return new ResultData(null);
  }
  public static ResultData ok(String message) {
    return new ResultData(message, null);
  }
  public static ResultData ok(Object data) {
    return new ResultData(data);
  }
  public static ResultData ok(String message, Object data) {
    return new ResultData(message, data);
  }

  public static ResultData build(Integer code, String message) {
    return new ResultData(code, message, null);
  }

  public static ResultData build(Integer code, String message, Object data) {
    return new ResultData(code, message, data);
  }

  public String toJsonString() {
    return JSON.toJSONString(this);
  }


  /**
   * JSON字符串转成 MengxueguResult 对象
   * @param json
   * @return
   */
  public static ResultData format(String json) {
    try {
      return JSON.parseObject(json, ResultData.class);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

}
