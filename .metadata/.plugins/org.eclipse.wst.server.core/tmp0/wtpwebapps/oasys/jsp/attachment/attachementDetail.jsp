<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%> 
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
		+ request.getServerName() + ":" + request.getServerPort()
		+ path + "/";
	String appNo = request.getParameter("appNo");
	String userId = request.getParameter("userId");
	String isDetail = request.getParameter("isDetail");
	String detailId=request.getParameter("detailId");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>查看附件</title>
</head>
<style type="text/css">
	li{
		list-style:none;
	}

	ul li
	{
		margin:0;
		list-style:none;
	}
	
	ul li
	{
		float:left;
		width:100px;
		height:100px;
		overflow:hidden;
		margin:4px;
		position:relative;
		cursor:pointer;
		border:1px solid #999999;
		background:#ffffff;
	}
	
	ul li img
	{
		max-width:100%;
	}
	
	ul li:hover .mws-gallery-overlay
	{
		display:block;
	}
	
	ul li .mws-gallery-overlay
	{
		position:absolute;
		top:0; bottom:0;
		left:0; right:0;
		display:none;
		background:url(img/mws-gallery-overlay.png);
	}
	
	ul li .mws-gallery-overlay .mws-gallery-zoom
	{
		width:32px;
		height:32px;
		display:block;
		position:absolute;
		top:50%; left:50%;
		margin-left:-16px;
		margin-top:-16px;
		
		background:#323232 url(img/magnifying-glass-2.png) no-repeat center center;
		
		/* CSS 3 */
		
		-webkit-border-radius:4px;
		-moz-border-radius:4px;
		-o-border-radius:4px;
		-khtml-border-radius:4px;	
		border-radius:4px;		
	}

	#rollPhotos{
		list-style: none;margin: 0px;padding: 0px;
	}

	#rollPhotos li{
		float: left;background-color: #ccd;margin: 0px 20px 10px 0px;
	}
	
	.checkedPhoto{
		height:100%;
		border: 3px solid red;
		opcity: 0.5;
	}
	
	.photoListUlClass:after {
		clear: both;
		content: ' ';
		display: block;
		font-size: 0;
		line-height: 0;
		visibility: hidden;
		width: 0;
		height: 0;
	}
	
	.editPhotoChecked{
		position:absolute;
		left:0;
		top:0;
		width:30px;
		height:30px;
		background:url(img/check.png) no-repeat 0px 4px;
		border:1px solid gray;
	}
	
	.editPhotoUNChecked{
		position:absolute;
		left:0;
		top:0;
		width:30px;
		height:30px;
		background-color:white;
		border:1px solid gray;
	}
	
	.photoEditDC{
		display: none;
		position: absolute;
		left: 0;
		top: 0;
		bottom: 0;
		right: 0;
		z-index: 9999;
	}
	
	img{
		max-width:100%;
		max-height: 100%;
	}
	
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/xheditor/jquery-1.8.0.min.js"></script>
<script type="text/javascript" src="js/jquery.easyui.min.js"></script>
<script type="text/javascript" src="js/common.js"></script>
<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script>
<body style="background-color: #123659;">
	<div id="mws-container" class="clearfix" style="width:100%;height:100%;">
		<input type="hidden" id="appNo" value="<%=appNo%>">
		<input type="hidden" id="userId" value="<%=userId%>">
		<!-- 如果为1 则是查看详情，禁止编辑 -->
		<input type="hidden" id="isDetail" value="<%=isDetail%>">
		<input type="hidden" id="detailId" value="<%=detailId%>">
         <div class="container">
         		<div id="noAttachment" style="width: 100%;height:250px;text-align: center;padding-top:250px;display: none;">
					<font size="10" style="font-weight: bold;box-shadow: 3px 3px 5px 3px;color: white;">
						暂无附件
					</font>
				</div>  
             	<div style="width: 100%;height:50px;display: block;margin-top: 20px;">
             		<div onclick="returnPhotoTypeList();" style="background-color: #224b73;width:80px;height:30px;text-align: center;padding-top: 10px;margin-left: 10px;cursor: pointer;float: left;"><font style="color: white;font-family: inherit;">返回</font></div>
                 	<div id="cancelEditPhotoDivBtn" onclick="editPhotoList();" style="background-color: #224b73;width:80px;height:30px;text-align: center;padding-top: 10px;margin-left: 10px;cursor: pointer;float: left;display: none;"><font style="color: white;font-family: inherit;">编辑</font></div>
                 	<div id="cancelDownloadPhotoDivBtn" onclick="downLoadPhotoList();" style="background-color: #224b73;width:80px;height:30px;text-align: center;padding-top: 10px;margin-left: 10px;cursor: pointer;float: left;display: none;"><font style="color: white;font-family: inherit;">下载</font></div>
                 	<div id="editPhotoDivBtn" style="display: none;">
                 		<div onclick="editPhotoListCancel();" style="background-color: #224b73;width:80px;height:30px;text-align: center;padding-top: 10px;margin-left: 10px;cursor: pointer;float: left;"><font style="color: white;font-family: inherit;">完成编辑</font></div>
                 		<div id="allChecked" onclick="editPhotoAllChecked();" style="background-color: #224b73;width:80px;height:30px;text-align: center;padding-top: 10px;margin-left: 10px;cursor: pointer;float: left;"><font style="color: white;font-family: inherit;">全选</font></div>
                 		<div id="allUnChecked" onclick="editPhotoAllUnChecked();" style="background-color: #224b73;width:80px;height:30px;text-align: center;padding-top: 10px;margin-left: 10px;cursor: pointer;float: left;display:none;"><font style="color: white;font-family: inherit;">反选</font></div>
                 		<div onclick="editPhotoDelete();" style="background-color: #224b73;width:80px;height:30px;text-align: center;padding-top: 10px;margin-left: 10px;cursor: pointer;float: left;"><font style="color: white;font-family: inherit;">删除所选</font></div>
                 		<div onclick="downloadPhoto();" style="background-color: #224b73;width:80px;height:30px;text-align: center;padding-top: 10px;margin-left: 10px;cursor: pointer;float: left;"><font style="color: white;font-family: inherit;">下载所选</font></div>
                 	</div>
                 	<div id="downloadPhotoDivBtn" style="display: none;">
                 		<div onclick="downloadPhotoListCancel();" style="background-color: #224b73;width:80px;height:30px;text-align: center;padding-top: 10px;margin-left: 10px;cursor: pointer;float: left;"><font style="color: white;font-family: inherit;">完成下载</font></div>
                 		<div id="allDownloadChecked" onclick="downloadPhotoAllChecked();" style="background-color: #224b73;width:80px;height:30px;text-align: center;padding-top: 10px;margin-left: 10px;cursor: pointer;float: left;"><font style="color: white;font-family: inherit;">全选</font></div>
                 		<div id="allDownloadUnChecked" onclick="downloadPhotoAllUnChecked();" style="background-color: #224b73;width:80px;height:30px;text-align: center;padding-top: 10px;margin-left: 10px;cursor: pointer;float: left;display:none;"><font style="color: white;font-family: inherit;">反选</font></div>
                 		<div onclick="downloadPhoto();" style="background-color: #224b73;width:80px;height:30px;text-align: center;padding-top: 10px;margin-left: 10px;cursor: pointer;float: left;"><font style="color: white;font-family: inherit;">下载所选</font></div>
                 	</div>
                </div>
                <div >
               		<!-- 相册主页 S -->
               		<div id="TypeList" class="mws-panel-content">
                		</div>
               		<!-- 相册主页 E -->
                </div>
         </div>
     </div>
     <script type="text/javascript">
		var isRollPhotoListOpen = false;//图片详情是否打开，判断上下左右键是否失效
		var isDetail = $("#isDetail").val();
		var detailId=$("#detailId").val();//是否是申请子项:有值是子项否则不是子项
		//图片类型列表
		var photoTypeList;
		var photoListNow;//当前查看的图片列表对象
		var imageIndex = 0;//图片下标
		var photoListsize = 0;//图片列表长度
		var lefts = 0;//是否是第一张图片,滚动栏位移距离 
		//var isImage = "^[a-zA-Z]:(\\.+)(.JPEG|.jpeg|.JPG|.jpg|.GIF|.gif|.BMP|.bmp|.PNG|.png)$";
		//得到图片类型列表，循环创建图片类型 DOM
		var ulStr = "<ul id='mws-gallery' class='clearfix'>"; //类型列表DOM字符串
		$(function(){
			checkAttachmentList();
			
			//方向键事件监听
			document.onkeydown=function(event){
				var e = event || window.event || arguments.callee.caller.arguments[0];
				
				if(isRollPhotoListOpen){
					if(e && e.keyCode==38 || e && e.keyCode==37){//上,左
						//alert('38=上键，37=左键');
						$("#previousBtn").click();
					}

					if(e && e.keyCode==40 || e && e.keyCode==39){//下,右
						//alert('38=下键，37=右键');
						$("#nextBtn").click();
					}
				}
			}; 
			
			//创建相册列表
		   /*  for(var i = 0; i < photoTypeList.length; i++){
		    	photoType = photoTypeList[i].attType;
		    	photoTypeName = photoTypeList[i].attTypeName;
		    	imageList = photoTypeList[i].acm;
		    	count = photoTypeList[i].count;
				//创建类型列表DOM
				ulStr += "<li><div id='"+photoType+"' onclick=\"checkPhotoType("+i+",'"+imageList.length+"')\">";
				if(imageList.length==0){
					ulStr += "<div style='height:80px;'><img src='img/nophoto.png'/></div>";
				}else{
					ulStr += "<div style='height:80px;'><img src='"+imageList[0].attURL+"'/></div>";
				}
				ulStr += "<div style='position: absolute;top:60px;background-color: gray;width: 100%;height:20px;text-align: right;opacity:0.8;'>共"+count+"张</div>";
				ulStr += "<div style='position: absolute;top:80px;background-color: gray;width: 100%;height:20px;text-align: center;opacity:0.8;'>"+photoTypeName+"</div>";
				ulStr += "<div class='mws-gallery-overlay'><div class='mws-gallery-zoom'></div></div></div></li>";
			}
			ulStr += "</ul>";
			$(ulStr).appendTo("#TypeList"); */ 
			
			for(var k in photoTypeList){
				photoType = k;									//附件类型
		    	//photoTypeName = photoTypeList[k][0].attTypeName;	//附件类型名称
		    	photoTypeName = "附件";	//附件类型名称
		    	imageList = photoTypeList[k];					//附件LIST
		    	count = photoTypeList[k].length;				//该类型下附件个数
				//创建类型列表DOM
				ulStr += "<li><div id='"+photoType+"' onclick=\"checkPhotoType('"+k+"','"+count+"')\">";
				if(imageList.length==0){
					ulStr += "<div style='height:80px;'><img src='img/nophoto.png'/></div>";
				}else{
					ulStr += "<div style='height:80px;'><img src='"+imageList[0].attURL+"'/></div>";
				}
				ulStr += "<div style='position: absolute;top:60px;background-color: gray;width: 100%;height:20px;text-align: right;opacity:0.8;'>共"+count+"张</div>";
				ulStr += "<div style='position: absolute;top:80px;background-color: gray;width: 100%;height:20px;text-align: center;opacity:0.8;'>"+photoTypeName+"</div>";
				ulStr += "<div class='mws-gallery-overlay'><div class='mws-gallery-zoom'></div></div></div></li>";
			}
			ulStr += "</ul>";
			$(ulStr).appendTo("#TypeList");
			
		});
	
		//查看相册
		function checkPhotoType(photoType,len){
			var photoList = photoTypeList[photoType];
			photoListNow = photoList;
			var photoListStr = "";							  //相册内容列表DOM字符串
			var rollListStr = "";								  //滚动详情DOM字符串
			photoListStr += "<ul id='photoListUlId' class='photoListUlClass'>";
			rollListStr += "<div id='photoDetail' style='position:absolute;width:100%;height:100%;left:0px;top:0px;display: none;z-index: 999;overflow: hidden;background-color: gray;'>";
			rollListStr += "<div style='position:absolute;right:10px;top:10px;width:60px;height:60px;z-index: 9999;cursor:pointer;' onClick=\"closeBtnOn();\"><img src='img/close.png' /></div>"
			
//			rollListStr += "<ul id='' class='clearfix'><li style='width:"+$(window).width()+"px;height:"+$(window).height()*0.85+"px;margin:0;padding:0;' > <img id='imgplace' src='"+photoList[0].attURL+"' alt='' /> </li> </ul>";
			rollListStr += "<div style='width:"+$(window).width()+"px;height:"+$(window).height()*0.85+"px;margin:0;padding:0;overflow:auto;text-align:center;' > <img id='imgplace' src='"+photoList[0].attURL+"' alt='' /> </div> ";
			
			rollListStr += "<div id='previousBtn' style='position: absolute;left: 0px;top:400px;width:60px;height:60px;cursor: pointer;' onclick=\"lastPage();\">";
			rollListStr += "<img src='img/go_previous.png' alt='上一张' /></div>";
			rollListStr += "<div id='nextBtn' style='position: absolute;right: 10px;top:400px;width:60px;height:60px;cursor: pointer;' onclick=\"nextPage();\">";
			rollListStr += "<img src='img/go_next.png' alt='下一张' /></div>";
			rollListStr += "<div id='photoRoll' style='position: absolute;bottom:0px;height:110px;background-color: gray;width: "+$(window).width()+"px;overflow: hidden;'><ul id='rollPhotos'>"; //图片详细列表dom字符串
			
			for(var i = 0; i < photoList.length; i++){
					//jsp/openoffice/documentView.jsp?attId="+attId+"
					if(/^(JPEG|jpeg|JPG|jpg|GIF|gif|BMP|bmp|PNG|png)$/.test(photoList[i].fileType)){
						if(i==0){
							rollListStr += "<li ><div id='photoRollList"+i+"' class='checkedPhoto' onclick=\"checkPhotoRoll("+i+")\">";
						}else{
							rollListStr += "<li ><div id='photoRollList"+i+"' onclick=\"checkPhotoRoll("+i+")\">";
						}
						rollListStr += "<div style='height:80px;'><img src='"+photoList[i].attURL+"' alt='' /></div></div>";
					}else{
						rollListStr += "<li ><div id='photoRollList"+i+"' onclick=\"onlineReviewFile('"+photoList[i].attId+"')\">";
						rollListStr += "<div style='height:80px;'><img src='img/ViewFile.png' alt='' /></div></div>";
					}
					
					rollListStr += "<div style='position: absolute;top:80px;background-color: gray;width: 100%;height:20px;text-align: center;opacity:0.8;'>"+photoList[i].attName+"</div>";
					rollListStr += "</li>";
					
					photoListStr += "<li>";
					//checkbox
					photoListStr += "<div class='photoEditDC' onClick='photoChecked(this);'><div class='editPhotoUNChecked'><input type='hidden' value='"+photoList[i].attId+"' ></div></div>";
					
					if(/^(JPEG|jpeg|JPG|jpg|GIF|gif|BMP|bmp|PNG|png)$/.test(photoList[i].fileType)){	
						photoListStr += "<div onclick=\"checkPhotoDetail("+i+")\">";
						photoListStr += "<div style='height:80px;'><img src='"+photoList[i].attURL+"'/></div>";
					}else{
						photoListStr += "<div onclick=\"onlineReviewFile('"+photoList[i].attId+"')\">";
						photoListStr += "<div style='height:80px;'><img src='img/ViewFile.png'/></div>";
					}
					photoListStr += "<div style='position: absolute;top:80px;background-color: gray;width: 100%;height:20px;text-align: center;'>"+photoList[i].attName+"</div>";
					photoListStr += "<div class='isEditHidden mws-gallery-overlay'><div class='mws-gallery-zoom'></div></div></div></li>";
				
			}
			rollListStr += "</ul></div></div>";
			photoListStr += "</ul>";
			$("#TypeList").empty();
			$(photoListStr).appendTo("#TypeList");
			$(rollListStr).appendTo("#mws-container");
			if(1!=isDetail && 2!=isDetail){
				$("#cancelEditPhotoDivBtn").css("display","block");
			}else{
				$("#cancelDownloadPhotoDivBtn").css("display","block");
			}
		}
		
		//查看图片详情
		function checkPhotoDetail(index){
			$(".checkedPhoto").removeAttr("class");
			$("#photoDetail").css("display","block").show();
			isRollPhotoListOpen = true;
			photoListsize = photoListNow.length;
			$("#imgplace").attr("src",photoListNow[index].attURL);
			$("#photoRollList"+index+"").attr("class","checkedPhoto");
			imageIndex = index;
			lefts = -parseInt(index)*110;
			$("#photoRoll ul").css("marginLeft",lefts);
		}
		
		//上一页
		function lastPage(){
			if(imageIndex == 0){
				imageIndex = photoListsize;
				lefts = -(photoListsize-2)*110;
			}
			imageIndex--;
			$("#imgplace").attr("src",photoListNow[imageIndex].attURL);
			$(".checkedPhoto").removeAttr("class");
			$("#photoRollList"+imageIndex+"").attr("class","checkedPhoto");
			lefts += 110;
			$("#photoRoll ul").css("marginLeft",lefts);
		}
		
		//下一页
		function nextPage(){
			imageIndex++;
			if(imageIndex == photoListsize){
				imageIndex = 0;
				lefts = 150;//减去多少显示多少张图片 
			}
			$("#imgplace").attr("src",photoListNow[imageIndex].attURL);
			$(".checkedPhoto").removeAttr("class");
			$("#photoRollList"+imageIndex+"").attr("class","checkedPhoto");
			lefts += -110;
			$("#photoRoll ul").css("marginLeft",lefts);
		}
		
		//滚动栏点击
		function checkPhotoRoll(index){
			imageIndex = index;
			$("#imgplace").attr("src",photoListNow[index].attURL);
			$(".checkedPhoto").removeAttr("class");
			$("#photoRollList"+index+"").attr("class","checkedPhoto");
		}
		
		//关闭详情按钮
		function closeBtnOn(){
			$("#photoDetail").hide();
		}
		
		//返回按钮
		function returnPhotoTypeList(){
			/* $("#photoListUlId").remove();
			$("#photoDetail").remove();
			$("#TypeList").empty();
			$(ulStr).appendTo("#TypeList");
			$("#editPhotoDivBtn").hide(); */
			window.location.reload();
		}
		
		//编辑图片
		function editPhotoList(){
			//鼠标滑过遮盖层隐藏
			$("div .isEditHidden").css("display","none");
			$("#photoListUlId li").css("cursor","default");
			$("div .photoEditDC").css("display","block");
			$("#editPhotoDivBtn").css("display","block");
			$("#cancelEditPhotoDivBtn").hide();
		}
		
		//完成编辑 
		function editPhotoListCancel(){
			$("div .isEditHidden").css("display","");
			$("#photoListUlId li").css("cursor","pointer");
			$("div .photoEditDC").css("display","none");
			$("div .editPhotoChecked").attr("class","editPhotoUNChecked");
			$("#cancelEditPhotoDivBtn").show();
			$("#editPhotoDivBtn").hide();
		}
		
		//完成下载
		function downloadPhotoListCancel(){
			$("div .isEditHidden").css("display","");
			$("#photoListUlId li").css("cursor","pointer");
			$("div .photoEditDC").css("display","none");
			$("div .editPhotoChecked").attr("class","editPhotoUNChecked");
			$("#cancelDownloadPhotoDivBtn").show();
			$("#downloadPhotoDivBtn").hide();
		}
		
		//选中图片
		function photoChecked(obj){
			if("editPhotoChecked"==$(obj).children().attr("class")){
				$(obj).children().attr("class","editPhotoUNChecked");
			}else{
				$(obj).children().attr("class","editPhotoChecked");
			}
		}
		
		//全选
		function editPhotoAllChecked(){
			$("div .editPhotoUNChecked").attr("class","editPhotoChecked");
			$("#allChecked").css("display","none");
			$("#allUnChecked").css("display","block");
		}
		
		//查询附件列表
		function checkAttachmentList(){
			var appNo = $("#appNo").val();
			var userId = $("#userId").val();
			var isDetail = $("#isDetail").val();
			var detailId=$("#detailId").val();
			$.ajax({
				url : "attachmentController/findAttachmentByULA.do",
				data : {"appNo":appNo,"userId":userId,"isDetail":isDetail,"detailId":detailId},
				type : "post",
				async : false,
				success : function(data){
					if($.isEmptyObject(data)){
						$("#noAttachment").next().css("display","none");
						$("#noAttachment").css("display","block");
						return false;
					}else{
						photoTypeList = data;
					}
				},
				error : function(){
					
				}
			});
		}
		
		//删除附件
		function editPhotoDelete(){
			var checked = $("div .editPhotoChecked");
			if(!checked.length>0){
				alert("请先选择一个附件!");
				return false;
			}
			if(confirm('确认删除所选附件?')){
				var photoAttids = "";
				for(var i = 0 ; i < checked.length; i++){
					/* var photoUrl = $(checked[i]).parent().next().children()[0].innerHTML;
					photoUrlls = photoUrl.split('"'); */
					var attId = $(checked[i]).children("input").val();
					photoAttids += attId+",";
				}
				deleteAttachmentByUrl(photoAttids);
				for(var i = 0 ; i < checked.length; i++){
					$(checked[i]).parent().parent().remove();
				}
			} 
		}
		
		//删除一条附件
		function deleteAttachmentByUrl(photoAttids){
	    	$.ajax({
		    	   url:'attachmentController/deleteAttachmentByUrl.do',
		    	   data:{"photoAttids":photoAttids},
		    	   type:"post",
		    	   success:function(data){
		    		   data = $.parseJSON(data);
		    		   alert(data.message);
		    	   },
		    	   error:function(){
		    		   alert("提示","删除失败!");
		    	   }
		       });
		}
		
		//预览其它类型附件
		function onlineReviewFile(attId){
			window.open("jsp/openoffice/documentView.jsp?attId="+attId+"")
		}
		
		//下载图片
		function downLoadPhotoList(){
			$("#cancelDownloadPhotoDivBtn").hide();
			$("div .isEditHidden").css("display","none");
			$("#photoListUlId li").css("cursor","default");
			$("div .photoEditDC").css("display","block");
			$("#downloadPhotoDivBtn").css("display","block");
		}
		
		//反选
		function editPhotoAllUnChecked(){
			$("div .photoEditDC").css("display","block");
			$("div .editPhotoChecked").attr("class","editPhotoUNChecked");
			$("#allChecked").show();
			$("#allUnChecked").hide();
		}
		
		function downloadPhotoAllChecked(){
			$("div .editPhotoUNChecked").attr("class","editPhotoChecked");
			$("#allDownloadChecked").css("display","none");
			$("#allDownloadUnChecked").css("display","block");
		}
		
		function downloadPhotoAllUnChecked(){
			$("div .photoEditDC").css("display","block");
			$("div .editPhotoChecked").attr("class","editPhotoUNChecked");
			$("#allDownloadChecked").show();
			$("#allDownloadUnChecked").hide();
		}
		
		//下载所选
		function downloadPhoto(){
			var checked = $("div .editPhotoChecked");
			if(!checked.length>0){
				alert("请先选择一个附件!");
				return false;
			}
			if(confirm('确认下载所选附件?')){
				var photoAttids = "";
				for(var i = 0 ; i < checked.length; i++){
					var photoAttid = $(checked[i]).children("input").val();
					photoAttids += photoAttid+",";
				}
				downloadAttachment(photoAttids);
			} 
		}
		
		//下载附件
		function downloadAttachment(photoAttids){
			downFileByFormPost("attachmentController/downloadAttachment.do", {"photoAttids":photoAttids})
		}
	</script>
</body>
</html>