/*
 *Create with eclipse
 *User: sun
 *Date: 15-06-01
 *Time: AM09:35
 *To change this template use File | Settings | File Templates.
 */
function GuaGuaLe(idFront, idBack)
{
	this.$eleBack = $("#" + idBack);
	this.$eleFront = $("#" + idFront);
	this.frontCanvas = new Canvas2D(this.$eleFront);
	this.backCanvas = new Canvas2D(this.$eleBack);
	
	this.isStart = false;
	
}

GuaGuaLe.prototye = {
		constructor: GuaGuaLe,
		/*
		 * 将用户传入的参数和默认参数做合并
		 * @param desAttr
		 * @returns {{frontFillColor: string, backFillColor: string, backFontColor: string, backFontSize: number, msg: string}]
		 */
		mergeAttr: function (desAttr)
		{
			var defaultAttr = {
					frontFillColor: "silver",
					backFillColor: "gold",
					backFontColor: "red",
					backFontSize: 24,
					msg: "谢谢惠顾"
			};
			for (var p in desAttr)
			{
				defaultAttr[p] = desAttr[p];
			}
			
			return defaultAttr;
		
		},
		
		init: function (desAttr)
		{
			var attr = this.mergeAttr(desAttr);
			
			//初始化canvas
			this.backCanvas.penColor(attr.backFillColor);
			this.backCanvas.fontSize(attr.backFontSize);
			this.backCanvas.drawRect({x:0, y:0}, {x: this.backCanvas.width(), y: this.backCanvas.height()},true);
			this.backCanvas.penColor(attr.backFontColor);
			this.backCanvas.drawTextInCenter(attr.msg, true);
			//初始化canvas
			this.frontCanvas.penColor(attr.frontFillColor);
			this.frontCanvas.drawRect({x: 0, y: 0},{x: this.frontCavas.width(), y:this.frontCanvas.height()},true);
			
			var _this = this;
			//设置事件
			this.$eleFront.mousedown(function (event)
			{
				_this.mouseDown(event);
			}).mousemove(function (event)
					{
						_this.mouseMove(event);
					}).mouseup(function (event)
							{
								_this.mouseUp(event);
							});
					
		},
		mouseDown: function (event)
		{
			this.isStart = true;
			this.startPoint = this.frontCanvas.getCanvasPoint(event.pageX, event.pageY);
		},
		mouseMove: function (event)
		{
			if (!this.isStart)return;
			var p = this.frontCanvas.getCanvasPoint(event.pageX, event.pageY);
			this.frontCanvas.clearRect(p);
		},
		mouseUp: function (event)
		{
			this.isStart = false;
		}
};