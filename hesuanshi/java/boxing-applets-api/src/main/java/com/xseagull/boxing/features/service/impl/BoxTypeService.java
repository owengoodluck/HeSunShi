package com.xseagull.boxing.features.service.impl;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import com.xseagull.boxing.constant.Constants;
import com.xseagull.boxing.features.dao.*;
import com.xseagull.boxing.features.entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.xseagull.boxing.common.ResultCode;
import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.service.IBoxTypeService;

@Service
@Transactional
public class BoxTypeService implements IBoxTypeService {

	@Autowired
	private BoxTypeDao boxTypeDao;
	
	@Autowired
	private BoxMaterialDao boxMaterialDao;
	
	@Autowired
	private PrintMethodDao printMethodDao;
	
	@Autowired
	private DeliveryConfigDao deliveryConfigDao;

	@Autowired
	private SysConfigDao sysConfigDao;
	

	/**
	 * 查询分类
	 */
	@Override
	public ResultData queryClassify() {
		return ResultData.success(boxTypeDao.queryClassify());
	}
	
	/**
	 * 查询前5个分类
	 */
	@Override
	public ResultData queryClassify5() {
		return ResultData.success(boxTypeDao.queryClassify5());
	}
	
	/**
	 * 查询分类详情
	 */
	@Override
	public ResultData queryClassifyDetailById(long id) {
		return ResultData.success(boxTypeDao.queryClassifyDetailById(id));
	}

	@Override
	public ResultData calculated(Calculated calculated) {
		ScriptEngine jse = new ScriptEngineManager().getEngineByName("JavaScript");  
		BoxTypeDetail boxTypeDetail = boxTypeDao.queryClassifyDetailById(calculated.getTypeId());
		BoxMaterialDetail boxMaterialDetail = boxMaterialDao.queryClassifyDetailById(calculated.getMaterialId());
		PrintMethodPrice printMethodPrice = printMethodDao.queryNear(calculated.getPrintMethodId(), calculated.getPiece());
		double printPrice = 0;
		if (printMethodPrice != null) {
			printPrice = printMethodPrice.getPrice();
		}
		if (boxTypeDetail.getMinPiece() > calculated.getPiece()) {
			return ResultData.warn(ResultCode.INVALID_CALCULATED, "数量至少" + boxTypeDetail.getMinPiece());
		}
		
		if (calculated.getPiece() > boxTypeDetail.getMaxPiece() && boxTypeDetail.getMaxPiece() != 0) {
			return ResultData.warn(ResultCode.INVALID_CALCULATED, "数量最多" + boxTypeDetail.getMaxPiece());
		}
		String valuationFormula = boxTypeDetail.getValuationFormula();
		valuationFormula = valuationFormula + "*" + (calculated.getPiece() + boxTypeDetail.getReserveNum());
		valuationFormula = valuationFormula.replaceAll("\\$L", String.valueOf(calculated.getLength()));
		valuationFormula = valuationFormula.replaceAll("\\$W", String.valueOf(calculated.getWidth()));
		valuationFormula = valuationFormula.replaceAll("\\$H", String.valueOf(calculated.getHeight()));
		valuationFormula = valuationFormula.replaceAll("\\$M", String.valueOf(Double.valueOf(boxMaterialDetail.getPrice()) + printPrice));
		valuationFormula = valuationFormula.replaceAll("\\$N", String.valueOf(calculated.getPiece()));
		
    	try {
    		DecimalFormat df = new DecimalFormat("#.00");
    		Map<String, String> map = new HashMap<String, String>();
    		map.put("price", df.format(jse.eval(valuationFormula)));
    		
    		DeliveryConfig deliveryConfig = deliveryConfigDao.query();
    		String deliveryFormula = "0";
    		if (deliveryConfig != null) {
    			deliveryFormula = deliveryConfig.getFormula();
    			deliveryFormula = deliveryFormula.replaceAll("\\$G", boxMaterialDetail.getGrams());
    			deliveryFormula = deliveryFormula.replaceAll("\\$Y", String.valueOf((double)jse.eval(valuationFormula)/calculated.getPiece()));
    			deliveryFormula = deliveryFormula.replaceAll("\\$M", String.valueOf(Double.valueOf(boxMaterialDetail.getPrice()) + printPrice));
    			deliveryFormula = deliveryFormula.replaceAll("\\$N", String.valueOf(calculated.getPiece()));
    			deliveryFormula = deliveryFormula.replaceAll("\\$F", deliveryConfig.getFirstCost());
    			deliveryFormula = deliveryFormula.replaceAll("\\$C", deliveryConfig.getContinuedCost());
    			deliveryFormula = deliveryFormula.replaceAll("\\$Q", deliveryConfig.getContinuedWeight());
    			deliveryFormula = deliveryFormula.replaceAll("\\$V", deliveryConfig.getFirstWeight());
    		}
    		if ((double)jse.eval(deliveryFormula) > 0) {
    			map.put("fare", df.format(jse.eval(deliveryFormula)));
    		} else {
    			map.put("fare", deliveryConfig.getFirstCost());
    		}
    		map.put("advancePaymentPrice",this.getAdvancePaymentPriceConfig());
			return ResultData.success(map);
		} catch (ScriptException e) {
			e.printStackTrace();
			return ResultData.error(ResultCode.INTERNAL_SERVER_ERROR);
		}	
	}

	@Override
	public ResultData queryClassifyDetailAll() {
		return ResultData.success(boxTypeDao.queryClassifyDetailAll());
	}

    private String getAdvancePaymentPriceConfig(){
		SysConfig config = sysConfigDao.queryConfigByItemKey(Constants.ADVANCE_PAYMENT_PRICE);
		if(config!=null && config.getItemValue()!=null){
			return config.getItemValue();
		}else{
			return "200"; // Default Value . TODO
		}
    }

    public ResultData getAdvancePaymentPrice(){
		return ResultData.success(getAdvancePaymentPriceConfig());
	}
}
