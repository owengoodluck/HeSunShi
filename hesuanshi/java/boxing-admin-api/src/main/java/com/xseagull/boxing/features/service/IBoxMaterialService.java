package com.xseagull.boxing.features.service;

import com.xseagull.boxing.common.ResultData;
import com.xseagull.boxing.features.entity.BoxMaterial;

public interface IBoxMaterialService {

	// 新增材质
	ResultData insert(BoxMaterial boxMaterial);

	// 分页材质
	ResultData queryByPage(Integer pageNo, Integer pageSize, String name);

	// 更新材质
	ResultData update(BoxMaterial boxMaterial);

	// 更新材质状态
	ResultData updateStatus(BoxMaterial boxMaterial);

	// 删除材质
	ResultData delete(long id);

}
