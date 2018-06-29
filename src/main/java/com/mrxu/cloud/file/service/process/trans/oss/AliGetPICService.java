package com.mrxu.cloud.file.service.process.trans.oss;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
//import com.aliyun.oss.common.utils.DateUtil;
import com.mrxu.cloud.common.config.Constants;
import com.mrxu.cloud.common.enums.MrxuExceptionEnums;
import com.mrxu.cloud.common.exception.MrxuException;
import com.mrxu.cloud.common.util.DateUtil;
import com.mrxu.cloud.file.config.AliyunOSSConfig;
import com.mrxu.cloud.file.domain.trans.TransCodingDTO;
import com.mrxu.cloud.file.enums.TransTypeEnum;
import com.mrxu.cloud.file.util.OSSKeyGenerator;
import com.sun.jndi.toolkit.url.UrlUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.crypto.dsig.SignatureMethod;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service("aliGetpic")
public class AliGetPICService implements ITransCodeService {
	@Override
	public TransCodingDTO transCode(String file, String attName) {
		return null;
	}

	/*Logger logger = Logger.getLogger(AliGetPICService.class);


	@Autowired
	private AliyunOSSConfig aliyunOSSConfig;

	@Autowired
	private OSSKeyGenerator oSSKeyGenerator;

	@Override
	public TransCodingDTO transCode(String objectName, String attName) {
		TransCodingDTO transCodingDTO;
		try {
			JSONObject inputJson = new JSONObject(); // 作业输入，JSON对象
			inputJson.put("Bucket", aliyunOSSConfig.getOss_bucket_temp());
			inputJson.put("Location", aliyunOSSConfig.getOss_location());
			inputJson.put("Object", objectName);

			JSONObject snapshotConfigJson = new JSONObject(); // 截图配置，Json对象
			JSONObject outputFileJson = new JSONObject();
			outputFileJson.put("Bucket", aliyunOSSConfig.getOss_bucket());
			outputFileJson.put("Location", aliyunOSSConfig.getOss_location());
			String outputFileKey = oSSKeyGenerator.genKey("jpg"); // 获取上传文件名
			outputFileJson.put("Object", outputFileKey);

			snapshotConfigJson.put("OutputFile", outputFileJson);
			snapshotConfigJson.put("Time", "100"); // 从10毫秒开始

			// 组装截图请求 URL
			String snapshotJobUrl = assembleSnapshotJobUrl(inputJson.toString(), snapshotConfigJson.toString(),
					aliyunOSSConfig.getOss_accessKey(), aliyunOSSConfig.getOss_secretKey());
			System.out.println("snapshotJobUrl-------->:" + snapshotJobUrl);

			String responseData = HttpUtil.get(snapshotJobUrl); // 发起请求

			JSONObject responseDataJson = JSON.parseObject(responseData);
			JSONObject SnapshotJobJson = responseDataJson.getJSONObject("SnapshotJob");
			System.out.println("responseData:" + responseData);

			if ("Fail".equals(SnapshotJobJson.getString("State"))) {
				throw new Exception("Your request is fail,the reason：" + SnapshotJobJson.getString("Message"));
			}

			// 阿里云文件资源地址
			String ossUrl = "https://" + aliyunOSSConfig.getOss_bucket() + "." + aliyunOSSConfig.getOss_endpoint() + "/"
					+ outputFileKey;
			transCodingDTO = new TransCodingDTO();
			transCodingDTO.setType(TransTypeEnum.Thumbnail.getItemValue());
			transCodingDTO.setTransCodingUrl(ossUrl);
		} catch (Exception e) {
			logger.error(e);
			throw new MrxuException(MrxuExceptionEnums.RC_SPC_FILE_UPLOAD_ERROR.value(),
					MrxuExceptionEnums.RC_SPC_FILE_UPLOAD_ERROR.getMessage());
		}
		return transCodingDTO;
	}

	*//**
	 * 组装截图请求URL
	 * 
	 * @param input
	 * @param snapshotConfig
	 * @param accessKeyId
	 * @param accessKeySecret
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 *//*
	private String assembleSnapshotJobUrl(String input, String snapshotConfig, String accessKeyId,
			String accessKeySecret) throws Exception {

		final String HTTP_METHOD = "GET";
		Map<String, String> parameterMap = new HashMap<>();

		// 加入请求公共参数
		parameterMap.put("Action", Constants.SNAPSHOTJOB_ACTION);
		parameterMap.put("Version", "2014-06-18");
		parameterMap.put("AccessKeyId", accessKeyId); // 此处请替换成您自己的AccessKeyId
		// parameterMap.put("Timestamp",
		// "2015-05-14T09:03:45Z");//此处将时间戳固定只是测试需要，这样此示例中生成的签名值就不会变，方便您对比验证，可变时间戳的生成需要用下边这句替换
		parameterMap.put("Timestamp", DateUtil.formatIso8601Date(new Date()));
		parameterMap.put("SignatureMethod", "HMAC-SHA1");
		parameterMap.put("SignatureVersion", "1.0");
		// parameterMap.put("SignatureNonce",
		// "4902260a-516a-4b6a-a455-45b653cf6150");//此处将唯一随机数固定只是测试需要，这样此示例中生成的签名值就不会变，方便您对比验证，可变唯一随机数的生成需要用下边这句替换
		parameterMap.put("SignatureNonce", UUID.randomUUID().toString());
		parameterMap.put("Format", "json");

		// 加入方法特有参数
		parameterMap.put("Input", input);
		parameterMap.put("SnapshotConfig", snapshotConfig);

		// 对参数进行排序
		List<String> sortedKeys = new ArrayList<>(parameterMap.keySet());
		Collections.sort(sortedKeys);
		// 生成stringToSign字符
		final String SEPARATOR = "&";
		final String EQUAL = "=";
		StringBuilder stringToSign = new StringBuilder();
		stringToSign.append(HTTP_METHOD).append(SEPARATOR);
		stringToSign.append(UrlUtil.percentEncode("/")).append(SEPARATOR);
		StringBuilder canonicalizedQueryString = new StringBuilder();
		for (String key : sortedKeys) {
			// 此处需要对key和value进行编码
			String value = parameterMap.get(key);
			canonicalizedQueryString.append(SEPARATOR).append(UrlUtil.percentEncode(key)).append(EQUAL)
					.append(UrlUtil.percentEncode(value));
		}
		// 此处需要对canonicalizedQueryString进行编码
		stringToSign.append(UrlUtil.percentEncode(canonicalizedQueryString.toString().substring(1)));

		final String ALGORITHM = "HmacSHA1";
		final String secret = accessKeySecret;// 此处请替换成您的AccessKeySecret
		SecretKey key = new SecretKeySpec((secret + SEPARATOR).getBytes(Constants.ENCODE_TYPE), SignatureMethod.HMAC_SHA1);
		Mac mac = Mac.getInstance(ALGORITHM);
		mac.init(key);

		String signature = URLEncoder.encode(new String(new org.apache.commons.codec.binary.Base64()
				.encode(mac.doFinal(stringToSign.toString().getBytes(Constants.ENCODE_TYPE))), Constants.ENCODE_TYPE), Constants.ENCODE_TYPE);

		// 生成请求URL
		StringBuilder requestURL;
		requestURL = new StringBuilder(aliyunOSSConfig.mstsUrl);
		requestURL.append(URLEncoder.encode("Signature", Constants.ENCODE_TYPE)).append("=").append(signature);
		for (Map.Entry<String, String> e : parameterMap.entrySet()) {
			requestURL.append("&").append(UrlUtil.percentEncode(e.getKey())).append("=").append(UrlUtil.percentEncode(e.getValue()));
		}

		return requestURL.toString();
	}*/

	

}
