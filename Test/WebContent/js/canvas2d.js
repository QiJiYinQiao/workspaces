/*
 * created with eclipse
 * User: sun
 * Date: 15-06-01
 * Time: AM09:00
 * To change this temple use File | Setting | File Templates.
 */

function Canvas2D($canvas)
{
	var context = $canvas[0].getContext("2d"),
		width = $canvas[0].width,
		height = $canvas[0].height,
		pageOffset = $canvas[0].offset();
	
	context.font = "24px Verdana, Geneva, sans-serif";
	context.textBaseline = "top";
	
	/*
	 * 绘制矩形
	 * @param start
	 * @param end
	 * @param isFill
	 */
	this.drawRect = function (start, end, isFill)
	{
		var w = end.x - start.x , h = end.y - start.y;
		if (ifFill)
		{
			context.fillRect(start.x,start.y, w, h);
		}
		else
		{
			context.strokeRect(start.x, start.y, w, h);
		}
	};
	
	/*
	 * 根据书写的文本，得到该文本在canvas上书写的中心位置的左上角坐标
	 * @param text
	 * @return {{x:number, y:number}}
	 */
	this.caculateTextCenterPos = function (text)
	{
		var metrics = context.measureText(text);
		console.log(metrics);
		var textWidth = metrics.width;
		var textHeight = parseInt(context.font);
		
		return {
			x: width / 2 - textWidth / 2,
			y: height / 2 - textHeight / 2
		};
	}
	this.width = function ()
	{
		return width;
	}
	this.height = function ()
	{
		return height;
	}
	this.resetOffset = function ()
	{
		pageOffset = $canvas.offset();
	}
	/*
	 * 当屏幕大小发生变化，重新计算offset
	 */
	$(window).resize(function ()
	{
		pageOffset = $canvas.offset();
	});
	
	/*
	 * 将页面上的左边转化为canvas中的坐标
	 * @param pageX
	 * @param pageY
	 * @returns {{x: number, y: number}}
	 */
	this.getCanvasPoint = function (pageX, pageY)
	{
		return{
			x: pageX - pageOffset.left,
			y: pageY - pageOffset.top
		}
	}
	
	/*
	 * 清除区域，此用户鼠标擦出刮奖涂层
	 * @param start
	 * @returns {*}
	 */
	this.clearRect = function (start)
	{
		context.clearRect(start.x, start.y, 10, 10);
		return this;
	};
	
	/*
	 * 将文本绘制到canvas的中间
	 * @param text
	 * @param fill
	 */
	this.drawTextInCenter = function (text, fill)
	{
		var point = this.caculateTextCenterPos(text);
		if (fill)
		{
			context.fillText(text, point.x, point.y);
		}
		else
		{
			context.strokeText(text, point.x, point.y);
		}
	};
	
	/*
	 * 设置画笔的宽度
	 * @param nameWidth
	 * @returns {*]
	 */
	this.penWidth = function (newWidth)
	{
		if(arguments.length)
		{
			context.lineWidth = new Width;
			return this;
		}
		return context.lineWidth;
	};
	
	/*
	 * 设置画笔的颜色
	 * @param newColor
	 * @returns {*}
	 */
	this.penColor = function (newColor)
	{
		if (arguments.length)
		{
			context.strokeStyle = newColor;
			context.fillStyle = newColor;
			return this;
		}
		
		return context.strokeStyle;
	};
	
	/*
	 * 设置字体大小
	 * @param fontSize
	 * @returns {*}
	 */
	this.fontSize = function (fontSize)
	{
		if (arguments.length)
		{
			context.font = fontSize + "px Verdana, Geneva, sans-serif";
			
			return this;
		}
		
		return context.fontSize;
	}
	
}