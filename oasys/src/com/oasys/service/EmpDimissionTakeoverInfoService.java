package com.oasys.service;

import java.util.List;

import com.oasys.model.EmpDimissionTakeoverInfo;

public interface EmpDimissionTakeoverInfoService {

	List<EmpDimissionTakeoverInfo> findEmpDimissionTakeoverList(String appNo);

	void saveEmpDimissionTakeoverInfo(
			EmpDimissionTakeoverInfo empDimissionTakeoverInfo);

	void doDeleteById(EmpDimissionTakeoverInfo takeoverInfo);

}
