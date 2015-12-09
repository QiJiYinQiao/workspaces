package com.oasys.viewModel;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("rawtypes")
public class GridModel {
	private Object data;
	private List rows = new ArrayList();
	private Long total = 0L;

	public List getRows() {
		return rows;
	}

	public void setRows(List rows) {
		this.rows = rows;
	}

	public Long getTotal() {
		return total;
	}

	public void setTotal(Long total) {
		this.total = total;
	}

	public GridModel(List rows, Long total) {
		super();
		this.rows = rows;
		this.total = total;
	}

	public GridModel() {
		super();
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
