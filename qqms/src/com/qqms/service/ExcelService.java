package com.qqms.service;

import java.util.List;


public interface ExcelService
{

	<T> List<T> findExcelExportList(String isCheckedIds, Class<T> clazz );


}
