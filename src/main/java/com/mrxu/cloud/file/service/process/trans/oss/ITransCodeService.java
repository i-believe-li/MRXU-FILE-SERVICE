package com.mrxu.cloud.file.service.process.trans.oss;


import com.mrxu.cloud.file.domain.trans.TransCodingDTO;

public interface ITransCodeService {
	/**
	 * 转码
	 * @param file
	 * @return
	 */
	TransCodingDTO transCode(String file, String attName);
}
