/**
 * 用户页面js
 */

$("#form-add").validate({
	rules:{
		username:{
			required:true,
			minlength: 2,
			maxlength: 20,
			remote: {
                url: rootPath + "/UserController/checkLoginNameUnique",
                type: "post",
                dataType: "json",
                dataFilter: function(data, type) {
                    if (data == "0")
                    	return true;
                    else 
                    	return false;
                }
            }
		},
		number:{
			required:true,
			minlength: 2,
			maxlength: 20,
			remote: {
				url: rootPath + "/UserController/checkNumberUnique",
				type: "post",
				dataType: "json",
				dataFilter: function(data, type) {
					if (data == "0")
						return true;
					else
						return false;
				}
			}
		},
		deptName:{
			required:true,
		},
		password:{
			required:true,
			minlength: 5,
			maxlength: 20
		},
	},
	messages: {
        "username": {
            remote: "用户已经存在"
        },"number": {
            remote: "工号已经存在"
        },
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
		url : rootPath + "/UserController/add",
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

