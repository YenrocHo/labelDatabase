/**
 * 阶段添加判断页面js
 */

$("#form-add").validate({
	rules:{
		stage:{
			required:true,
            remote: {
                url: rootPath + "/StageController/checkStageUnique",
                type: "post",
                dataType: "json",
                data: {
                    name: function () {
                        return $.trim($("#stage").val());
                    }
                },
                dataFilter: function (data, type) {
                    if (data == "0") return true;
                    else return false;
                }
            }
		},
	},
	messages: {
        "stage": {
            remote: "此阶段已经存在"
        }
    },
	submitHandler:function(form){
		add();
	}
});

/**
 * 用户添加方法
 */
function add() {
	var dataFormJson=$("#form-add").serialize();
	$.ajax({
		cache : true,
		type : "POST",
		url : rootPath + "/StageController/add",
		data : dataFormJson,
		async : false,
		error : function(request) {
			$.modal.alertError("系统错误");
		},
		success : function(data) {
			$.operate.saveSuccess(data);
		}
	});
}

