package com.oasys.service;

import com.oasys.model.BusinessTripAttach;

public interface BusinessTripAttachService {

	void saveOrUpdateBusinessTripAttach(BusinessTripAttach businessTripAttach);

	boolean saveBusinessTripAttach(BusinessTripAttach businessTripAttach);
	BusinessTripAttach findBusinessTripAttachByAppNo(String appNo);
}
