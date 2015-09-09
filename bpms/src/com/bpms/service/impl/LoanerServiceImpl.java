package com.bpms.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bpms.dao.BaseDAO;
import com.bpms.model.Address;
import com.bpms.model.LoanOrder;
import com.bpms.model.Loaner;
import com.bpms.model.vo.LoanerModel;
import com.bpms.service.AddressService;
import com.bpms.service.LoanOrderService;
import com.bpms.service.LoanerService;
import com.bpms.util.Collections;
import com.bpms.util.HqlUtil;
import com.bpms.util.PageUtil;

@Service("loanerService")
public class LoanerServiceImpl implements LoanerService {

	@Autowired
	private BaseDAO<Loaner> baseDAO;
	@Autowired
	private LoanOrderService loanOrderServiceImpl;
	@Autowired
	private AddressService addressServiceImpl;

	@Override
	public LoanerModel persistenceLoaner(LoanerModel loanerModel) {
		// 返回的对象
		LoanerModel res = new LoanerModel();
		res.setState(false);
		try {
			// 贷款人对象
			Loaner loaner = new Loaner();
			// 判断前台传过来的实体对象是否有id
			if (StringUtils.isNotBlank(loanerModel.getLoanerId())) {
				// 有id，就根据id将该贷款人查出
				loaner = findById(loanerModel.getLoanerId());
				// 如果该贷款人的身份证号没变，则是更新该贷款人
				if (loaner.getIdNo().equals(loanerModel.getIdNo())) {
					// 将该贷款人最新的信息复制过来
					PropertyUtils.copyProperties(loaner, loanerModel);
					// 执行更新
					baseDAO.update(loaner);
				} else {
					loaner = new Loaner();
					// 将该贷款人最新的信息复制过来
					PropertyUtils.copyProperties(loaner, loanerModel);
					// 设置创建时间
					loaner.setCreateDate(new Date());
					loaner.setLoanerId(null);
					baseDAO.save(loaner);
				}
			} else {
				// 将该贷款人最新的信息复制过来
				PropertyUtils.copyProperties(loaner, loanerModel);
				// 设置创建时间
				loaner.setCreateDate(new Date());
				loaner.setLoanerId(null);
				baseDAO.save(loaner);
			}
			// 新增户籍地址
			Address address = new Address();
			address.setProvinceId(loanerModel.getHukouProvinceId() == null ? 0
					: loanerModel.getHukouProvinceId());// 省
			address.setCityId(loanerModel.getHukouCityId() == null ? 0
					: loanerModel.getHukouCityId());// 市
			address.setAreaId(loanerModel.getHukouAreaId() == null ? 0
					: loanerModel.getHukouAreaId());// 县/区
			address.setAddrDetails(loanerModel.getHukouAddrDetails() == null ? ""
					: loanerModel.getHukouAddrDetails());// 详细地址
			address.setAddrType("A");// 地址类型
			address.setHostType("A");// 宿主类别
			address.setHostId(loaner.getLoanerId());// 宿主id
			addressServiceImpl.saveAddress(address);
			// 现住地址
			Address address1 = new Address();
			address1.setProvinceId(loanerModel.getCurProvinceId() == null ? 0
					: loanerModel.getCurProvinceId());// 省
			address1.setCityId(loanerModel.getCurCityId() == null ? 0
					: loanerModel.getCurCityId());// 市
			address1.setAreaId(loanerModel.getCurAreaId() == null ? 0
					: loanerModel.getCurAreaId());// 县/区
			address1.setAddrDetails(loanerModel.getCurAddrDetails() == null ? ""
					: loanerModel.getCurAddrDetails());// 详细地址
			address1.setAddrType("B");// 地址类型
			address1.setHostType("B");// 宿主类别
			address1.setHostId(loaner.getLoanerId());// 宿主id
			addressServiceImpl.saveAddress(address1);

			loaner.setHukouAddr(address.getAddrId());
			loaner.setCurAddr(address1.getAddrId());

			/*** 新增订单 ***/
			LoanOrder loanOrder = new LoanOrder();
			// 如果订单id不为空，更新该订单
			if (StringUtils.isNotBlank(loanerModel.getLoanOrderId())) {
				// 根据订单id查到该订单
				loanOrder = loanOrderServiceImpl.findLoanOrderById(loanerModel
						.getLoanOrderId());
				// 如果身份证号没变，则是更新
				if (loanOrder.getIdNo().equals(loanerModel.getIdNo())) {
					// 更新信息
					PropertyUtils.copyProperties(loanOrder, loaner);
				} else {
					// 身份证号变了，则是新增
					loanOrder = new LoanOrder();
					// 更新信息
					PropertyUtils.copyProperties(loanOrder, loaner);
					// id置空
					loanOrder.setLoanOrderId(null);
				}
				// 执行更新
				loanOrderServiceImpl.persistenceLoanOrder(loanOrder);
			} else {
				// 复制字段
				PropertyUtils.copyProperties(loanOrder, loaner);
				// 执行新增
				loanOrderServiceImpl.persistenceLoanOrder(loanOrder);
			}
			res.setLoanerId(loaner.getLoanerId());// 贷款人id
			res.setLoanOrderId(loanOrder.getLoanOrderId());// 订单id
			res.setCreateDate(loaner.getCreateDate());// 创建时间
			res.setHukouAddr(address.getAddrId());// 户籍地址id
			res.setCurAddr(address1.getAddrId());// 现住地址id
			res.setState(true);
			return res;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return res;
	}

	@Override
	public void updateLoaner(Loaner loaner) {
		// TODO Auto-generated method stub
	}

	@Override
	public List<Loaner> findLoaner(Map<String, Object> map, PageUtil pageUtil) {
		String hql = "from Loaner t where 1=1";
		hql += HqlUtil.getSearchConditionsHQL("t", map);
		hql += HqlUtil.getGradeSearchConditionsHQL("t", pageUtil);
		return baseDAO.find(hql, map, pageUtil.getPage(), pageUtil.getRows());
	}

	@Override
	public Long countAllLoaner(Map<String, Object> map, PageUtil pageUtil) {
		String hql = "select count(*) from Loaner t where 1=1";
		hql += HqlUtil.getSearchConditionsHQL("t", map);
		hql += HqlUtil.getGradeSearchConditionsHQL("t", pageUtil);
		return baseDAO.count(hql, map);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LoanerModel> findCombogridLoaner(String q) {
		// 定义返回值
		List<LoanerModel> list = new ArrayList<LoanerModel>();
		String sql = "SELECT "
				+ "tbpl.AGE AS age, "
				+ "tbpl.ANNUAL_SALARY  AS annualSalary, "
				+ "tbpl.CREATE_DATE AS createDate, "
				+ "tbpl.CUR_ADDR AS curAddr, "
				+ "tbpl.EMAIL AS email, "
				+ "tbpl.FIXED_TEL AS fixedTel, "
				+ "tbpl.GENDER_TYPE AS genderType, "
				+ "tbpl.HAS_CHILD AS hasChild, "
				+ "tbpl.HOUSE_INSTALL_PAY AS houseInstallPay, "
				+ "tbpl.HUKOU_ADDR AS hukouAddr, "
				+ "tbpl.ID_NO AS idNo, "
				+ "tbpl.INCOME_SRC AS incomeSrc, "
				+ "tbpl.LOANER_ID AS loanerId, "
				+ "tbpl.MARRIAGE_TYPE AS marriageType, "
				+ "tbpl.MOBILE_TEL AS mobileTel, "
				+ "tbpl.MORTGAGE_STATUS AS mortgageStatus, "
				+ "tbpl.NAME AS name, "
				+ "tbpl.QQ_NO AS qqNo, "
				+ "tbpl.RENT AS rent, "

				+ "tbpa1.ADDR_DETAILS AS hukouAddrDetails, "
				+ "tbpa1.ADDR_ID AS hukouAddrId, "
				+ "tbpa1.ADDR_TYPE AS hukouAddrType, "
				+ "tbpa1.AREA_ID AS hukouAreaId, "
				+ "tbpa1.CITY_ID AS hukouCityId, "
				+ "tbpa1.HOST_ID AS hukouHostId,  "
				+ "tbpa1.HOST_TYPE AS hukouHostType, "
				+ "tbpa1.PROVINCE_ID AS hukouProvince, "

				+ "tbpa2.ADDR_DETAILS AS curAddrDetails, "
				+ "tbpa2.ADDR_ID AS curAddrId, "
				+ "tbpa2.ADDR_TYPE AS curAddrType, "
				+ "tbpa2.AREA_ID AS curAreaId, "
				+ "tbpa2.CITY_ID AS curCityId, "
				+ "tbpa2.HOST_ID AS curHostId, "
				+ "tbpa2.HOST_TYPE AS curHostType, "
				+ "tbpa2.PROVINCE_ID AS curProvince, "

				+ "tbpl.FAMILY_TEL AS familyTel "// 新增字段家庭电话

				+ "FROM  t_bp_loaner tbpl  "
				+ "LEFT JOIN t_bp_address tbpa1 ON tbpl.hukou_addr = tbpa1.addr_id "
				+ "LEFT JOIN t_bp_address tbpa2 ON tbpl.cur_addr = tbpa2.addr_id ";

		if (StringUtils.isNotBlank(q)) {
			sql += "WHERE tbpl.NAME LIKE '%" + q + "%'" + "OR tbpl.ID_NO = '"
					+ q + "'";
		}
		List<Object> objects = this.baseDAO.findBySql(sql,new  PageUtil(1, 10));
		if (Collections.listIsNotEmpty(objects)) {
			for (Object object : objects) {
				Object[] obj = (Object[]) object;
				LoanerModel loanerModel = new LoanerModel();
				loanerModel.setAge((Integer) obj[0]);
				loanerModel.setAnnualSalary((BigDecimal) obj[1]);
				loanerModel.setCreateDate((Date) obj[2]);
				loanerModel.setCurAddr((String) obj[3]);
				loanerModel.setEmail((String) obj[4]);
				loanerModel.setFixedTel((String) obj[5]);
				loanerModel.setGenderType(String.valueOf(obj[6]));
				loanerModel.setHasChild(String.valueOf(obj[7]));
				loanerModel.setHouseInstallPay((BigDecimal) obj[8]);
				loanerModel.setHukouAddr((String) obj[9]);
				loanerModel.setIdNo((String) obj[10]);
				loanerModel.setIncomeSrc((String) obj[11]);
				loanerModel.setLoanerId((String) obj[12]);
				loanerModel.setMarriageType(String.valueOf(obj[13]));
				loanerModel.setMobileTel((String) obj[14]);
				loanerModel.setMortgageStatus(String.valueOf(obj[15]));
				loanerModel.setName((String) obj[16]);
				loanerModel.setQqNo((String) obj[17]);
				loanerModel.setRent((BigDecimal) obj[18]);

				loanerModel.setHukouAddrDetails((String) obj[19]);
				loanerModel.setHukouAddrId((String) obj[20]);
				loanerModel.setHukouAddrType(String.valueOf(obj[21]));
				loanerModel.setHukouAreaId((Integer) obj[22]);
				loanerModel.setHukouCityId((Integer) obj[23]);
				loanerModel.setHukouHostId((String) obj[24]);
				loanerModel.setHukouHostType(String.valueOf(obj[25]));
				loanerModel.setHukouProvinceId((Integer) obj[26]);

				loanerModel.setCurAddrDetails((String) obj[27]);
				loanerModel.setCurAddrId((String) obj[28]);
				loanerModel.setCurAddrType(String.valueOf(obj[29]));
				loanerModel.setCurAreaId((Integer) obj[30]);
				loanerModel.setCurCityId((Integer) obj[31]);
				loanerModel.setCurHostId((String) obj[32]);
				loanerModel.setCurHostType(String.valueOf(obj[33]));
				loanerModel.setCurProvinceId((Integer) obj[34]);

				loanerModel.setFamilyTel((String) obj[35]);
				list.add(loanerModel);
			}
		}
		return list;
	}

	/**
	 * 根据ID查询一个贷款人客户信息的身份证
	 */
	public Loaner findById(String id) {
		try {
			return baseDAO.get(Loaner.class, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
