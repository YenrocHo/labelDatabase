/**
 * projectName: labelDatabase
 * fileName: ResponseResult.java
 * packageName: com.fc.aden.util.dxm.result
 * date: 2019-09-22
 * copyright(c) 2017-2020 德慧公司
 */
package com.fc.aden.util.dxm.result;

/**
 * @version: V1.0
 * @author: DongXiaoMing
 * @className: ResponseResult
 * @packageName: com.fc.aden.util.dxm.result
 * @description: 返回实体对象
 * @data: 2019-09-22
 **/
public class ResponseResult {

    private int code;
    private String message;
    private String data;



    public ResponseResult setCodeMsg(ResponseCode rc) {
        this.code = rc.getCode();
        this.message = rc.getText();
        return this;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
