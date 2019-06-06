/**
 * 产品页面js
 */

$("#form-add").validate({
    rules:{
        cName:{
            required:true,
            minlength: 1,
            maxlength: 20,
            remote: {
                url: rootPath + "/ProductController/checkcNameUnique",
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
        eName:{
            required:true,
            minlength: 1,
            maxlength: 20
        }
    },
    messages: {
        "cname": {
            remote: "产品名已经存在"
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
        url : rootPath + "/ProductController/add",
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

