/**
 * @license Copyright (c) 2003-2014, CKSource - Frederico Knabben. All rights reserved.
 * For licensing, see LICENSE.html or http://ckeditor.com/license
 */

CKEDITOR.editorConfig = function( config ) {
	// Define changes to default configuration here. For example:
	// config.language = 'fr';
	// config.uiColor = '#AADC6E';
	config.toolbar = 'Full';
	config.pasteFromWordIgnoreFontFace = false;
	config.toolbar_Full = [
		['Bold','Italic','Underline','Strike'],
		['Styles','Format','Font','FontSize'],
		['TextColor','BGColor','Smiley']
		];
	config.width = 620; //宽度
    config.height = 400; //高度
	var pathName = window.document.location.pathname;
    //获取带"/"的项目名，如：/budgetSys
    var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
   /* config.filebrowserImageUploadUrl = projectName+'/smSysmanager/ckeditorUpload.action'; //固定路径
*/    
    
	//自定义表情的放置目录  
    config.smiley_path= projectName+"/js/ckeditor/plugins/smiley/self/";  
    //设置对话框一行显示几个表情  
    config.smiley_columns = 10;  
    //设置对话框表情  每一个表情的名字  
    config.smiley_images = ['emo_01.gif','emo_02.gif','emo_03.gif','emo_04.gif','emo_05.gif','emo_06.gif','emo_07.gif','emo_08.gif','emo_09.gif','emo_10.gif',
                            'emo_11.gif','emo_12.gif','emo_13.gif','emo_14.gif','emo_15.gif','emo_16.gif','emo_17.gif','emo_18.gif','emo_19.gif','emo_20.gif',
                            'emo_21.gif','emo_22.gif','emo_23.gif','emo_24.gif','emo_25.gif','emo_26.gif','emo_27.gif','emo_28.gif','emo_29.gif','emo_30.gif',
                            'emo_31.gif','emo_32.gif','emo_33.gif','emo_34.gif','emo_35.gif','emo_36.gif','emo_37.gif','emo_38.gif','emo_39.gif','emo_40.gif',
                            'emo_41.gif','emo_42.gif','emo_43.gif','emo_44.gif','emo_45.gif','emo_46.gif','emo_47.gif','emo_48.gif','emo_49.gif','emo_50.gif',
                            'emo_51.gif','emo_52.gif','emo_53.gif','emo_54.gif','emo_55.gif','emo_56.gif','emo_57.gif','emo_58.gif','emo_59.gif','emo_60.gif'
                            ];  
    config.smiley_descriptions = ['小狗','神马','浮云','给力','聚集','V5','熊猫','囧兔兔','奥特曼','囧',
                                  '互粉','礼物','微笑','笑','大笑','可爱','可怜','抠鼻','汗','害羞',
                                  '调皮','闭嘴','鄙视','爱心','流泪','偷笑','亲亲','生病','开心','糗大了',
                                  '右哼哼','左哼哼','嘘','衰','委屈','呕吐','哈欠','卖萌','愤怒','疑问',
                                  '饥饿','再见','思考','流汗','打叫','睡觉','财迷','叹气','酷','色',
                                  '无语','鼓掌','晕','哭泣','抓狂','冷汗','坏笑','咒骂','爱心','心碎'
                              ]
};
