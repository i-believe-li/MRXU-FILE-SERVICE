package com.mrxu.cloud.file.service.process.trans.oss;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.mrxu.cloud.common.enums.MTSTranscodingEnum;
import com.mrxu.cloud.file.domain.entity.trans.TransCodingDTO;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.xml.crypto.dsig.SignatureMethod;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;

@Service("aliTranscoder")
public class AliTranscodeService implements IMediaService {
	@Override
	public TransCodingDTO transCode(String file, MTSTranscodingEnum trancodeEnum, String attName) {
		return null;
	}

	/*Logger logger = Logger.getLogger(AliGetPICService.class);

	@Autowired
	private AliyunOSSConfig aliyunOSSConfig;

	@Autowired
	private OSSKeyGenerator oSSKeyGenerator;

	@Override
	public TransCodingDTO transCode(String objectName, MTSTranscodingEnum trancodeEnum, String attName) {
		TransCodingDTO transCodingDTO = new TransCodingDTO();
		JSONObject inputJson = new JSONObject(); // 作业输入，JSON对象
		inputJson.put("Bucket", aliyunOSSConfig.getOss_bucket());
		inputJson.put("Location", aliyunOSSConfig.getOss_location());
		inputJson.put("Object", objectName);

		JSONObject outputJson = new JSONObject();// 作业输出，JSON对象
		String[] strArr = StringUtils.split(objectName, ".");
		if (strArr == null || strArr.length == 1) {
			return null;
		}
		outputJson.put("OutputObject", strArr[0] + "." + trancodeEnum.getItemValue());
		// 已经不支持TemplateId
		// outputJson.put("TemplateId", templateId);
		JSONObject container = new JSONObject();
		container.put("Format", trancodeEnum.getItemValue());
		outputJson.put("Container", container);

		JSONArray outputJsons = new JSONArray();
		outputJsons.add(outputJson);

		System.out.println(outputJsons.toString());
		// 组装截图请求 URL
		String transcodingJobUrl;
		try {
			transcodingJobUrl = assembleTranscodingJobUrl(inputJson.toString(), aliyunOSSConfig.getOss_bucket(),
					outputJsons.toString(), aliyunOSSConfig.getOss_location(), aliyunOSSConfig.getPipelineId(),
					aliyunOSSConfig.getOss_accessKey(), aliyunOSSConfig.getOss_secretKey());

			System.out.println("transcodingJobUrl-------->:" + transcodingJobUrl);

			String responseData = HttpUtil.get(transcodingJobUrl); // 发起请求

			JSONObject responseDataJson = JSON.parseObject(responseData);
			JSONObject JobResultList = responseDataJson.getJSONObject("JobResultList");
			JSONArray JobResults = JobResultList.getJSONArray("JobResult");
			System.out.println("responseData:" + responseData);

			for (Object JobResult : JobResults) {
				JSONObject jo = (JSONObject) JobResult;
				if (!Boolean.valueOf(jo.get("Success").toString())) {
					throw new Exception("Your request is fail,the reason：" + jo.getString("Message"));
				}
			}

			// 阿里云文件资源地址
			String transcodingUrl = "https://" + aliyunOSSConfig.getOss_bucket() + "." + aliyunOSSConfig.getOss_endpoint() + "/"
					+ outputJson.get("OutputObject");
			transCodingDTO.setTransCodingUrl(transcodingUrl);
		} catch (Exception e) {
			logger.error(e);
			throw new MrxuException(MrxuExceptionEnums.RC_SPC_FILE_UPLOAD_ERROR.value(),
					MrxuExceptionEnums.RC_SPC_FILE_UPLOAD_ERROR.getMessage());
		}
		return transCodingDTO;
	}

	*//**
	 *
	 * @param input
	 * @param outputBucket
	 * @param outputs
	 * @param outputLocation
	 * @param pipelineId
	 * @param accessKeyId
	 * @param accessKeySecret
	 * @return
	 * @throws UnsupportedEncodingException
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 *//*
	private String assembleTranscodingJobUrl(String input, String outputBucket, String outputs, String outputLocation,
			String pipelineId, String accessKeyId, String accessKeySecret) throws Exception {

		final String HTTP_METHOD = "GET";
		Map<String, String> parameterMap = new HashMap<>();

		// 加入请求公共参数
		parameterMap.put("Action", Constants.TRANSCODING_ACTION);
		parameterMap.put("Version", "2014-06-18");
		parameterMap.put("AccessKeyId", accessKeyId); // 此处请替换成您自己的AccessKeyId
		// parameterMap.put("Timestamp",
		// "2015-05-14T09:03:45Z");//此处将时间戳固定只是测试需要，这样此示例中生成的签名值就不会变，方便您对比验证，可变时间戳的生成需要用下边这句替换
		parameterMap.put("Timestamp", DateUtil.formatAlternativeIso8601Date(new Date()));
		parameterMap.put("SignatureMethod", "HMAC-SHA1");
		parameterMap.put("SignatureVersion", "1.0");
		parameterMap.put("SignatureNonce", UUID.randomUUID().toString());
		parameterMap.put("Format", "json");

		// 加入方法特有参数
		parameterMap.put("Input", input);
		parameterMap.put("OutputBucket", outputBucket);
		parameterMap.put("Outputs", outputs);
		parameterMap.put("OutputLocation", outputLocation);
		parameterMap.put("PipelineId", pipelineId);

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
		SecretKey key = new SecretKeySpec((secret + SEPARATOR).getBytes(Constants.ENCODE_TYPE),
				SignatureMethod.HMAC_SHA1);
		Mac mac = Mac.getInstance(ALGORITHM);
		mac.init(key);

		String signature = URLEncoder
				.encode(new String(
						new org.apache.commons.codec.binary.Base64()
								.encode(mac.doFinal(stringToSign.toString().getBytes(Constants.ENCODE_TYPE))),
						Constants.ENCODE_TYPE), Constants.ENCODE_TYPE);

		// 生成请求URL
		StringBuilder requestURL;
		requestURL = new StringBuilder("http://mts.cn-shanghai.aliyuncs.com?");
		requestURL.append(URLEncoder.encode("Signature", Constants.ENCODE_TYPE)).append("=").append(signature);
		for (Map.Entry<String, String> e : parameterMap.entrySet()) {
			requestURL.append("&").append(UrlUtil.percentEncode(e.getKey())).append("=")
					.append(UrlUtil.percentEncode(e.getValue()));
		}

		return requestURL.toString();
	}*/

}
