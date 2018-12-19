package com.example.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

@Controller
@RequestMapping("/test")
public class TestController {

	
	@RequestMapping(value = "/t1", method = RequestMethod.GET)
	@ResponseBody
	public String t1(Model model) {
		
		for(int j = 5; j <= 18; j++) {
			
			Connection conn = null;
			PreparedStatement pst = null;
			try {
				
				Class.forName("oracle.jdbc.driver.OracleDriver");// 指定连接类型
				conn = DriverManager.getConnection("jdbc:oracle:thin:@zyd.houbank.net:10087:hbdb", "usr_r_chenxgsp",
						"Usr_R_ChenxGsp%$98!");// 获取连接
				final String sql = "select SEND_MESSAGE, RES_ORDERID FROM HBHX.T_EXTRACT_APPLY WHERE BID_ID in (select id from HBBL.LOAN WHERE LOAN_APPLY_NO = ('";
				
				String execSql = null;
				
				int i = 0;
				
				FileReader fileReader = new FileReader("D:\\data\\loan" + j + ".txt");
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				
				FileWriter fileWriter = new FileWriter("D:\\data\\loan_result" + j + ".txt");
				BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
				
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					
					i ++;
					
					System.out.println(line);
					execSql = sql + line + "'))";
					
					String channelKey = null;
					String orderNo = null;
					String reqOrderId = null;
					
					pst = conn.prepareStatement(execSql);
					ResultSet rs = pst.executeQuery();
					while (rs.next()) {
						try {
							String rs1 = rs.getString(1);
							
							System.out.println(rs1);
							
							String rs2 = rs.getString(2);
							
							
							try {
								JSONObject jsonObject = JSON.parseObject(rs1);
								if(jsonObject != null) {
									channelKey = jsonObject.getString("channelKey") == null ? "" : jsonObject.getString("channelKey");
									orderNo = jsonObject.getString("orderNo") == null ? "" : jsonObject.getString("orderNo");
								}
							} catch(Exception e1) {
								e1.printStackTrace();
							}
							
							
							reqOrderId = rs2;
							
						} catch(Exception e) {
							e.printStackTrace();
						}
					}
					rs.close();
					
					execSql = null;
					channelKey = channelKey == null ? "" : channelKey;
					orderNo = orderNo == null ? "" : orderNo;
					reqOrderId = reqOrderId == null ? "" : reqOrderId;
					
					String result = line + "," + channelKey + "," + orderNo + "," + reqOrderId + "\n";
					
					System.out.println(result);
					
					bufferedWriter.write(result);
					
					System.out.println("第" + i + "条数据已处理完成.");
					
			    }
				bufferedWriter.flush();
				bufferedWriter.close();

				bufferedReader.close();

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (null != conn) {
						conn.close();
					}
					if (null != pst) {
						pst.close();
					}
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		
		
		
		return "OK!";
	}
	

}