
    itemsCodeFun();
    $("#form-edit").validate({
        rules: {
            product: {
                required: true,
            },
            itemsCode: {
                required: true,
            },
            submitHandler: function (form) {
                edit();
            }
        }
    });
function edit() {
    var dataFormJson = $("#form-edit").serialize();
    $.ajax({
        cache : true,
        type : "POST",
        url : rootPath + "/ProductController/edit",
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

//根据项目点显示存储条件
function itemsCodeFun() {
    var items =$("[name='itemsCode']").val();
    $.ajax({
        url: rootPath + "/ProductController/findStore",
        type: "get",
        dataType: "json",
        data: {
            "itemsCode": items,
        },
        success: function (result) {
            storeView(result)
        }
    });
}

//食品种类渲染页面
function storeView(result) {
    if (result != null) {
        $("#sysStores").html("");
        $.each(result.data, function (i, d) {
            var store_str = "";
            store_str += "<label>";
            store_str += "<input class='sqcheckbox' name='store' value='" + d.id + "' checked type='checkbox'><b>" + d.name + "</b>";
            store_str += "</label>";
            $("#sysStores").append(store_str);
        });

    }
}